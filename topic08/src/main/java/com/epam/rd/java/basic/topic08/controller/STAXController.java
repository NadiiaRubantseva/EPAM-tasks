package com.epam.rd.java.basic.topic08.controller;

import com.epam.rd.java.basic.topic08.constants.XML;
import com.epam.rd.java.basic.topic08.entity.Answer;
import com.epam.rd.java.basic.topic08.entity.Question;
import com.epam.rd.java.basic.topic08.entity.Test;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.stream.StreamSource;

/**
 * Controller for StAX parser.
 */
public class STAXController extends DefaultHandler {

	private String xmlFileName;

	// main container
	private Test test;

	public Test getTest() {
		return test;
	}

	public STAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	/**
	 * Parses XML document with StAX (based on event reader). There is no validation during the
	 * parsing.
	 */
	public void parse() throws XMLStreamException {

		Question question = null;
		Answer answer = null;

		// current element name holder
		String currentElement = null;

		XMLInputFactory factory = XMLInputFactory.newInstance();

		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

		XMLEventReader reader = factory.createXMLEventReader(
				new StreamSource(xmlFileName));

		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();

			// skip any empty content
			if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
				continue;
			}

			// handler for start tags
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				currentElement = startElement.getName().getLocalPart();

				if (XML.TEST.equalsTo(currentElement)) {
					test = new Test();
					continue;
				}

				if (XML.QUESTION.equalsTo(currentElement)) {
					question = new Question();
					continue;
				}

				if (XML.ANSWER.equalsTo(currentElement)) {
					answer = new Answer();
					Attribute attribute = startElement.getAttributeByName(
							new QName(XML.CORRECT.value()));
					if (attribute != null) {
						answer.setCorrect(Boolean.parseBoolean(attribute.getValue()));
					}
				}
			}

			// handler for contents
			if (event.isCharacters()) {
				Characters characters = event.asCharacters();

				if (XML.QUESTION_TEXT.equalsTo(currentElement)) {
					question.setQuestionText(characters.getData());
					continue;
				}

				if (XML.ANSWER.equalsTo(currentElement)) {
					answer.setContent(characters.getData());
					continue;
				}
			}

			// handler for end tags
			if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				String localName = endElement.getName().getLocalPart();

				if (XML.QUESTION.equalsTo(localName)) {
					test.getQuestion().add(question);
					continue;
				}

				if (XML.ANSWER.equalsTo(localName)) {
					question.getAnswer().add(answer);
				}
			}
		}
		reader.close();
	}


}
package com.epam.rd.java.basic.topic08.controller;

import com.epam.rd.java.basic.topic08.constants.Constants;
import com.epam.rd.java.basic.topic08.constants.XML;
import com.epam.rd.java.basic.topic08.entity.Answer;
import com.epam.rd.java.basic.topic08.entity.Question;
import com.epam.rd.java.basic.topic08.entity.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Controller for SAX parser.
 */
public class SAXController extends DefaultHandler {

	private final String xmlFileName;

	// current element name holder
	private String currentElement;

	// main container
	private Test test;

	private Question question;

	private Answer answer;

	public SAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	/**
	 * Parses XML document.
	 *
	 * @param validate If true validate XML document against its XML schema. With
	 *                 this parameter it is possible make parser validating.
	 */
	public void parse(boolean validate)
			throws ParserConfigurationException, SAXException, IOException {

		// obtain sax parser factory
		SAXParserFactory factory = SAXParserFactory.newInstance();

		// XML document contains namespaces
		factory.setNamespaceAware(true);

		// set validation
		if (validate) {
			factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		SAXParser parser = factory.newSAXParser();
		parser.parse(xmlFileName, this);
	}

	// ///////////////////////////////////////////////////////////
	// ERROR HANDLER IMPLEMENTATION
	// ///////////////////////////////////////////////////////////

	@Override
	public void error(org.xml.sax.SAXParseException e) throws SAXException {
		// if XML document not valid just throw exception
		throw e;
	}

	public Test getTest() {
		return test;
	}

	// ///////////////////////////////////////////////////////////
	// CONTENT HANDLER IMPLEMENTATION
	// ///////////////////////////////////////////////////////////


	@Override
	public void startElement(String uri, String localName, String qName,
							 Attributes attributes) throws SAXException {

		currentElement = localName;

		if (XML.TEST.equalsTo(currentElement)) {
			test = new Test();
			return;
		}

		if (XML.QUESTION.equalsTo(currentElement)) {
			question = new Question();
			return;
		}

		if (XML.ANSWER.equalsTo(currentElement)) {
			answer = new Answer();
			if (attributes.getLength() > 0) {
				answer.setCorrect(Boolean.parseBoolean(attributes.getValue(uri,
						XML.CORRECT.value())));
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String elementText = new String(ch, start, length).trim();

		// return if content is empty
		if (elementText.isEmpty()) {
			return;
		}

		if (XML.QUESTION_TEXT.equalsTo(currentElement)) {
			question.setQuestionText(elementText);
			return;
		}

		if (XML.ANSWER.equalsTo(currentElement)) {
			answer.setContent(elementText);
			return;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (XML.QUESTION.equalsTo(localName)) {
			// just add question to container
			test.getQuestion().add(question);
			return;
		}

		if (XML.ANSWER.equalsTo(localName)) {
			// just add answer to container
			question.getAnswer().add(answer);
			return;
		}
	}
}
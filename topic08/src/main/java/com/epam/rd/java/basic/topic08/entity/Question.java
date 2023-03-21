
package com.epam.rd.java.basic.topic08.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Question complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Question"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="QuestionText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;sequence&gt;
 *           &lt;element name="Answer" type="{http://com.epam.rd.java.basic.topic08}Answer" maxOccurs="4"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Question", namespace = "http://com.epam.rd.java.basic.topic08", propOrder = {
    "questionText",
    "answer"
})
public class Question {

    @XmlElement(name = "QuestionText", required = true)
    protected String questionText;
    @XmlElement(name = "Answer", required = true)
    protected List<Answer> answer;

    /**
     * Gets the value of the questionText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Sets the value of the questionText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestionText(String value) {
        this.questionText = value;
    }

    /**
     * Gets the value of the answer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the answer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnswer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Answer }
     * 
     * 
     */
    public List<Answer> getAnswer() {
        if (answer == null) {
            answer = new ArrayList<Answer>();
        }
        return this.answer;
    }

}

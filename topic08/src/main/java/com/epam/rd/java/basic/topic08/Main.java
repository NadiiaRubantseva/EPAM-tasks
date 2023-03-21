package com.epam.rd.java.basic.topic08;

import com.epam.rd.java.basic.topic08.controller.*;
import com.epam.rd.java.basic.topic08.entity.Test;
import com.epam.rd.java.basic.topic08.util.Sorter;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            return;
        }

        String xmlFileName = args[0];
        System.out.println("Input ==> " + xmlFileName);

        ////////////////////////////////////////////////////////
        // DOM
        ////////////////////////////////////////////////////////

        // get container
        DOMController domController = new DOMController(xmlFileName);
        domController.parse(true);
        Test test = DOMController.getTest();

        // sort (case 1)
        Sorter.sortQuestionsByQuestionText(test);

        // save
        String outputXmlFile = "output.dom.xml";
        DOMController.saveToXML(test, outputXmlFile);

        ////////////////////////////////////////////////////////
        // SAX
        ////////////////////////////////////////////////////////

        // get
        SAXController saxController = new SAXController(xmlFileName);
        saxController.parse(true);
        test = saxController.getTest();

        // sort  (case 2)
        Sorter.sortQuestionsByQuestionText(test);

        // save
        outputXmlFile = "output.sax.xml";
        DOMController.saveToXML(test, outputXmlFile);

        ////////////////////////////////////////////////////////
        // StAX
        ////////////////////////////////////////////////////////

        // get
        STAXController staxController = new STAXController(xmlFileName);
        staxController.parse();

        // sort  (case 3)
        Sorter.sortQuestionsByQuestionText(test);

        // save
        outputXmlFile = "output.stax.xml";
        DOMController.saveToXML(test, outputXmlFile);
    }
}

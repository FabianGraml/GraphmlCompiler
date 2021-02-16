package net.htlgrieskirchen.aud4.election;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class Parser {
    public void read(String filename) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(filename));
        NodeList nodes = doc.getElementsByTagName("node");
        for (int i = 0; i < nodes.getLength(); i++) {
            //System.out.println(((Element) nodes.item(i)).getElementsByTagName("y:NodeLabel").item(0).getTextContent() + i);

        }
        NodeList edges = doc.getElementsByTagName("edge");
        for (int i = 0; i < nodes.getLength(); i++) {
            //System.out.println(((Element) nodes.item(i)).getElementsByTagName("y:NodeLabel").item(0).getTextContent());
            String source = (((Element) edges.item(i)).getAttribute("source"));
            String target = (((Element) edges.item(i)).getAttribute("target"));
            String[] sourceSplit = source.split("::");
            source = sourceSplit[1].replace("n", "");


        }
    }
}

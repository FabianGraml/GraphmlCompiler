package net.htlgrieskirchen.aud4.election;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Parser {
    public void read(String filename) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(filename));
        NodeList nodes = doc.getElementsByTagName("node");
        for (int i = 0; i < nodes.getLength(); i++) {

        }
        NodeList edges = doc.getElementsByTagName("edge");
        Files.delete(Paths.get("src/net/htlgrieskirchen/aud4/election/edges.txt"));
        Files.createFile(Paths.get("src/net/htlgrieskirchen/aud4/election/edges.txt"));
        for (int i = 0; i < nodes.getLength(); i++) {
            String output = ((Element) nodes.item(i)).getElementsByTagName("y:NodeLabel").item(0).getTextContent();
            System.out.println(output);
            Files.write( Paths.get("src/net/htlgrieskirchen/aud4/election/edges.txt"), (output+"\n").getBytes(), StandardOpenOption.APPEND);
            String source = (((Element) edges.item(i)).getAttribute("source"));
            String target = (((Element) edges.item(i)).getAttribute("target"));
            String[] sourceSplit = source.split("::");
            source = sourceSplit[1].replace("n", "");

        }

    }
}

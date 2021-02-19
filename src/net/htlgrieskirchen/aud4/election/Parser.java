package net.htlgrieskirchen.aud4.election;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    Map<Integer, Integer> edgeMap = new HashMap<>();
    List<String> commandList = new ArrayList<>();
    Klasse klasse = new Klasse();
    public void read(String filename) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(filename));
        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getElementsByTagName("node");
        for (int i = 0; i < nodes.getLength(); i++) {
            String id = ((Element) nodes.item(i)).getAttribute("id");
            Element element = ((Element) nodes.item(i));
            String nameSpace = element.getElementsByTagName("y:NodeLabel").item(0).getTextContent().trim();
            if(i == 0){
                klasse.methodName = nameSpace;
            }

            System.out.println(nameSpace);
            commandList.add(nameSpace);
        }

        NodeList edges = doc.getElementsByTagName("edge");
        int targetInt = 0;
        int sourceInt = 0;
        for (int i = 0; i < nodes.getLength(); i++) {
            //System.out.println(((Element) nodes.item(i)).getElementsByTagName("y:NodeLabel").item(0).getTextContent());
            String source = (((Element) edges.item(i)).getAttribute("source"));
            String target = (((Element) edges.item(i)).getAttribute("target"));
            String[] sourceSplit = source.split("::");
            String[] targetSplit = target.split("::");
            sourceInt = Integer.parseInt(sourceSplit[1].replace("n", ""));

            if (targetSplit.length == 2) {
                targetInt = Integer.parseInt(targetSplit[1].replace("n", ""));
            }
            else{
                targetInt = -1;
            }
            System.out.println("S: "+source);
            System.out.println("T: "+target);
            edgeMap.put(sourceInt, targetInt);
        }
        System.out.println("");
    }
    public void paste(){
        for (int i = 0; i < commandList.size(); i++) {
            int target = edgeMap.get(i);
            System.out.println(commandList.get(i));
            //System.out.println(nodeList.get(i+1).getMessage());
        }

    }
}

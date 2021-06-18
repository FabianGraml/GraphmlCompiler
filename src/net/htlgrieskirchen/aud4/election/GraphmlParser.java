package net.htlgrieskirchen.aud4.election;

import net.htlgrieskirchen.aud4.election.Graph.Edge;
import net.htlgrieskirchen.aud4.election.Graph.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class GraphmlParser {

    Map<Node, List<Edge>> codeList = new HashMap<>();

    public Map<Node, List<Edge>> parse(String filename){
        List<Node> nodes = readNodes(filename);
        List<Edge> edges = readEdges(filename);

        edges.forEach(edge -> {
            Node nodeFrom = nodes.stream().filter(node -> node.getId().equals(edge.getFrom())).findFirst().orElse(null);

            List<Edge> nodeEdges = codeList.get(nodeFrom);
            if(nodeFrom != null) {
                if (codeList.get(nodeFrom) != null){
                    codeList.get(nodeFrom);
                } else {
                    nodeEdges = new ArrayList<>();
                }
                nodeEdges.add(edge);

                codeList.put(nodeFrom, nodeEdges);
            }
        });
        return codeList;
    }

    //Just reading all Edges here
    public List<Edge> readEdges(String filename){
        List<Edge> edges = new ArrayList<>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(filename);
            doc.getDocumentElement().normalize();
            NodeList graphEdges = doc.getElementsByTagName("edge");
            for (int i = 0; i < graphEdges.getLength(); i++) {
                String source = ((Element) graphEdges.item(i)).getAttribute("source");
                String target = ((Element) graphEdges.item(i)).getAttribute("target");
                String arrow = ((Element)((Element) graphEdges.item(i)).getElementsByTagName("y:Arrows").item(0)).getAttribute("source");
                int priority = arrow.equals("skewed_dash") ? 0 : 1;
                edges.add(new Edge(source, target, priority));
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return edges;
    }
    //Just reading all Nodes here
    public List<Node> readNodes(String filename){

        List<Node> nodes = new ArrayList<>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(filename);
            doc.getDocumentElement().normalize();
            NodeList graphNodes = doc.getElementsByTagName("node");
            for (int i = 0; i < graphNodes.getLength(); i++) {
                String id = ((Element) graphNodes.item(i)).getAttribute("id");
                String text = ((Element) graphNodes.item(i)).getElementsByTagName("y:NodeLabel").item(0).getTextContent().trim();
                nodes.add(new Node(id, text));
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return nodes;
    }

// Code that works but doesn't acutally work
    public void read(String filename) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(filename));
        NodeList nodes = doc.getElementsByTagName("node");
        for (int i = 0; i < nodes.getLength(); i++) {

        }
        NodeList edges = doc.getElementsByTagName("edge");
        Files.delete(Paths.get("src/net/htlgrieskirchen/aud4/election/Files/edges.txt"));
        Files.createFile(Paths.get("src/net/htlgrieskirchen/aud4/election/Files/edges.txt"));
        for (int i = 0; i < nodes.getLength(); i++) {
            String output = ((Element) nodes.item(i)).getElementsByTagName("y:NodeLabel").item(0).getTextContent();
            System.out.println(output);
            Files.write(Paths.get("src/net/htlgrieskirchen/aud4/election/Files/edges.txt"), (output + "\n").getBytes(), StandardOpenOption.APPEND);
            String source = (((Element) edges.item(i)).getAttribute("source"));
            String target = (((Element) edges.item(i)).getAttribute("target"));
            String[] sourceSplit = source.split("::");
            source = sourceSplit[1].replace("n", "");
        }
    }


}

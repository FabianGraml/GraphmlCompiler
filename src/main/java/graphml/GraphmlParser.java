package graphml;

import models.Edge;
import models.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphmlParser {
    Map<Node, List<Edge>> codeList = new HashMap<>();
    public Map<Node, List<Edge>> parse(String filename) {
        List<Node> nodes = readNodes(filename);
        List<Edge> edges = readEdges(filename);

        edges.forEach(edge -> {
            Node nodeFrom = nodes.stream().filter(node -> node.getNodeId().equals(edge.getEdgeFrom())).findFirst().orElse(null);

            List<Edge> nodeEdges = codeList.get(nodeFrom);
            if (nodeFrom != null) {
                if (codeList.get(nodeFrom) != null) {
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
    public List<Edge> readEdges(String filename) {
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
                edges.add(new Edge(source, target));
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return edges;
    }
    public List<Node> readNodes(String filename) {

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
                if (!text.isEmpty()) {
                    nodes.add(new Node(id, text));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return nodes;
    }
}

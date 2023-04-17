package graphml;

import models.Edge;
import models.Node;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GraphmlCompiler {
    private BufferedWriter bw;
    private Map<Node, List<Edge>> codeList;

    public GraphmlCompiler(String targetFileName, String sourceFileName) {
        try {
            this.bw = new BufferedWriter(new FileWriter(targetFileName));
            this.codeList = new GraphmlParser().parse(sourceFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void compile() {
        writeLineToFile("package files;\nimport java.util.HashMap;\n" + "import java.util.Map;\n" + "\n" + "public class ElectionUtil {" + "\n" + "public ElectionUtil(){}" + "\n" + "public static String evaluate(String[] votes) {");
        Node startNode = findStartNode();
        Node lastNode = findLastNode();
        setBrackets();
        boolean returnElse = true;
        codeBuilder(startNode, returnElse);
        writeLineToFile("return result;\n}\n}");
    }

    private void writeLineToFile(String line) {
        try {
            bw.newLine();
            bw.write(line);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Node findLastNode() {
        return codeList.keySet().stream().filter(x -> x.getNodeId().equals("n0::n9")).findFirst().get();
    }

    private Node findStartNode() {
        return codeList.keySet().stream().filter(x -> x.getNodeId().equals("n1")).findFirst().get();
    }

    private void setBrackets() {
        codeList.keySet().forEach(x -> {
            if (x.getNodeValue().startsWith("if") || x.getNodeValue().startsWith("for")) {
                x.setNodeValue(x.getNodeValue() + "{");
            }
        });
    }

    private void codeBuilder(Node currentNode, boolean returnElse) {
        if (currentNode.getNodeValue().equals("String[] votes")) {
            currentNode.setNodeValue(" ");
        }
        if (!currentNode.getNodeValue().contains("return")) {
            writeLineToFile(currentNode.getNodeValue());
        }
        currentNode.setNodeVisited(true);

        List<Edge> outgoingEdges = codeList.get(currentNode);
        for (int i = 0; i < outgoingEdges.size(); i++) {
            Edge edge = outgoingEdges.get(i);
            Node nextNode = codeList.keySet().stream()
                    .filter(x -> x.getNodeId().equals(edge.getEdgeTo()))
                    .findFirst().orElse(null);

            if (nextNode != null && !nextNode.isNodeVisited()) {
                if (returnElse && currentNode.getNodeValue().startsWith("if") && i > 0) {
                    writeLineToFile("} else {");
                }
                codeBuilder(nextNode, returnElse);
            }
        }
        if (currentNode.getNodeValue().startsWith("if") || currentNode.getNodeValue().startsWith("for")) {
            writeLineToFile("}");
        }
    }
}

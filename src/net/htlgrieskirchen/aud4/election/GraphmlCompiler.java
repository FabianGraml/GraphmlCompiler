package net.htlgrieskirchen.aud4.election;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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
        writeLineToFile("package net.htlgrieskirchen.aud4.election.Files;\nimport java.util.HashMap;\n" + "import java.util.Map;\n" + "\n" + "public class ElectionUtil {" + "\n" +"public ElectionUtil(){}"+"\n"+ "public static String evaluate(String[] votes) {");

        Node lastNode = null;
        //you entered Map<Long, String> entry
        for (Map.Entry<Node, List<Edge>> entry : codeList.entrySet()) {
            lastNode = entry.getKey();
        }
        //System.out.println(lastNode.getValue());
        setBrackets();
        Node startNode = findStartNode();
        lastNode = findLastNode();
        boolean returnElse = true;
        codeBuilder(startNode, returnElse);
        writeLineToFile("\n}\n}\n}\n");
        writeLineToFile("return result;}\n}");
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

    private void codeBuilder(Node firstNode, boolean returnElse) {
        if (firstNode.getNodeValue().equals("String[] votes")) {
            firstNode.setNodeValue(" ");
        }
        if(!firstNode.getNodeValue().contains("return")) {
            writeLineToFile(firstNode.getNodeValue());
        }
        firstNode.setNodeVisited(true);
        boolean setElse = true;

        for (Edge edge : codeList.get(firstNode)){

            Node nextNode = codeList.keySet().stream().filter(x -> x.getNodeId().equals(edge.getEdgeTo())).findFirst().orElse(null);

            if (nextNode != null) {

                if (!nextNode.isNodeVisited()) {
                    codeBuilder(nextNode, returnElse);
                }
                if (returnElse) {
                    if (firstNode.getNodeValue().startsWith("if") && codeList.get(firstNode).size() > 1&& (!firstNode.getNodeId().contains("n0::n7"))) {
                        if (setElse) {
                            writeLineToFile("}else { ");
                            setElse = false;
                        }
                    }
                }
                if (firstNode.getNodeValue().startsWith("for")) {
                    writeLineToFile("}");
                }
            }
        }

    }
}

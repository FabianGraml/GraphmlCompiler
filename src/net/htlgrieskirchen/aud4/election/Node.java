package net.htlgrieskirchen.aud4.election;

public class Node {

    private String nodeId;
    private String nodeValue;
    private boolean nodeVisited;

    public Node(String nodeId, String nodeValue) {
        this.nodeId = nodeId;
        this.nodeValue = nodeValue;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public boolean isNodeVisited() {
        return nodeVisited;
    }

    public void setNodeVisited(boolean nodeVisited) {
        this.nodeVisited = nodeVisited;
    }
}

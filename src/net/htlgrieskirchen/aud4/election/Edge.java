package net.htlgrieskirchen.aud4.election;

public class Edge {
    private String edgeFrom;
    private String edgeTo;

    public Edge(String edgeFrom, String edgeTo) {
        this.edgeFrom = edgeFrom;
        this.edgeTo = edgeTo;
    }

    public String getEdgeFrom() {
        return edgeFrom;
    }

    public void setEdgeFrom(String edgeFrom) {
        this.edgeFrom = edgeFrom;
    }

    public String getEdgeTo() {
        return edgeTo;
    }

    public void setEdgeTo(String edgeTo) {
        this.edgeTo = edgeTo;
    }
}

package net.htlgrieskirchen.aud4.election.Graph;

public class Edge {
    private String from, to;
    private int priority;

    public Edge(String from, String to, int priority) {
        this.from = from;
        this.to = to;
        this.priority = priority;
    }


    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getPriority() {
        return priority;
    }
}

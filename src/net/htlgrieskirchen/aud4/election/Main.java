package net.htlgrieskirchen.aud4.election;

public class Main {
    public static void main(String[] args) {
        GraphmlParser graphmlParser = new GraphmlParser();
        graphmlParser.parse("src/net/htlgrieskirchen/aud4/election/Files/ElectionUtilBasic.graphml");
        GraphmlCompiler graphmlCompiler = new GraphmlCompiler("src/net/htlgrieskirchen/aud4/election/Files/ElectionUtil.java","src/net/htlgrieskirchen/aud4/election/Files/ElectionUtilBasic.graphml");
        graphmlCompiler.compile();
    }
}

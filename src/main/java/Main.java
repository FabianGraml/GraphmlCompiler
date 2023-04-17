import graphml.GraphmlCompiler;
import graphml.GraphmlParser;

public class Main {
    public static void main(String[] args) {
        GraphmlParser graphmlParser = new GraphmlParser();
        graphmlParser.parse("src/main/java/files/ElectionUtilBasic.graphml");
        GraphmlCompiler graphmlCompiler = new GraphmlCompiler("src/main/java/files/ElectionUtil.java","src/main/java/files/ElectionUtilBasic.graphml");
        graphmlCompiler.compile();
    }
}
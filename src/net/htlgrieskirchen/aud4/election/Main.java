package net.htlgrieskirchen.aud4.election;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        GraphmlParser graphmlParser = new GraphmlParser();
        graphmlParser.parse("src/net/htlgrieskirchen/aud4/election/Files/ElectionUtilBasic.graphml");
        Compiler compiler = new Compiler("src/net/htlgrieskirchen/aud4/election/Files/Test.java","src/net/htlgrieskirchen/aud4/election/Files/ElectionUtilBasic.graphml");
        compiler.compile();
    }
}

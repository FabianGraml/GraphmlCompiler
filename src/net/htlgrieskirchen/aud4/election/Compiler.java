package net.htlgrieskirchen.aud4.election;
import net.htlgrieskirchen.aud4.election.Graph.Edge;
import net.htlgrieskirchen.aud4.election.Graph.Node;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Compiler {

    private BufferedWriter targetFileWriter;
    private Map<Node, List<Edge>> codeList;

   public Compiler(String targetFileName, String sourceFileName){
       try {
           this.targetFileWriter = new BufferedWriter(new FileWriter(targetFileName));
           this.codeList = new GraphmlParser().parse(sourceFileName);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   public void compile(){
       try {
           targetFileWriter.write("package net.htlgrieskirchen.aud4.election.Files;\nimport java.util.HashMap;\n" +"import java.util.Map;\n" +"\n" +"public class Test {" +"\n"+"public static String evaluate(String[] votes) {");
           targetFileWriter.flush();
       } catch (IOException e) {
           e.printStackTrace();
       }
       System.out.println("public static String evaluate(String[] votes) {");
       setBrackets();
       Node startNode = findStartNode();
       codeBuilder(startNode);
       System.out.println("}");
       try {
           targetFileWriter.newLine();
           targetFileWriter.write("return result; }");
           targetFileWriter.flush();  targetFileWriter.newLine();
           targetFileWriter.write("}");
           targetFileWriter.flush();
       } catch (IOException e) {
           e.printStackTrace();
       }

   }
   private Node findStartNode(){
       return codeList.keySet().stream().filter(x -> x.getId().equals("n1")).findFirst().get();
   }
   private void setBrackets(){
           codeList.keySet().forEach(x -> {
               if (x.getValue().startsWith("if") || x.getValue().startsWith("for")) {
                   x.setValue(x.getValue() + "{");
               }
           });
   }
   private void codeBuilder(Node node){
       if (node.getValue().equals("String[] votes")){
           node.setValue(" ");
       }

       try {
           targetFileWriter.newLine();
           targetFileWriter.write(node.getValue());
           targetFileWriter.flush();
       } catch (IOException e) {
           e.printStackTrace();
       }

       System.out.println(node.getValue());
       node.setVisited(true);
       codeList.get(node).forEach(edge -> {
           Node nextNode = codeList.keySet().stream().filter(x -> x.getId().equals(edge.getTo())).findFirst().orElse(null);

           if (nextNode != null){

               if (!nextNode.isVisited()){
                   codeBuilder(nextNode);
               }
           }
       });
       if (node.getValue().startsWith("if") || node.getValue().startsWith("for") ){

           try {
               targetFileWriter.newLine();
               targetFileWriter.write("}");
               targetFileWriter.flush();
           } catch (IOException e) {
               e.printStackTrace();
           }

           System.out.println("}");
       }



   }
}

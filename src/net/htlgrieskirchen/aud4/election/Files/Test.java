package net.htlgrieskirchen.aud4.election.Files;
import java.util.HashMap;
import java.util.Map;

public class Test {
public static String evaluate(String[] votes) {
 
String result = "none";
if (votes.length != 0){
HashMap<String, Integer> candidates;
candidates = new HashMap<>();
for (String vote : votes){
if (candidates.get(vote) != null){
candidates.put(vote, candidates.get(vote) + 1);
candidates.put(vote, 1);
}
Map.Entry<String, Integer> winner = null;
for (Map.Entry<String, Integer> candidate : candidates.entrySet()){
if (winner != null){
if (candidate.getValue() > winner.getValue()){
winner = candidate;
}
winner = candidate;
}
result = winner.getKey();
for (Map.Entry<String, Integer> candidate1 : candidates.entrySet()){
if (!candidate1.equals(winner)){
if (candidate1.getValue().equals(winner.getValue())){
result = "draw";
break;
}
}
return result;
}
}
}
}
return result; }
}
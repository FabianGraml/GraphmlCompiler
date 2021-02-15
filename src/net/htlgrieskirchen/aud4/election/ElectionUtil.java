/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.aud4.election;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Torsten Welsch
 */
public final class ElectionUtil {
    
    private ElectionUtil() {}
    
        public static String evaluate(String[] votes) {
        String result = "none";

        if (votes.length != 0) {
            HashMap<String, Integer> candidates;
            candidates = new HashMap<>();

            for (String s : votes) {
                if (candidates.get(s) != null) {
                    candidates.put(s, candidates.get(s) + 1);
                } else {
                    candidates.put(s, 1);
                }

            }

            Map.Entry<String, Integer> winner = null;
            for (Map.Entry<String, Integer> candidate : candidates.entrySet()) {
                if (winner != null) {
                    if (candidate.getValue() > winner.getValue()) {
                        winner = candidate;
                    }
                }
                else {
                    winner = candidate;
                }
            }
            
            result = winner.getKey();
            
            for (Map.Entry<String, Integer> candidate : candidates.entrySet()) {
                if (!candidate.equals(winner)) {
                    if (candidate.getValue().equals(winner.getValue())) {
                        result = "draw";
                        break;

                    }
                }
            }
        }
        
        return result;
    }
    
}

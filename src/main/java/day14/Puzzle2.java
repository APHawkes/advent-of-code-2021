package day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Puzzle2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day14/input.txt"));
        String currentLine;

        StringBuilder template = new StringBuilder(reader.readLine());

        Map<String, Long> pairCount = new HashMap<>();
        for (int i = 0; i < template.length()-1; i++) {
            String pattern = template.substring(i, i+2);
            pairCount.merge(pattern,1L, (x,y)->x+y);
        }

        //add pairs for "_X" and "X_" at front/back of template
        pairCount.put("_" + template.substring(0,1), 1L);
        pairCount.put(template.substring(template.length()-1) + "_", 1L);


        Map<String, List<String>> insertionRules = new HashMap<>();
        while (null != (currentLine = reader.readLine())) {
            if (currentLine.contains("->")){
                StringTokenizer st = new StringTokenizer(currentLine, " ->");
                String pair = st.nextToken();
                String insertion = st.nextToken();
                insertionRules.put(pair, List.of(
                        pair.substring(0,1) + insertion,
                        insertion + pair.substring(1,2)));
            }
        }

        for (int step = 0; step < 40; step++) {
            Map<String, Long> newPairCount = new HashMap<>();
            for(Map.Entry<String, Long> entry : pairCount.entrySet()){
                if (insertionRules.keySet().contains(entry.getKey())) {
                   for (String newPair : insertionRules.get(entry.getKey())) {
                       newPairCount.merge(newPair, entry.getValue(), (x,y)->x+y);
                   }
                } else {
                    newPairCount.merge(entry.getKey(), entry.getValue(), (x,y)->x+y);
                }
            }
            pairCount = newPairCount;
        }

        Map<Character, Long> charCount = new HashMap<>();
        for (Map.Entry<String,Long> pair : pairCount.entrySet()) {
            charCount.merge(pair.getKey().charAt(0), pair.getValue(), (x,y)->x+y);
            charCount.merge(pair.getKey().charAt(1), pair.getValue(), (x,y)->x+y);
        }

        //don't count "_" from _X and Y_
        charCount.remove('_');

        long max = charCount.values().stream().max(Long::compareTo).get()/2;
        long min = charCount.values().stream().min(Long::compareTo).get()/2;

        System.out.println(max-min);


    }


}

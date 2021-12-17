package day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Puzzle1 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day14/input.txt"));
        String currentLine;

        StringBuilder template = new StringBuilder(reader.readLine());
        Map<String, String> insertionRules = new HashMap<>();
        while (null != (currentLine = reader.readLine())) {
            if (currentLine.contains("->")){
                StringTokenizer st = new StringTokenizer(currentLine, " ->");
                String reactant = st.nextToken();
                String product = st.nextToken();
                insertionRules.put(reactant, product);
            }
        }

        for (int steps = 0; steps < 10; steps++) {
            int i = 0;
            while (i < template.length()-1) {
                String pattern = template.substring(i, i+2);
                if (insertionRules.keySet().contains(pattern)) {
                    template.insert(i+1, insertionRules.get(pattern));
                    i +=2;
                } else {
                    i++;
                }
            }
        }

        Map<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < template.length(); i++) {
            charCount.merge(template.charAt(i), 1, (x,y)->x+y);
        }

        int max = charCount.values().stream().max(Integer::compareTo).get();
        int min = charCount.values().stream().min(Integer::compareTo).get();

        System.out.println(max-min);


    }


}

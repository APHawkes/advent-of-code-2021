package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Puzzle1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day8/input.txt"));
        String currentLine;
        List<String> signalPatterns = new ArrayList<>();
        List<String> outputValues = new ArrayList<>();
        while ((currentLine = reader.readLine())!= null) {
            StringTokenizer st = new StringTokenizer(currentLine, " |", true);

            boolean reachedPipe = false;
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (" ".equals(token))
                    continue;
                if ("|".equals(token)){
                    reachedPipe = true;
                    continue;
                }
                if (!reachedPipe) {
                    signalPatterns.add(token);
                } else {
                    outputValues.add(token);
                }
            }
        }

        long count = outputValues.stream()
                //correspond to digits 1, 4, 7, 8
                .filter(x->(x.length()==2 || x.length()==4 || x.length()==3 || x.length()==7))
                .count();

        System.out.println(count);
    }


}

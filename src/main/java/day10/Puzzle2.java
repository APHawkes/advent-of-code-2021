package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Puzzle2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day10/input.txt"));
        String currentLine;

        long total = 0;
        Map<Character,Integer> map = Map.of('(',1,'[',2,'{',3,'<',4);
        List<Long> scores = new ArrayList<>();
        skipLine:
        while (null != (currentLine = reader.readLine())) {
            Stack<Character> stack = new Stack<>();
            char[] chars = currentLine.toCharArray();

            long lineScore = 0;
            for (int i = 0; i < chars.length; i++) {
                char currentChar = chars[i];
                if (stack.isEmpty()) {
                    stack.push(currentChar);
                } else {
                    char top = stack.peek();
                    if (currentChar == '{'
                            ||currentChar == '['
                            || currentChar == '('
                            || currentChar == '<') {
                        stack.push(currentChar);
                    } else {
                        //ensure matches
                        if (currentChar == ')' && top != '(')
                            continue skipLine;
                        if (currentChar == ']' && top != '[')
                            continue skipLine;
                        if (currentChar == '}' && top != '{')
                            continue skipLine;
                        if (currentChar == '>' && top != '<')
                            continue skipLine;
                        if (lineScore>0) {
                            continue skipLine;
                        } else {
                            stack.pop();
                        }
                    }
                }
            }
            //remaining items on stack need scoring;
            while (!stack.isEmpty()) {
                char top = stack.pop();
                lineScore = lineScore*5 + map.get(top);
            }
            System.out.println(lineScore);
            scores.add(lineScore);
        }
        scores.sort(Long::compareTo);
        System.out.println(scores.get(scores.size()/2));
    }

}

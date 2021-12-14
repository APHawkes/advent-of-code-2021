package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Puzzle1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day10/input.txt"));
        String currentLine;

        long total = 0;
        while (null != (currentLine = reader.readLine())) {
            Stack<Character> stack = new Stack<>();
            char[] chars = currentLine.toCharArray();

            int lineScore = 0;
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
                            lineScore = 3;
                        if (currentChar == ']' && top != '[')
                            lineScore = 57;
                        if (currentChar == '}' && top != '{')
                            lineScore = 1197;
                        if (currentChar == '>' && top != '<')
                            lineScore = 25137;
                        if (lineScore>0) {
                            break;
                        } else {
                            stack.pop();
                        }
                    }
                }
            }
            System.out.println(lineScore);
            total += lineScore;
        }
        System.out.println(total);
    }

}

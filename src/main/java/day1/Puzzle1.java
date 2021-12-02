package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day1/input.txt"));
        int previousDepth = Integer.parseInt(reader.readLine());
        int count = 0;
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            int currentDepth = Integer.parseInt(currentLine);
            if (currentDepth > previousDepth)
                count++;
            previousDepth = currentDepth;
        }
        System.out.println(count);
    }
}

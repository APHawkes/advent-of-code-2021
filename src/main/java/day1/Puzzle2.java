package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Puzzle2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day1/input.txt"));
        int[] sampleWindow = new int[3];
        //grab first window values
        shift(sampleWindow, Integer.parseInt(reader.readLine()));
        shift(sampleWindow, Integer.parseInt(reader.readLine()));
        shift(sampleWindow, Integer.parseInt(reader.readLine()));

        int count = 0;
        int previousWindowSum = sum(sampleWindow);
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            //update current values
            int currentDepth = Integer.parseInt(currentLine);
            shift(sampleWindow, currentDepth);
            int currentWindowSum = sum(sampleWindow);

            //compare to prev and increment count
            if (previousWindowSum < currentWindowSum) {
                count++;
            }

            //prepare for next iteration
            previousWindowSum = currentWindowSum;
        }
        System.out.println(count);
    }

    private static int sum(int[] window) {
        return Arrays.stream(window).sum();
    }

    private static void shift(int[] window, int newValue) {
        for (int i = 2; i > 0; i--) {
            window[i] = window[i - 1];
        }
        window[0] = newValue;
    }
}

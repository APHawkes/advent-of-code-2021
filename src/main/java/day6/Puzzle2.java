package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle2 {
    private static final int DAYS = 256;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day6/input.txt"));
        String currentLine = reader.readLine();

        long[] pool = new long[9];
        Arrays.stream(currentLine.split(","))
                .map(Integer::parseInt)
                .forEach(x->++pool[x]);

        for (int day = 0; day < DAYS; day++) {
            long zeroCount = pool[0];

            for (int i = 0; i < 8; i++) {
                pool[i] = pool[i+1];
            }

            pool[8] = zeroCount;
            pool[6] += zeroCount;
        }
        System.out.println("Pool size: " + Arrays.stream(pool).sum());

    }
}

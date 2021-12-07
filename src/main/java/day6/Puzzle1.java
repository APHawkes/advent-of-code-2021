package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle1 {
    private static final int DAYS = 80;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day6/input.txt"));
        String currentLine = reader.readLine();

        List<Integer> pool = new ArrayList<>();
        pool.addAll(Arrays.stream(currentLine.split(",")).map(Integer::parseInt).collect(Collectors.toList()));

        for (int day = 0; day < DAYS; day++) {
            for (int i = pool.size() - 1; i >= 0; i--) {
                if (pool.get(i) == 0) {
                    pool.set(i, 6);
                    pool.add(8);
                } else {
                    pool.set(i, pool.get(i)-1);
                }
            }
        }
        System.out.println("Pool size: " + pool.size());

    }
}

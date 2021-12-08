package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day7/input.txt"));
        String currentLine = reader.readLine();

        List<Integer> positions = new ArrayList<>();
        positions.addAll(Arrays.stream(currentLine.split(",")).map(Integer::parseInt).collect(Collectors.toList()));

        var maxPos = positions.stream().max(Integer::compareTo).get();
        int minCost = Integer.MAX_VALUE;

        for (int i = 0; i < maxPos; i++) {
            int cost = calculateCost(positions, i);
            if (cost < minCost) {
                minCost = cost;
            }
        }

        System.out.println("Min Fuel Cost: " + minCost);

    }

    public static int calculateCost(List<Integer> positions, int finalPosition) {
        var cost = positions.stream()
                .map(x-> finalPosition-x)
                .map(Math::abs)
                .map(difference -> {
                    int accumulator = 0;
                    for (int i = 1; i <= difference; i++){
                        accumulator  += i;
                    }
                    return accumulator;
                })
                .reduce((x,y)->x+y);
        return cost.get();
    }
}

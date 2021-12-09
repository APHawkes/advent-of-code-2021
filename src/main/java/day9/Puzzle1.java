package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day9/input.txt"));
        String currentLine;

        //build heightmap
        List<List<Integer>> heightmap = new ArrayList<>();
        while (null != (currentLine = reader.readLine())) {
            heightmap.add(
                currentLine.chars()
                        .mapToObj(c-> (char)c)
                        .map(Character::getNumericValue)
                        .collect(Collectors.toList())
            );
        }
        
        //evaluate all positions
        int riskSum = 0;
        for (int y = 0; y < heightmap.size(); y++) {
            for (int x = 0; x < heightmap.get(y).size(); x++) {
                if (isLowPoint(heightmap, x, y)) {
                    int riskLevel = 1 + heightmap.get(y).get(x);
                    riskSum += riskLevel;
                }
            }
        }
        System.out.println(riskSum);
    }

    public static boolean isLowPoint(List<List<Integer>> heightmap, int x, int y) {
        int currentHeight = heightmap.get(y).get(x);
        //above
        if (y-1 >= 0 && heightmap.get(y-1).get(x) <= currentHeight){
            return false;
        }
        //below
        if (y+1 < heightmap.size() && heightmap.get(y+1).get(x) <= currentHeight) {
            return false;
        }
        //left
        if (x-1 >= 0 && heightmap.get(y).get(x-1) <= currentHeight) {
            return false;
        }
        //right
        if (x+1 < heightmap.get(y).size() && heightmap.get(y).get(x+1) <= currentHeight) {
            return false;
        }
        return true;
    }

}

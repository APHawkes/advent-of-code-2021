package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle2 {
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
        List<Integer> lowXs = new ArrayList<>();
        List<Integer> lowYs = new ArrayList<>();
        for (int y = 0; y < heightmap.size(); y++) {
            for (int x = 0; x < heightmap.get(y).size(); x++) {
                if (isLowPoint(heightmap, x, y)) {
                    lowXs.add(x);
                    lowYs.add(y);
                }
            }
        }

        List<Integer> basinSizes = new ArrayList<>();
        for (int i =0; i < lowYs.size(); i++){
            int basinSize = basinSearch(heightmap, lowXs.get(i), lowYs.get(i));
            basinSizes.add(basinSize);
        }

        basinSizes.sort(Integer::compareTo);
        int basinProduct = 1;
        //get last 3, multiply all
        for (int i = 0; i < 3; i++) {
            basinProduct *= basinSizes.get(basinSizes.size() - 1 - i);
        }

        System.out.println(basinProduct);
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

    public static int basinSearch(List<List<Integer>> heightmap, int x, int y) {
        //terminal cases
        if (x < 0 || y < 0 || y > heightmap.size()-1 || x > heightmap.get(y).size()-1)
            return 0; //beyond ranges
        if (heightmap.get(y).get(x) == 9) //don't count "ridges" as part of a basin
            return 0;
        if (heightmap.get(y).get(x) <0) //already counted
            return 0;

        //flag to <0 to avoid recounts
        heightmap.get(y).set(x, -1);

        int left = basinSearch(heightmap, x-1, y);
        int right = basinSearch(heightmap, x+1, y);
        int up = basinSearch(heightmap, x, y-1);
        int down = basinSearch(heightmap, x, y+1);
        return left + right + up + down + 1; //sum all directions plus count self
    }

}

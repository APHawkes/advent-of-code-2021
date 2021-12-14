package day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class Puzzle1 {
    public static final int GRID_SIZE = 10;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day11/input.txt"));
        String currentLine;

        int[][] octopuses = new int[GRID_SIZE][GRID_SIZE];
        int lineNum = 0;
        while (null != (currentLine = reader.readLine())) {
            char[] chars = currentLine.toCharArray();
            for (int i = 0; i < GRID_SIZE; i++) {
                octopuses[lineNum][i] = Character.getNumericValue(chars[i]);
            }
            lineNum ++;
        }

        //octopus grid built
        //start simulation
        int totalcount = 0;
        for (int step = 1; step < 101; step++) { //1-indexed loop
            //increment all for step
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    octopuses[i][j]++;
                }
            }

            //do flashing
            boolean flashed;
            do {
                flashed = false;
                for (int y = 0; y < GRID_SIZE; y++) {
                    for (int x = 0; x < GRID_SIZE; x++) {
                        if (octopuses[y][x] > 9){
                            //flash
                            octopuses[y][x]=0;
                            flashed = true;
                            //increment neighbors
                            //above
                            if (y-1 >= 0) {
                                if (octopuses[y-1][x] > 0) //mid
                                    octopuses[y-1][x]++;
                                if (x-1 >= 0 && octopuses[y-1][x-1] > 0)//left
                                    octopuses[y-1][x-1]++;
                                if (x+1 < GRID_SIZE && octopuses[y-1][x+1] > 0)//right
                                    octopuses[y-1][x+1]++;
                            }
                            //beside
                            if (x-1 >= 0 && octopuses[y][x-1] > 0)//left
                                octopuses[y][x-1]++;
                            if (x+1 < GRID_SIZE && octopuses[y][x+1] > 0)//right
                                octopuses[y][x+1]++;
                            //below
                            if (y+1 < GRID_SIZE) {
                                if (octopuses[y+1][x] > 0) //mid
                                    octopuses[y+1][x]++;
                                if (x-1 >= 0 && octopuses[y+1][x-1] > 0)//left
                                    octopuses[y+1][x-1]++;
                                if (x+1 < GRID_SIZE && octopuses[y+1][x+1] > 0)//right
                                    octopuses[y+1][x+1]++;
                            }
                        }
                    }
                }
            } while (flashed);

            //Count flashed octopuses
            for (int y = 0; y < GRID_SIZE; y++) {
                for (int x = 0; x < GRID_SIZE; x++) {
                    if (octopuses[y][x] ==0)
                        totalcount++;
                }
            }
            //end step
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    System.out.print(octopuses[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println(totalcount);
    }

}

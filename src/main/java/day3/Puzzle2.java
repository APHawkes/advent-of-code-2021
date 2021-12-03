package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Puzzle2 {
    private static final int WIDTH = 12;
    private static final int ROWS = 1000;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day3/input.txt"));
        String line;
        int result = 0;
        int[] posCount = new int[WIDTH];
        int[] data = new int[ROWS];
        int count = 0;
        //read all data
        while ((line = reader.readLine()) != null) {
            data[count] = Integer.parseInt(line, 2);
            count++;
        }

        int oxygenGenerator = calculateOxygenGenerator(data);
        int co2 = calculateCO2(data);
        System.out.println("oxygen Gen:" + Integer.toBinaryString(oxygenGenerator));
        System.out.println("oxygen Gen:" + oxygenGenerator);
        System.out.println("co2 scrubb:" + Integer.toBinaryString(co2));
        System.out.println("co2 scrubb:" + co2);
        System.out.println(oxygenGenerator * co2);
    }

    private static int calculateOxygenGenerator(int[] data) {
        int mask = 0;
        int filter = 0;
        for (int i = WIDTH; i >= 0; i--) {
            int finalMask = mask;
            int finalFilter = filter;
            int[] filteredData = Arrays.stream(data).filter(x -> (x & finalMask) == finalFilter).toArray();
            if (filteredData.length == 1){
                filter = filteredData[0];
                break;
            }
            //How many 1s in position i with existing mask/filter?
            int positionMask = 1 << (i-1);
            long posSum = Arrays.stream(filteredData).filter(x -> (x & positionMask) > 0).count();

            if (posSum*2 >= filteredData.length) {
                //Position i most common bit is 1
                filter = filter | positionMask;
            } else {
                //Position i least common bit is 1

            }
            mask = mask | positionMask;
        }
        return filter;
    }

    private static int calculateCO2(int[] data) {
        int mask = 0;
        int filter = 0;
        for (int i = WIDTH; i >= 0; i--) {
            int finalMask = mask;
            int finalFilter = filter;
            int[] filteredData = Arrays.stream(data).filter(x -> (x & finalMask) == finalFilter).toArray();
            if (filteredData.length == 1){
                filter = filteredData[0];
                break;
            }
            //How many 1s in position i with existing mask/filter?
            int positionMask = 1 << (i-1);
            long posSum = Arrays.stream(filteredData).filter(x -> (x & positionMask) > 0).count();

            if (posSum*2 >= filteredData.length) {
                //Position i most common bit is 1

            } else {
                //Position i least common bit is 1
                filter = filter | positionMask;
            }
            mask = mask | positionMask;
        }
        return filter;
    }

    private static long countOnesInPosition(int[] data, int mask, int filter, int pos) {
        long posSum = Arrays.stream(data)
                .filter(x -> (x & mask) == filter)
                .filter(x -> (x & 1 << (pos-1)) > 0)
                .count();
        return posSum;
    }

    private static long countFiltered(int[] data, int mask, int filter) {
        long posSum = Arrays.stream(data)
                .filter(x -> (x & mask) == filter)
                .count();
        return posSum;
    }
}

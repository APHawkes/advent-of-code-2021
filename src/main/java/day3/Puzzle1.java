package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Puzzle1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day3/input.txt"));
        String line;
        int[] posCount = new int[12];
        int count = 0;
        int gamma=0;//most common bit in the corresponding position
        int epsilon;
        while ((line = reader.readLine()) != null) {
            count++;
            char[] chars = line.toCharArray();
            for (int i = 0; i < posCount.length; i++){
                if (chars[i] == '1') {
                    posCount[i] += 1;
                }
            }
        }

        for (int i = 0; i < posCount.length; i++) {
            gamma = gamma<<0x1;
            if (posCount[i] > (count/2)) {
                gamma = gamma|1;
            }
        }
        epsilon = ~gamma;
        epsilon = epsilon & 0x00000FFF;

        System.out.println("Count: " + count);
        Arrays.stream(posCount).forEach(x->System.out.println(x));
        System.out.println(Integer.toBinaryString(gamma));
        System.out.println(Integer.toBinaryString(epsilon));
        System.out.println(gamma*epsilon);
    }
}

package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Puzzle1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day2/input.txt"));
        String command;
        int depth = 0;
        int distance = 0;
        while ((command = reader.readLine()) != null){
            StringTokenizer stringTokenizer = new StringTokenizer(command, " ");
            String direction = stringTokenizer.nextToken();
            int value = Integer.parseInt(stringTokenizer.nextToken());
            switch (direction) {
                case "forward":
                    distance += value;
                    break;
                case "up":
                    depth -= value;
                    break;
                case "down":
                    depth += value;
                    break;
                default:
                    System.out.println("ERROR! Unknown direction " + direction);
                    break;
            }
        }
        System.out.println(depth*distance);
    }
}

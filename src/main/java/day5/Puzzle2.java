package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Puzzle2 {
    private static final int GRID_WIDTH = 1000;
    private static final int GRID_HEIGHT = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day5/input.txt"));
        String currentLine;

        int[] oceanFloor = new int[GRID_HEIGHT * GRID_WIDTH];

        while ((currentLine = reader.readLine()) != null) {
            StringTokenizer stOuter = new StringTokenizer(currentLine, "-> ");
            StringTokenizer stFirst = new StringTokenizer(stOuter.nextToken(), ", ");
            StringTokenizer stSecond = new StringTokenizer(stOuter.nextToken(), ", ");
            Pos firstPos = new Pos(Integer.parseInt(stFirst.nextToken()), Integer.parseInt(stFirst.nextToken()));
            Pos secondPos = new Pos(Integer.parseInt(stSecond.nextToken()), Integer.parseInt(stSecond.nextToken()));

            //Mark vertical
            if (firstPos.x == secondPos.x) {
                int lower = Math.min(firstPos.y, secondPos.y);
                int upper = Math.max(firstPos.y, secondPos.y);
                for(int i = lower; i < upper+1; i++ ){
                    int index = firstPos.x + i*GRID_WIDTH;
                    oceanFloor[index]++;
                }
            }

            //Mark horizonal
            if (firstPos.y == secondPos.y) {
                int lower = Math.min(firstPos.x, secondPos.x);
                int upper = Math.max(firstPos.x, secondPos.x);
                for(int i = lower; i < upper+1; i++ ){
                    int index = i + firstPos.y*GRID_WIDTH;
                    oceanFloor[index]++;
                }
            }

            //Mark diagonal
            if (Math.abs(firstPos.y - secondPos.y) == Math.abs(firstPos.x - secondPos.x)) {
                int left = Math.min(firstPos.x, secondPos.x);
                int right = Math.max(firstPos.x, secondPos.x);
                int top = Math.min(firstPos.y, secondPos.y);
                int bottom = Math.max(firstPos.y, secondPos.y);
                int increment = 0;
                int startingY = 0;
                if (firstPos.x == left) {
                    //first is left
                    if (firstPos.y == top) {
                        increment = 1; //y increases with X
                        startingY = top;
                    } else {
                        increment = -1; //y decreases with X
                        startingY = bottom;
                    }
                } else {
                    //second is left
                    if (secondPos.y == top) {
                        increment = 1; // y increases with X
                        startingY = top;
                    } else {
                        increment = -1; //y decreases with X
                        startingY = bottom;
                    }
                }

                for(int i = 0; i < (right+1)-left; i++ ){
                    int markX = i + left;
                    int markY = (startingY + i*increment);
                    int index = markX + markY*GRID_WIDTH;
                    oceanFloor[index]++;
                }
            }

        }
        //Calculate result
//        Arrays.stream(oceanFloor).forEachOrdered(x->System.out.print(x));
        System.out.println(Arrays.stream(oceanFloor).filter(x->x>1).count());
    }

    public static class Pos {
        public int x;
        public int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{" + x + "," + y +'}';
        }
    }
}

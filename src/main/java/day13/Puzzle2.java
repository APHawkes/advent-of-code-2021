package day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Puzzle2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day13/input.txt"));
        String currentLine;

        ArrayList<Pos> transparency = new ArrayList<>();
        while (null != (currentLine = reader.readLine())) {
            if (currentLine.contains(",")){
                StringTokenizer st = new StringTokenizer(currentLine, ",");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                transparency.add(new Pos(x,y));
            } else if (currentLine.startsWith("fold along y=")) {
                //iterate over transparency, moving everything with y > ? to mirrored side up
                int axis = Integer.parseInt(currentLine,13,currentLine.length(), 10);
                for (Pos pos: transparency) {
                    if (pos.y > axis) {
                        int newY = axis - (pos.y - axis);
                        if (!transparency.contains(new Pos(pos.x, newY))){
                            pos.y = newY;
                        } else {
                            pos.y = Integer.MIN_VALUE;
                        }
                    }
                }
            } else if (currentLine.startsWith("fold along x=")){
                //iterate over transparency, moving everything with x > ? to mirrored side left
                int axis = Integer.parseInt(currentLine,13,currentLine.length(), 10);
                for (Pos pos: transparency) {
                    if (pos.x > axis) {
                        int newX = axis - (pos.x - axis);
                        if (!transparency.contains(new Pos(newX, pos.y))){
                            pos.x = newX;
                        } else {
                            pos.x = Integer.MIN_VALUE;
                        }
                    }
                }
            }
        }

        //Display?
        String[][] display = new String[100][100];
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                display[y][x]=" ";
            }
        }
        for (Pos pos : transparency) {
            if (pos.y >=0 && pos.x >=0)
                display[pos.y][pos.x] = "#";
        }

        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                System.out.print(display[y][x]);
            }
            System.out.println();
        }


    }

    public static class Pos {
        public int x;
        public int y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            return this.x == ((Pos)obj).x && this.y == ((Pos)obj).y;
        }
    }
}

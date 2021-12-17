package day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Puzzle1 {

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
                break;
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
                break;
            }
        }

        //count positions
        long count = transparency.stream()
                .filter(pos -> pos.x >=0)
                .filter(pos -> pos.y >=0)
                .count();
        System.out.println(count);


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

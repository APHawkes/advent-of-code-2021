package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import static java.lang.System.exit;

public class Puzzle2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day4/input.txt"));
        String line = reader.readLine();

        StringTokenizer draws = new StringTokenizer(line, ",");

        //build boards
        List<List<BoardCell>> boards = new ArrayList<>();
        String boardString = "";
        while ((line = reader.readLine()) != null) {
            if ("".equals(line)) {
                if (!"".equals(boardString)) {
                    //new board complete
                    addBoard(boardString, (List<List<BoardCell>>) boards);
                    boardString = "";
                }
                continue;
            }
            boardString += " " + line;
        }
        if (boardString != ""){
            //new board complete
            addBoard(boardString, boards);
        }

        List<BoardCell> winningBoard = null;
        //Mark boards
        while (draws.hasMoreTokens()) {
            int draw = Integer.parseInt(draws.nextToken());
            //mark all boards with matching cell value
            for (List<BoardCell> board : boards) {
                for(BoardCell cell : board) {
                    if (cell.value == draw){
                        cell.marked = true;
                    }
                }
            }


            for (int boardIndex = 0 ; boardIndex < boards.size(); boardIndex++) {
                var board = boards.get(boardIndex);
                //check for win on rows
                CurrentBoardCheckRows:
                for(int i = 0; i < board.size(); i=i+5 ) {
                    int markedCells = 0;
                    //Var i is first element in row
                    for (int j = 0; j < 5; j++) {
                        if (board.get(i+j).marked){
                            markedCells++;
                        }
                    }
                    if (markedCells == 5) {
                        System.out.println("Win on board (row) : " + board);
                        winningBoard = board;
                        break CurrentBoardCheckRows;
                    }
                }
                //Check for wins on columns
                CurrentBoardCheckCols:
                for(int i = 0; i < 5; i++ ) {
                    int markedCells = 0;
                    //Var i is first element in row
                    for (int j = 0; j < 5; j++) {
                        if (board.get(i+j*5).marked){
                            markedCells++;
                        }
                    }
                    if (markedCells == 5) {
                        System.out.println("Win on board (col) : " + board);
                        winningBoard = board;
                        break CurrentBoardCheckCols;
                    }
                }
                if (winningBoard != null) {
                    //Remove winning puzzle from list
                    boards.remove(winningBoard);
                    //if only last board won
                    if (boards.size() == 0){
                        //Calculate value
                        Optional<Integer> sumUnmarked = winningBoard.stream().filter(x-> !x.marked).map(x->x.value).reduce((x, y)->x+y);
                        System.out.println("Result: " + sumUnmarked.get() * draw);
                        exit(0);
                    }
                    winningBoard = null;

                }

            }

        }

    }

    private static void addBoard(String boardString, List<List<BoardCell>> boards) {
        List<BoardCell> cells = new ArrayList<>(25);
        StringTokenizer st = new StringTokenizer(boardString, " ");
        st.asIterator().forEachRemaining(x -> cells.add(new BoardCell(Integer.parseInt(x.toString()), false)));
        boards.add(cells);
    }

    public static class BoardCell {
        public int value;
        public boolean marked;
        BoardCell(int value, boolean marked) {
            this.value = value;
            this.marked = marked;
        }

        @Override
        public String toString() {
            return "{" +
                    "" + value +
                    "," + marked +
                    '}';
        }
    }
}

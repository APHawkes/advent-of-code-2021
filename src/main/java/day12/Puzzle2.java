package day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Puzzle2 {
    public static final int GRID_SIZE = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day12/input.txt"));
        String currentLine;

        //build map
        Map<String, List<String>> caveMap = new HashMap<>();
        while (null != (currentLine = reader.readLine())) {
            StringTokenizer st = new StringTokenizer(currentLine, "-");
            String cave1 = st.nextToken();
            String cave2 = st.nextToken();
            caveMap.merge(cave1,
                    new ArrayList<>(List.of(cave2)), (x, y) -> {
                        x.add(cave2);
                        return x;
                    });
            caveMap.merge(cave2,
                    new ArrayList<>(List.of(cave1)), (x, y) -> {
                        x.add(cave1);
                        return x;
                    });
        }

        //Show map
//        caveMap.entrySet().stream().forEach(x->System.out.println(x));

        // explore options
        var paths = explore(caveMap, List.of("start"));
        System.out.println(paths.size());
    }

    public static List<String> visitChoices(Map<String, List<String>> map, List<String> history) {
        String currentCave = history.get(history.size() - 1);
        if (currentCave.equals("end")) {
            return List.of();
        }

        List<String> possibilities = map.get(currentCave);
        return possibilities.stream()
                //start is a special case and can only be visited initially
                .filter(x -> !x.equals("start"))
                //double-visit allowed only once
                .filter(x -> x.equals(x.toUpperCase())
                        || (hasDoubleVisit(history) && !history.contains(x))
                        || (!hasDoubleVisit(history)))
                .collect(Collectors.toList());
    }

    public static List<List<String>> explore(Map<String, List<String>> map, List<String> history) {
        List<List<String>> paths = new ArrayList<>();
        for (String option : visitChoices(map, history)) {
            List<String> path = new ArrayList<>(history);
            path.add(option);
            if ("end".equals(option))
                paths.add(path);
            else {
                for (List<String> fullPath : explore(map, path)) {
                    paths.add(fullPath);
                }
            }
        }
        return paths;
    }

    public static boolean hasDoubleVisit(List<String> history) {
        for (int i = 0; i < history.size(); i++) {
            String currCave = history.get(i);
            if (currCave.equals(currCave.toUpperCase())) //ignore double-visit to upper-case caves
                continue;
            if (history.lastIndexOf(currCave) != i) //first and last index !=
                return true;
        }
        return false;
    }
}
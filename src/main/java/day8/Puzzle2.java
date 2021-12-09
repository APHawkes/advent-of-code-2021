package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Puzzle2 {
    private static final char[] ALL_SIG = new char[]{'a','b','c','d','e','f'};
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/day8/input.txt"));
        String currentLine;
        int sum = 0;
        while ((currentLine = reader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(currentLine, " |", true);
            List<String> signalPatterns = new ArrayList<>();
            List<String> outputValues = new ArrayList<>();
            boolean reachedPipe = false;
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (" ".equals(token))
                    continue;
                if ("|".equals(token)) {
                    reachedPipe = true;
                    continue;
                }
                if (!reachedPipe) {
                    signalPatterns.add(token);
                } else {
                    outputValues.add(token);
                }
            }
            sum += processLine(signalPatterns, outputValues);
        }
        System.out.println(sum);
    }

    private static int processLine(List<String> signalPatterns, List<String> outputValues) {
        // Prepare candidate map
        String[] sigs = new String[10];

        //Eliminate knowns for 1,4,7 (8 is useless)
        var oneSig = signalPatterns.stream().filter(x -> x.length() == 2).findFirst().get().toCharArray();
        Arrays.sort(oneSig);
        sigs[1] = String.valueOf(oneSig);

        var sevenSig = signalPatterns.stream().filter(x -> x.length() == 3).findFirst().get().toCharArray();
        Arrays.sort(sevenSig);
        sigs[7] = String.valueOf(sevenSig);

        var fourSig = signalPatterns.stream().filter(x -> x.length() == 4).findFirst().get().toCharArray();
        Arrays.sort(fourSig);
        sigs[4] = String.valueOf(fourSig);

        //Try to find 0, 6, 9 based on comparing 6-char sequences
        Set<String> sixCharSigs = signalPatterns.stream().filter(x->x.length()==6).collect(Collectors.toSet());
        for (String sig : sixCharSigs) {
            // "6" digit does not contain all segments of 1, but "9" and "0" do!
            if (!stringContainsAllChars(sig, oneSig)) {
                char[] sigCharArray = sig.toCharArray();
                Arrays.sort(sigCharArray);
                sigs[6] = String.valueOf(sigCharArray);
                continue;
            }
            // "9" digit contains all segments of "4", but not "6" and "0"
            if (stringContainsAllChars(sig, fourSig)) {
                char[] sigCharArray = sig.toCharArray();
                Arrays.sort(sigCharArray);
                sigs[9] = String.valueOf(sigCharArray);
                continue;
            }
            // "0" is the only remaining number
            char[] sigCharArray = sig.toCharArray();
            Arrays.sort(sigCharArray);
            sigs[0] = String.valueOf(sigCharArray);
        }

        //Try to find 2, 3, 5 based on comparing 5-char sequences
        Set<String> fiveCharSigs = signalPatterns.stream().filter(x->x.length()==5).collect(Collectors.toSet());
        for (String sig: fiveCharSigs){
            //"3" contains all from "1" but not "2" and "5"
            if (stringContainsAllChars(sig, oneSig)) {
                char[] sigCharArray = sig.toCharArray();
                Arrays.sort(sigCharArray);
                sigs[3] = String.valueOf(sigCharArray);
                continue;
            }
            //"5" contains all the same as "6", but not "2" and "3"
            if (stringContainsAllChars(sigs[6], sig.toCharArray())) {
                char[] sigCharArray = sig.toCharArray();
                Arrays.sort(sigCharArray);
                sigs[5] = String.valueOf(sigCharArray);
                continue;
            }
            //"2" remains
            char[] sigCharArray = sig.toCharArray();
            Arrays.sort(sigCharArray);
            sigs[2] = String.valueOf(sigCharArray);
            continue;
        }

        //"8" has all 7 segments
        var eightSig = signalPatterns.stream().filter(x -> x.length() == 7).findFirst().get().toCharArray();
        Arrays.sort(eightSig);
        sigs[8] = String.valueOf(eightSig);

        //Signals interpreted! Now figure out values
        int accumulator = 0;
        for (String value: outputValues) {
            accumulator *=10;
            char[] charValue = value.toCharArray();
            Arrays.sort(charValue);
            String normalizedValue = String.valueOf(charValue);
            for (int i = 0; i < sigs.length; i++) {
                if (normalizedValue.equals(sigs[i])){
                    accumulator += i;
                }
            }
        }
        return accumulator;
    }

    public static boolean stringContainsAllChars(String sig, char[] chars){
        for (int i = 0; i < chars.length; i++) {
            if (!sig.contains(String.valueOf(chars[i])))
                return false;
        }
        return true;
    }
}

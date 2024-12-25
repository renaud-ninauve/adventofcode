package fr.ninauve.renaud.adventofcode.year2024.day25;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Part01 {

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part01.class
                .getResource("/year2024/day25/input.txt").toURI()), StandardCharsets.UTF_8);
        System.out.println(Part01.solve(input));
    }

    public static long solve(List<String> input) {
        Input parsed = parse(input);

        Map<Integer, Map<Integer, Set<List<Integer>>>> locksHeightsByCol = new HashMap<>();
        Map<Integer, Map<Integer, Set<List<Integer>>>> keysHeightsByCol = new HashMap<>();
        for (int col = 0; col < 5; col++) {
            locksHeightsByCol.put(col, heightsOfColumn(parsed.locks(), col));
            keysHeightsByCol.put(col, heightsOfColumn(parsed.keys(), col));
        }
        long result = 0;
        for (List<Integer> key : parsed.keys()) {
            Set<List<Integer>> matchingLocks = null;
            for (int col = 0; col < 5; col++) {
                Integer keyHeight = key.get(col);
                Map<Integer, Set<List<Integer>>> locksHeights = locksHeightsByCol.get(col);
                Set<List<Integer>> matchingLocksForCol = new HashSet<>();
                for (int height = 0; height < 5 - keyHeight + 1; height++) {
                    matchingLocksForCol.addAll(locksHeights.getOrDefault(height, Set.of()));
                }
                if (col == 0) {
                    matchingLocks = new HashSet<>(matchingLocksForCol);
                } else {
                    matchingLocks.retainAll(matchingLocksForCol);
                }
            }
            result += matchingLocks.size();
        }
        return result;
    }

    private static Map<Integer, Set<List<Integer>>> heightsOfColumn(List<List<Integer>> heights, int col) {
        return heights.stream()
                .collect(Collectors.groupingBy(h -> h.get(col), Collectors.toSet()));
    }

    public static Input parse(List<String> input) {
        final List<List<Integer>> locks = new ArrayList<>();
        final List<List<Integer>> keys = new ArrayList<>();

        int line = 0;
        while (line < input.size()) {
            boolean isKey = input.get(line).matches("\\.+");
            char symbol = '#';
            List<Integer> current = new ArrayList<>();
            for (int col = 0; col < 5; col++) {
                int height = 0;
                for (int row = 1; row < 6; row++) {
                    char c = input.get(line + row).charAt(col);
                    if (c == symbol) {
                        height++;
                    }
                }
                current.add(height);
            }
            if (isKey) {
                keys.add(current);
            } else {
                locks.add(current);
            }
            line += 8;
        }
        return new Input(locks, keys);
    }

    public record Input(List<List<Integer>> locks, List<List<Integer>> keys) {
    }

    public record ColumnHeight(int column, int height) {
    }
}

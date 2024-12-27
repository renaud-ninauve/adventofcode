package fr.ninauve.renaud.adventofcode.year2024.day18;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Part02 {

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part02.class
                .getResource("/year2024/day18/input.txt").toURI()), StandardCharsets.UTF_8);
        System.out.println(Part02.solve(input, 71, 71, 1024));
    }

    public static String solve(List<String> input, int nbRows, int nbCols, int time) {
        for(int i=time; i<input.size(); i++) {
            List<Cell> path = Part01.findPath(input, nbRows, nbCols, i);
            if (path == null) {
                return input.get(i - 1);
            }
        }
        return "not found";
    }
}

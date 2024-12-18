package fr.ninauve.renaud.adventofcode.year2024.day16;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part02 {

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part02.class
                .getResource("/year2024/day16/input.txt").toURI()), StandardCharsets.UTF_8);
        System.out.println(Part02.solve(input));
    }

    public static long solve(List<String> input) {
        return 0L;
//        List<Action> minActions = Part01.findMinActions(input);
//        return minActions.stream().filter(a -> a == Action.MOVE_STRAIGHT)
//                .count();
    }
}

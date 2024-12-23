package fr.ninauve.renaud.adventofcode.year2024.day23;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part02 {

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part02.class
                .getResource("/year2024/day23/input.txt").toURI()), StandardCharsets.UTF_8);
        System.out.println(Part02.solve(input));
    }

    public static String solve(List<String> input) {
        return maxConnected(input)
                .stream()
                .sorted()
                .collect(Collectors.joining(","));
    }

    public static Set<String> maxConnected(List<String> input) {
        Map<String, Set<String>> links = parse(input);
        var largestConnectedComputersSet = Set.<String>of();
        for (Map.Entry<String, Set<String>> computerConnections : links.entrySet()) {
            var connectedComputersSet = new HashSet<String>();
            connectedComputersSet.add(computerConnections.getKey());
            for (String connection : computerConnections.getValue()) {
                if (links.get(connection).containsAll(connectedComputersSet)) {
                    connectedComputersSet.add(connection);
                }
            }
            if (connectedComputersSet.size() > largestConnectedComputersSet.size()) {
                largestConnectedComputersSet = connectedComputersSet;
            }
        }
        return largestConnectedComputersSet;
    }

    public static Map<String, Set<String>> parse(List<String> input) {
        return input.stream()
                .map(line -> line.split("-"))
                .flatMap(split -> Stream.of(split, new String[]{split[1], split[0]}))
                .collect(Collectors.groupingBy(split -> split[0], Collectors.mapping(split -> split[1], Collectors.toSet())));
    }
}

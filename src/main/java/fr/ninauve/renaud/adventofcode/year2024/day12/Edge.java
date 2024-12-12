package fr.ninauve.renaud.adventofcode.year2024.day12;

import java.util.List;
import java.util.stream.Stream;

public record Edge(Coordinates a, Coordinates b) {
    public static Edge edge(Coordinates a, Coordinates b) {
        List<Coordinates> sorted = Stream.of(a, b)
                .sorted(Coordinates.READING_ORDER)
                .toList();
        return new Edge(sorted.getFirst(), sorted.getLast());
    }
}

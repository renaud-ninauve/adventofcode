package fr.ninauve.renaud.adventofcode.year2023.day03;

import java.util.List;
import java.util.stream.IntStream;

public record ColumnsGroup(int row, int start, int end) {
    public List<Coordinates> coordinates() {
        return IntStream.rangeClosed(start, end)
                .mapToObj(col -> new Coordinates(row, col))
                .toList();
    }

    public List<Coordinates> neighbours() {
        return coordinates().stream()
                .map(Coordinates::neighbours)
                .flatMap(List::stream)
                .toList();
    }
}

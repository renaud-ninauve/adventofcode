package fr.ninauve.renaud.adventofcode.year2023.day03;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record ColumnsGroup(int row, int start, int end) {
    public List<Coordinates> coordinates() {
        return IntStream.rangeClosed(start, end)
                .mapToObj(col -> new Coordinates(row, col))
                .toList();
    }

    public List<Coordinates> neighbours() {
        Coordinates first = new Coordinates(row, start);
        Coordinates last = new Coordinates(row, end);
        Coordinates left = first.moveOf(Coordinates.LEFT);
        Coordinates right = last.moveOf(Coordinates.RIGHT);

        return Stream.concat(
                Stream.of(left, right),
                IntStream.rangeClosed(start - 1, end + 1)
                        .mapToObj(col -> List.of(new Coordinates(row - 1, col), new Coordinates(row + 1, col)))
                        .flatMap(List::stream)
        ).toList();
    }
}

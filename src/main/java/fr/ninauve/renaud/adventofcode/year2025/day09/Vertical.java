package fr.ninauve.renaud.adventofcode.year2025.day09;

import java.util.Comparator;
import java.util.stream.Stream;

public record Vertical(Point start, Point end) implements Line {
    public Vertical(Point start, Point end) {
        if (!start.col().equals(end.col())) {
            throw new IllegalArgumentException("not a vertical");
        }
        Col col = start.col();
        Row minRow = Stream.of(start, end).map(Point::row).min(Comparator.naturalOrder()).get();
        Row maxRow = Stream.of(start, end).map(Point::row).max(Comparator.naturalOrder()).get();
        this.start = new Point(col, minRow);
        this.end = new Point(col, maxRow);
    }

    public Col col() {
        return start.col();
    }

    @Override
    public Stream<Point> endPoints() {
        return Stream.of(start, end);
    }
}

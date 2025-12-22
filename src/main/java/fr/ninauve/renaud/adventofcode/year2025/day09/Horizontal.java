package fr.ninauve.renaud.adventofcode.year2025.day09;

import java.util.Comparator;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public record Horizontal(Point start, Point end) implements Line {
    public Horizontal(Point start, Point end) {
        if (!start.row().equals(end.row())) {
            throw new IllegalArgumentException("not an horizontal");
        }
        Row row = start.row();
        Col minCol = Stream.of(start, end).map(Point::col).min(Comparator.naturalOrder()).get();
        Col maxCol = Stream.of(start, end).map(Point::col).max(Comparator.naturalOrder()).get();
        this.start = new Point(minCol, row);
        this.end = new Point(maxCol, row);
    }

    public Row row() {
        return start.row();
    }

    public Horizontal moveAtRow(Row newRow) {
        return new Horizontal(new Point(start.col(), newRow), new Point(end.col(), newRow));
    }

    public Stream<Point> allPoints() {
        return LongStream.rangeClosed(start.col().value(), end.col().value())
                .mapToObj(Col::new)
                .map(col -> new Point(col, row()));
    }

    @Override
    public Stream<Point> endPoints() {
        return Stream.of(start, end);
    }
}

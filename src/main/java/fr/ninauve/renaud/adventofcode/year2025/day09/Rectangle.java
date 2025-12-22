package fr.ninauve.renaud.adventofcode.year2025.day09;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Comparator.naturalOrder;

public record Rectangle(Point corner1, Point corner2) {
    public Rectangle(Point corner1, Point corner2) {
        Col minCol = Stream.of(corner1, corner2).map(Point::col).min(naturalOrder()).get();
        Col maxCol = Stream.of(corner1, corner2).map(Point::col).max(naturalOrder()).get();
        Row minRow = Stream.of(corner1, corner2).map(Point::row).min(naturalOrder()).get();
        Row maxRow = Stream.of(corner1, corner2).map(Point::row).max(naturalOrder()).get();
        this.corner1 = new Point(minRow, minCol);
        this.corner2 = new Point(maxRow, maxCol);
    }

    public long width() {
        return distance(corner1.col().value(), corner2.col().value()) + 1;
    }

    public long height() {
        return distance(corner1.row().value(), corner2.row().value()) + 1;
    }

    public long area() {
        return width() * height();
    }

    public Stream<Horizontal> horizontals() {
        return LongStream.rangeClosed(topLeft().row().value(), bottomRight().row().value())
                .mapToObj(Row::new)
                .map(r ->
                        new Horizontal(new Point(topLeft().col(), r),
                                new Point(bottomRight().col(), r)));
    }

    public Point topLeft() {
        Col minCol = Stream.of(corner1, corner2).map(Point::col).min(naturalOrder()).get();
        Row minRow = Stream.of(corner1, corner2).map(Point::row).min(naturalOrder()).get();
        return new Point(minRow, minCol);
    }

    public Point bottomRight() {
        Col maxCol = Stream.of(corner1, corner2).map(Point::col).max(naturalOrder()).get();
        Row maxRow = Stream.of(corner1, corner2).map(Point::row).max(naturalOrder()).get();
        return new Point(maxRow, maxCol);
    }

    private long distance(long a, long b) {
        long max = Math.max(a, b);
        long min = Math.min(a, b);
        return max - min;
    }
}

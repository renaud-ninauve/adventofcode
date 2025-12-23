package fr.ninauve.renaud.adventofcode.year2025.day09;

import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public record Point(Row row, Col col) {
    public Point(Col col, Row row) {
        this(row, col);
    }

    public List<Point> upDownLeftRight(Row maxRow, Col maxCol) {
        Row up = new Row(row().value() - 1);
        Row down = new Row(row().value() + 1);
        Col left = new Col(col().value() - 1);
        Col right = new Col(col().value() + 1);

        return Stream.of(
                        new Point(up, col()),
                        new Point(down, col()),
                        new Point(row(), left),
                        new Point(row(), right)
                ).filter(p -> isValid(maxRow, maxCol))
                .toList();
    }

    public List<Point> neighbours(Row maxRow, Col maxCol) {
        return LongStream.rangeClosed(row().value() - 1, row().value() + 1)
                .mapToObj(Row::new)
                .flatMap(r -> LongStream.rangeClosed(col().value() - 1, col().value() + 1)
                        .mapToObj(Col::new)
                        .map(c -> new Point(r, c)))
                .filter(p -> !p.equals(this))
                .filter(p -> isValid(maxRow, maxCol))
                .toList();
    }

    private boolean isValid(Row maxRow, Col maxCol) {
        long rowValue = row.value();
        long colValue = col.value();
        return rowValue >= 0 && rowValue <= maxRow.value()
                && colValue >= 0 && colValue <= maxCol.value();
    }
}
package fr.ninauve.renaud.adventofcode.year2024.day16;

import lombok.Builder;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Data
@Builder
public class Cell {
    public static Comparator<Cell> READING_ORDER = Comparator.comparing(Cell::getRow).thenComparing(Cell::getCol);

    public static final Cell UP = Cell.builder()
            .row(-1)
            .col(0)
            .build();
    public static final Cell UP_RIGHT = Cell.builder()
            .row(-1)
            .col(1)
            .build();
    public static final Cell RIGHT = Cell.builder()
            .row(0)
            .col(1)
            .build();
    public static final Cell DOWN_RIGHT = Cell.builder()
            .row(1)
            .col(1)
            .build();
    public static final Cell DOWN = Cell.builder()
            .row(1)
            .col(0)
            .build();
    public static final Cell DOWN_LEFT = Cell.builder()
            .row(1)
            .col(-1)
            .build();
    public static final Cell LEFT = Cell.builder()
            .row(0)
            .col(-1)
            .build();
    public static final Cell UP_LEFT = Cell.builder()
            .row(-1)
            .col(-1)
            .build();

    private final int row;
    private final int col;

    public Cell moveOf(Cell delta) {
        return new Cell(row + delta.getRow(), col + delta.getCol());
    }

    public Cell delta(Cell target) {
        return target.moveOf(this.multiply(-1));
    }

    public Cell multiply(int value) {
        return new Cell(row * value, col * value);
    }

    public List<Cell> neighbours() {
        return Stream.of(
                        Cell.UP,
                        Cell.RIGHT,
                        Cell.DOWN,
                        Cell.LEFT)
                .map(this::moveOf)
                .toList();
    }
}

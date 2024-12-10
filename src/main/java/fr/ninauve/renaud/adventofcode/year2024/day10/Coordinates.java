package fr.ninauve.renaud.adventofcode.year2024.day10;

import lombok.Builder;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Data
@Builder
public class Coordinates {
    public static Comparator<Coordinates> READING_ORDER = Comparator.comparing(Coordinates::getRow).thenComparing(Coordinates::getCol);

    public static final Coordinates UP = Coordinates.builder()
            .row(-1)
            .col(0)
            .build();
    public static final Coordinates RIGHT = Coordinates.builder()
            .row(0)
            .col(1)
            .build();
    public static final Coordinates DOWN = Coordinates.builder()
            .row(1)
            .col(0)
            .build();
    public static final Coordinates LEFT = Coordinates.builder()
            .row(0)
            .col(-1)
            .build();

    private final int row;
    private final int col;

    public Coordinates moveOf(Coordinates delta) {
        return new Coordinates(row + delta.getRow(), col + delta.getCol());
    }

    public Coordinates delta(Coordinates target) {
        return target.moveOf(this.multiply(-1));
    }

    public Coordinates multiply(int value) {
        return new Coordinates(row * value, col * value);
    }

    public List<Coordinates> neighbours() {
        return Stream.of(Coordinates.UP, Coordinates.RIGHT, Coordinates.DOWN, Coordinates.LEFT)
                .map(this::moveOf)
                .toList();
    }
}

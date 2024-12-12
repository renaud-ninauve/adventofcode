package fr.ninauve.renaud.adventofcode.year2024.day12;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fr.ninauve.renaud.adventofcode.year2024.day12.Edge.edge;

@Data
@Builder
public class Coordinates {
    public static Comparator<Coordinates> READING_ORDER = Comparator.comparing(Coordinates::getRow).thenComparing(Coordinates::getCol);

    public static final Coordinates UP = Coordinates.builder()
            .row(-1)
            .col(0)
            .build();
    public static final Coordinates UP_RIGHT = Coordinates.builder()
            .row(-1)
            .col(1)
            .build();
    public static final Coordinates RIGHT = Coordinates.builder()
            .row(0)
            .col(1)
            .build();
    public static final Coordinates DOWN_RIGHT = Coordinates.builder()
            .row(1)
            .col(1)
            .build();
    public static final Coordinates DOWN = Coordinates.builder()
            .row(1)
            .col(0)
            .build();
    public static final Coordinates DOWN_LEFT = Coordinates.builder()
            .row(1)
            .col(-1)
            .build();
    public static final Coordinates LEFT = Coordinates.builder()
            .row(0)
            .col(-1)
            .build();
    public static final Coordinates UP_LEFT = Coordinates.builder()
            .row(-1)
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
        return Stream.of(
                        Coordinates.UP,
                        Coordinates.RIGHT,
                        Coordinates.DOWN,
                        Coordinates.LEFT)
                .map(this::moveOf)
                .toList();
    }

    public List<Coordinates> neighboursWithDiagonal() {
        return Stream.of(
                        Coordinates.UP,
                        Coordinates.UP_RIGHT,
                        Coordinates.RIGHT,
                        Coordinates.DOWN_RIGHT,
                        Coordinates.DOWN,
                        Coordinates.DOWN_LEFT,
                        Coordinates.LEFT,
                        Coordinates.UP_LEFT)
                .map(this::moveOf)
                .toList();
    }

    public Collection<Edge> edges() {
        Coordinates topLeft = this;
        Coordinates topRight = moveOf(Coordinates.RIGHT);
        Coordinates downRight = topRight.moveOf(Coordinates.DOWN);
        Coordinates downLeft = topLeft.moveOf(Coordinates.DOWN);

        return Stream.of(
                edge(topLeft, topRight),
                edge(topRight, downRight),
                edge(downRight, downLeft),
                edge(downLeft, topLeft)
        ).collect(Collectors.toSet());
    }
}

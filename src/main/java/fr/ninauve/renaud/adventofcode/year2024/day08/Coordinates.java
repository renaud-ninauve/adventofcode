package fr.ninauve.renaud.adventofcode.year2024.day08;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coordinates {
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
}

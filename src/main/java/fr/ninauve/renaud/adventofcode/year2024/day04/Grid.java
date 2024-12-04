package fr.ninauve.renaud.adventofcode.year2024.day04;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class Grid {
    private final Map<Coordinates, Character> values = new HashMap<>();
    private int nbRows;
    private int nbCols;

    public void put(Coordinates coordinates, Character value) {
        values.put(coordinates, value);
        nbRows = Math.max(nbRows, coordinates.getRow() + 1);
        nbCols = Math.max(nbCols, coordinates.getCol() + 1);
    }

    public boolean contains(Coordinates coordinates, Character value) {
        Character found = values.get(coordinates);
        return Objects.equals(value, found);
    }

    public boolean containsInLine(List<Character> values, Coordinates origin, Coordinates delta) {
        for (int i = 0; i < values.size(); i++) {
            Character value = values.get(i);
            Coordinates coordinates = origin.moveOf(delta.multiply(i));
            if (!contains(coordinates, value)) {
                return false;
            }
        }
        return true;
    }

    @Data
    @Builder
    public static class Coordinates {
        private final int row;
        private final int col;

        public Coordinates moveOf(Coordinates delta) {
            return new Coordinates(row + delta.getRow(), col + delta.getCol());
        }

        public Coordinates multiply(int value) {
            return new Coordinates(row * value, col * value);
        }
    }
}

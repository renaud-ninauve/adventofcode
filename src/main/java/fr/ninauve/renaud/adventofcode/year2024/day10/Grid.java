package fr.ninauve.renaud.adventofcode.year2024.day10;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class Grid<T> {
    private final Map<Coordinates, T> cells;
    private final int nbRows;
    private final int nbCols;

    public static Grid<Height> fromInput(List<String> input) {
        int nbRows = input.size();
        int nbCols = input.getFirst().length();
        Map<Coordinates, Height> cells = new HashMap<>();
        for (int row = 0; row < nbRows; row++) {
            String line = input.get(row);
            for (int col = 0; col < nbCols; col++) {
                Coordinates coordinates = new Coordinates(row, col);
                int height = line.charAt(col) - '0';
                cells.put(coordinates, new Height(height));
            }
        }
        return new Grid<>(cells, nbRows, nbCols);
    }

    public List<Coordinates> neighbours(Coordinates coordinates) {
        return coordinates.neighbours().stream()
                .filter(c -> c.getRow() >= 0 && c.getCol() >= 0 && c.getRow() < nbRows && c.getCol() < nbCols)
                .toList();
    }

    public List<Coordinates> neighboursWithValue(Coordinates coordinates, T value) {
        return neighbours(coordinates).stream()
                .filter(c -> Objects.equals(cells.get(c), value))
                .toList();
    }

    public T get(Coordinates coordinates) {
        return cells.get(coordinates);
    }

    public List<Coordinates> find(T value) {
        return cells.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), value))
                .map(Map.Entry::getKey)
                .toList();
    }
}

package fr.ninauve.renaud.adventofcode.year2024.day12;

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

    public static Grid<CellContent> fromInput(List<String> input) {
        int nbRows = input.size();
        int nbCols = input.getFirst().length();
        Map<Coordinates, CellContent> cells = new HashMap<>();
        for (int row = 0; row < nbRows; row++) {
            String line = input.get(row);
            for (int col = 0; col < nbCols; col++) {
                Coordinates coordinates = new Coordinates(row, col);
                cells.put(coordinates, new CellContent(line.substring(col, col + 1)));
            }
        }
        return new Grid<>(cells, nbRows, nbCols);
    }

    public List<Coordinates> neighbours(Coordinates coordinates) {
        return coordinates.neighbours().stream()
                .filter(this::isValid)
                .toList();
    }

    public boolean isValid(Coordinates c) {
        return c.getRow() >= 0 && c.getCol() >= 0 && c.getRow() < nbRows && c.getCol() < nbCols;
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

package fr.ninauve.renaud.adventofcode.year2023.day03;

import lombok.Data;

import java.util.*;
import java.util.function.Predicate;

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

    public List<Coordinates> neighbours(ColumnsGroup group) {
        return group.neighbours().stream()
                .filter(this::isValid)
                .toList();
    }

    public boolean isValid(Coordinates c) {
        return c.getRow() >= 0 && c.getCol() >= 0 && c.getRow() < nbRows && c.getCol() < nbCols;
    }

    public List<ColumnsGroup> columnsGroups(Predicate<T> predicate) {
        final List<ColumnsGroup> all = new ArrayList<>();
        for (int row = 0; row < nbRows; row++) {
            int startMatching = -1;
            for (int col = 0; col < nbCols; col++) {
                Coordinates coordinates = new Coordinates(row, col);
                T cellContent = cells.get(coordinates);
                if (predicate.test(cellContent)) {
                    startMatching = startMatching == -1 ? col : startMatching;
                } else if (startMatching > -1) {
                    all.add(new ColumnsGroup(row, startMatching, col - 1));
                    startMatching = -1;
                }
            }
            if (startMatching > -1) {
                all.add(new ColumnsGroup(row, startMatching, nbCols - 1));
            }
        }
        return all;
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

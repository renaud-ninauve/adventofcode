package fr.ninauve.renaud.adventofcode.year2024.day15;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class Grid<T> {
    private final Map<Cell, T> cells;
    private final int nbRows;
    private final int nbCols;

    public static Grid<CellContent> fromInput(List<String> input) {
        int nbRows = input.size();
        int nbCols = input.getFirst().length();
        Map<Cell, CellContent> cells = new HashMap<>();
        for (int row = 0; row < nbRows; row++) {
            String line = input.get(row);
            for (int col = 0; col < nbCols; col++) {
                Cell cell = new Cell(row, col);
                cells.put(cell, CellContent.fromSymbol(line.substring(col, col + 1)));
            }
        }
        return new Grid<>(cells, nbRows, nbCols);
    }

    public List<Cell> neighbours(Cell cell) {
        return cell.neighbours().stream()
                .filter(this::isValid)
                .toList();
    }

    public boolean isValid(Cell c) {
        return c.getRow() >= 0 && c.getCol() >= 0 && c.getRow() < nbRows && c.getCol() < nbCols;
    }

    public T get(Cell cell) {
        return cells.get(cell);
    }

    public List<Cell> find(T value) {
        return cells.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), value))
                .map(Map.Entry::getKey)
                .toList();
    }
}

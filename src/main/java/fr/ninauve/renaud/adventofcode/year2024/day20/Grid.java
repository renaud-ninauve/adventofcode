package fr.ninauve.renaud.adventofcode.year2024.day20;

import lombok.Data;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class Grid {
    private final Map<Cell, CellContent> cells;
    private final int nbRows;
    private final int nbCols;

    public static Grid fromInput(List<String> input) {
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
        return new Grid(cells, nbRows, nbCols);
    }

    public List<String> toOutput() {
        return toOutput(new Cells(Set.of()), null);
    }

    public List<String> toOutput(Cells cells, String symbol) {
        final List<String> output = new ArrayList<>();
        for (int row = 0; row < nbRows; row++) {
            final StringBuilder line = new StringBuilder();
            for (int col = 0; col < nbCols; col++) {
                Cell cell = new Cell(row, col);
                final String outputSymbol;
                if (cells.contains(cell)) {
                    outputSymbol = symbol;
                } else {
                    CellContent content = get(cell);
                    outputSymbol = content.symbol();
                }
                line.append(outputSymbol);
            }
            output.add(line.toString());
        }
        return output;
    }

    public List<Cell> neighbours(Cell cell) {
        return cell.neighbours().stream()
                .filter(this::isValid)
                .toList();
    }

    public boolean isValid(Cell c) {
        return c.getRow() >= 0 && c.getCol() >= 0 && c.getRow() < nbRows && c.getCol() < nbCols;
    }

    public CellContent get(Cell cell) {
        return cells.getOrDefault(cell, CellContent.EMPTY);
    }

    public List<Cell> find(CellContent value) {
        return cells.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), value))
                .map(Map.Entry::getKey)
                .toList();
    }
}

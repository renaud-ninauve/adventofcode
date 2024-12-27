package fr.ninauve.renaud.adventofcode.year2024.day18;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class Grid {
    private final Map<Cell, CellContent> cells;
    private final int nbRows;
    private final int nbCols;

    public static Grid fromInput(List<String> input, int nbRows, int nbCols) {
        Map<Cell, CellContent> cells = input.stream()
                .map(line -> line.split(","))
                .map(split -> new Cell(Integer.parseInt(split[1]), Integer.parseInt(split[0])))
                .collect(Collectors.toMap(Function.identity(), cell -> CellContent.CORRUTED));
        return new Grid(cells, nbRows, nbCols);
    }

    public List<String> toOutput() {
        final List<String> output = new ArrayList<>();
        for (int row = 0; row < nbRows; row++) {
            final StringBuilder line = new StringBuilder();
            for (int col = 0; col < nbCols; col++) {
                Cell cell = new Cell(row, col);
                CellContent content = get(cell);
                line.append(content.symbol());
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
        return cells.getOrDefault(cell, CellContent.SAFE);
    }

    public List<Cell> find(CellContent value) {
        return cells.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), value))
                .map(Map.Entry::getKey)
                .toList();
    }
}

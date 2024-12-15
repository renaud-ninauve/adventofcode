package fr.ninauve.renaud.adventofcode.year2024.day15;

import lombok.Data;

import java.util.*;

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
        return cells.get(cell);
    }

    public List<Cell> find(CellContent value) {
        return cells.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), value))
                .map(Map.Entry::getKey)
                .toList();
    }

    public void move(Cell cell, Move move) {
        move(cell, move.delta());
    }

    private void move(Cell cell, Cell delta) {
        CellContent content = cells.get(cell);
        Cell target = cell.moveOf(delta);
        CellContent targetContent = cells.get(target);

        if (targetContent == CellContent.BOX) {
            List<Cell> cellsToMove = new ArrayList<>();
            cellsToMove.add(cell);
            cellsToMove.add(target);
            moveBox(cellsToMove, delta);
            doMove(cellsToMove, delta);
            return;
        }
        if (targetContent != CellContent.EMPTY) {
            return;
        }
        cells.put(cell, CellContent.EMPTY);
        cells.put(target, content);
    }

    private void doMove(List<Cell> cellsToMove, Cell delta) {
        List<CellContent> contents = cellsToMove.stream()
                .map(this::get)
                .toList();
        for(int i=0; i<cellsToMove.size(); i++) {
            Cell cell = cellsToMove.get(i);
            CellContent content = contents.get(i);
            Cell target = cell.moveOf(delta);
            cells.put(target, content);
        }
        cells.put(cellsToMove.getFirst(), CellContent.EMPTY);
    }

    private void moveBox(List<Cell> cellsToMove, Cell delta) {
        Cell next = cellsToMove.getLast().moveOf(delta);
        CellContent nextContent = cells.get(next);
        if (nextContent == CellContent.WALL) {
            cellsToMove.clear();
            return;
        }
        if (nextContent == CellContent.EMPTY) {
            return;
        }
        if (nextContent == CellContent.BOX) {
            cellsToMove.add(next);
            moveBox(cellsToMove, delta);
        }
    }
}

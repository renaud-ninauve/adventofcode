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

        if (targetContent.isMoveable()) {
            List<Cell> cellsToMove = new ArrayList<>();
            cellsToMove.add(cell);
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
        if (cellsToMove.isEmpty()) {
            return;
        }
        List<CellContent> contents = cellsToMove.stream()
                .map(this::get)
                .toList();
        for (int i = 0; i < cellsToMove.size(); i++) {
            Cell cell = cellsToMove.get(i);
            CellContent content = contents.get(i);
            Cell target = cell.moveOf(delta);
            cells.put(target, content);
        }
        for (Cell cell : cellsToMove) {
            Cell previous = cell.moveOf(delta.multiply(-1));
            if (!cellsToMove.contains(previous)) {
                cells.put(cell, CellContent.EMPTY);
            }
        }
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
        if (nextContent == CellContent.BOX
                || nextContent == CellContent.LEFT_BOX && Move.RIGHT.delta().equals(delta)
                || nextContent == CellContent.RIGHT_BOX && Move.RIGHT.delta().equals(delta)
                || nextContent == CellContent.LEFT_BOX && Move.LEFT.delta().equals(delta)
                || nextContent == CellContent.RIGHT_BOX && Move.LEFT.delta().equals(delta)) {
            cellsToMove.add(next);
            moveBox(cellsToMove, delta);
        } else if (nextContent == CellContent.LEFT_BOX) {
            cellsToMove.add(next);
            moveBox(cellsToMove, delta);
            if (cellsToMove.isEmpty()) {
                return;
            }
            List<Cell> rightCellsToMove = new ArrayList<>();
            rightCellsToMove.add(next.moveOf(Move.RIGHT.delta()));
            moveBox(rightCellsToMove, delta);
            if (rightCellsToMove.isEmpty()) {
                cellsToMove.clear();
            } else {
                cellsToMove.addAll(rightCellsToMove);
            }
        } else if (nextContent == CellContent.RIGHT_BOX) {
            cellsToMove.add(next);
            moveBox(cellsToMove, delta);
            if (cellsToMove.isEmpty()) {
                return;
            }
            List<Cell> leftCellsToMove = new ArrayList<>();
            leftCellsToMove.add(next.moveOf(Move.LEFT.delta()));
            moveBox(leftCellsToMove, delta);
            if (leftCellsToMove.isEmpty()) {
                cellsToMove.clear();
            } else {
                cellsToMove.addAll(leftCellsToMove);
            }
        }
    }
}

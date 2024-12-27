package fr.ninauve.renaud.adventofcode.year2024.day18;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public record Cells(Collection<Cell> cells) {

    public static Cells fromInput(List<String> input, int nbRows, int nbCols, String symbol) {
        Collection<Cell> path = new HashSet<>();
        for (int row = 0; row < nbRows; row++) {
            String line = input.get(row);
            for (int col = 0; col < nbCols; col++) {
                if (symbol.equals(Character.toString(line.charAt(col)))) {
                    Cell cell = new Cell(row, col);
                    path.add(cell);
                }
            }
        }
        return new Cells(path);
    }

    public boolean contains(Cell cell) {
        return cells.contains(cell);
    }
}

package fr.ninauve.renaud.adventofcode.year2024.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part01 {
    private static final List<Cell> DELTAS_CLOCKWISE = List.of(
            Cell.RIGHT, Cell.DOWN, Cell.LEFT, Cell.UP
    );
    private static final List<String> SYMBOLS_CLOCKWISE = List.of(
            ">", "v", "<", "^"
    );

    public static List<String> toOutput(Grid grid, List<Action> actions) {
        final Map<Cell, String> trajectory = new HashMap<>();
        Cell position = grid.find(CellContent.START).getFirst();
        int direction = 0;
        for (Action action : actions) {
            switch (action) {
                case MOVE_STRAIGHT: {
                    Cell delta = DELTAS_CLOCKWISE.get(direction);
                    String symbol = SYMBOLS_CLOCKWISE.get(direction);
                    position = position.moveOf(delta);
                    trajectory.put(position, symbol);
                    break;
                }
                case TURN_CLOCKWISE: {
                    direction = (direction + 1) % DELTAS_CLOCKWISE.size();
                    break;
                }
                case TURN_COUNTERCLOCKWISE: {
                    direction = (DELTAS_CLOCKWISE.size() + direction - 1) % DELTAS_CLOCKWISE.size();
                    break;
                }
            }
        }
        final List<String> lines = new ArrayList<>();
        for (int row = 0; row < grid.getNbRows(); row++) {
            final StringBuilder line = new StringBuilder();
            for (int col = 0; col < grid.getNbCols(); col++) {
                Cell cell = new Cell(row, col);
                String symbol = trajectory.getOrDefault(cell, grid.get(cell).symbol());
                line.append(symbol);
            }
            lines.add(line.toString());
        }
        return lines;
    }

    public static List<Action> findMinActions(List<String> input) {
        return null;
    }
}

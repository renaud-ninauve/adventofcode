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
        Grid grid = Grid.fromInput(input);
        Cell start = grid.find(CellContent.START).getFirst();
        Cell exit = grid.find(CellContent.EXIT).getFirst();
        int direction = 0;
        List<Action> minActions = new ArrayList<>();
        findMinActions(minActions, exit, new State(start, direction), new ArrayList<>(), new ArrayList<>());
        return minActions;
    }

    private static void findMinActions(List<Action> minActions, Cell target, State current, List<Action> currentActions, List<State> visited) {
        Cell position = current.position();
        int direction = current.direction;
        if (visited.contains(current)) {
            return;
        }
        visited.add(current);
        if (position.equals(target)) {
            if (minActions.isEmpty() || cost(currentActions) < cost(minActions)) {
                minActions.clear();
                minActions.addAll(currentActions);
                return;
            }
        }
        if (!minActions.isEmpty() && cost(currentActions) >= cost(minActions)) {
            return;
        }

        Cell delta = DELTAS_CLOCKWISE.get(direction);
        List<Action> straightActions = new ArrayList<>(currentActions);
        straightActions.add(Action.MOVE_STRAIGHT);
        findMinActions(minActions, target, new State(position.moveOf(delta), direction), straightActions, visited);

        int clockwiseDirection = (direction + 1) % DELTAS_CLOCKWISE.size();
        findMinActions(minActions, target, new State(position, clockwiseDirection), straightActions, visited);

        int counterClockwiseDirection = (DELTAS_CLOCKWISE.size() + direction - 1) % DELTAS_CLOCKWISE.size();
        findMinActions(minActions, target, new State(position, counterClockwiseDirection), straightActions, visited);
    }

    private static long cost(List<Action> actions) {
        return actions.stream().mapToLong(Action::cost).sum();
    }

    private record State(Cell position, int direction) {
    }
}

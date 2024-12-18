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
        Map<State, List<Action>> minActions = new HashMap<>();
        findMinActions(grid, exit, List.of(new Context(new State(start, direction), new ArrayList<>())), minActions);
        return minActions.get(exit);
    }

    private static void findMinActions(Grid grid, Cell target, List<Context> contexts, Map<State, List<Action>> minForState) {
        final List<Context> newContexts = new ArrayList<>();
        for (Context context : contexts) {
            Cell position = context.state().position();
            int direction = context.state().direction();
            List<Action> actions = context.actions();

            Cell delta = DELTAS_CLOCKWISE.get(direction);
            List<Action> straightActions = new ArrayList<>(actions);
            straightActions.add(Action.MOVE_STRAIGHT);
            newContexts.add(new Context(new State(position.moveOf(delta), direction), straightActions));

            int clockDirection = (direction + 1) % DELTAS_CLOCKWISE.size();
            List<Action> clockActions = new ArrayList<>(actions);
            straightActions.add(Action.TURN_CLOCKWISE);
            newContexts.add(new Context(new State(position, clockDirection), clockActions));

            int counterClockDirection = (DELTAS_CLOCKWISE.size() + direction - 1) % DELTAS_CLOCKWISE.size();
            List<Action> counterClockActions = new ArrayList<>(actions);
            counterClockActions.add(Action.TURN_COUNTERCLOCKWISE);
            newContexts.add(new Context(new State(position, counterClockDirection), counterClockActions));
        }

        List<Action> minForTarget = minForState.get(target);
        final List<Context> filtered = new ArrayList<>();
        for (Context context : newContexts) {
            Cell position = context.state().position();
            int direction = context.state().direction();
            List<Action> actions = context.actions();
            if (grid.get(position) == CellContent.WALL) {
                continue;
            }
            if (minForTarget != null && cost(actions) >= cost(minForTarget)) {
                continue;
            }
            List<Action> minActionsState = minForState.get(context.state());
            if (minActionsState == null || cost(actions) < cost(minActionsState)) {
                minForState.put(context.state(), actions);
            } else {
                continue;
            }
            filtered.add(context);

        }
        findMinActions(grid, target, filtered, minForState);
    }

    private static long cost(List<Action> actions) {
        return actions.stream().mapToLong(Action::cost).sum();
    }

    private record Context(State state, List<Action> actions) {
    }

    private record State(Cell position, int direction) {
    }
}

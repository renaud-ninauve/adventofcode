package fr.ninauve.renaud.adventofcode.year2024.day16;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

public class Part01 {
    private static final List<Cell> DELTAS_CLOCKWISE = List.of(
            Cell.RIGHT, Cell.DOWN, Cell.LEFT, Cell.UP
    );
    private static final List<String> SYMBOLS_CLOCKWISE = List.of(
            ">", "v", "<", "^"
    );

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part01.class
                .getResource("/year2024/day16/input.txt").toURI()), StandardCharsets.UTF_8);
        System.out.println(Part01.solve(input));
    }

    public static List<String> toOutput(Grid grid, List<Action> actions) {
        final Map<Cell, String> trajectory = new HashMap<>();
        Cell position = grid.find(CellContent.START).getFirst();
        int direction = 0;
        for (Action action : actions) {
            switch (action) {
                case MOVE_STRAIGHT: {
                    Cell delta = DELTAS_CLOCKWISE.get(direction);
                    position = position.moveOf(delta);
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
            String symbol = SYMBOLS_CLOCKWISE.get(direction);
            trajectory.put(position, symbol);
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

    public static long solve(List<String> input) {
        List<Action> minActions = findMinActions(input);
        return cost(minActions);
    }

    public static List<Action> findMinActions(List<String> input) {
        Grid grid = Grid.fromInput(input);
        Cell start = grid.find(CellContent.START).getFirst();
        Cell exit = grid.find(CellContent.EXIT).getFirst();
        Map<State, List<Action>> minActions = new HashMap<>();
        findMinActions(grid, start, exit, minActions);
        return minForPosition(minActions, exit);
    }

    private static void findMinActions(Grid grid, Cell origin, Cell target, Map<State, List<Action>> minForState) {
        Queue<Context> queue = new LinkedList<Context>();
        queue.add(new Context(new State(origin, 0), List.of()));
        Context current = null;
        while ((current = queue.poll()) != null) {
            Cell position = current.state().position();
            int direction = current.state().direction();
            List<Action> actions = current.actions();

            List<Action> minActionsForTarget = minForPosition(minForState, target);
            if (minActionsForTarget != null && cost(actions) >= cost(minActionsForTarget)) {
                continue;
            }

            List<Action> minActionsForCurrent = minForState.get(current.state());
            if (minActionsForCurrent == null || cost(actions) < cost(minActionsForCurrent)) {
                minForState.put(current.state(), actions);
            } else {
                continue;
            }

            Cell delta = DELTAS_CLOCKWISE.get(direction);
            Cell straightPosition = position.moveOf(delta);
            List<Action> straightActions = new ArrayList<>(actions);
            straightActions.add(Action.MOVE_STRAIGHT);
            if (grid.isValid(straightPosition) && grid.get(straightPosition) != CellContent.WALL) {
                queue.offer(new Context(new State(straightPosition, direction), straightActions));
            }

            int clockDirection = (direction + 1) % DELTAS_CLOCKWISE.size();
            List<Action> clockActions = new ArrayList<>(actions);
            clockActions.add(Action.TURN_CLOCKWISE);
            queue.add(new Context(new State(position, clockDirection), clockActions));

            int counterClockDirection = (DELTAS_CLOCKWISE.size() + direction - 1) % DELTAS_CLOCKWISE.size();
            List<Action> counterClockActions = new ArrayList<>(actions);
            counterClockActions.add(Action.TURN_COUNTERCLOCKWISE);
            queue.add(new Context(new State(position, counterClockDirection), counterClockActions));
        }
    }

    private static List<Action> minForPosition(Map<State, List<Action>> minForState, Cell position) {
        return IntStream.range(0, DELTAS_CLOCKWISE.size())
                .mapToObj(direction -> minForState.get(new State(position, direction)))
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Part01::cost))
                .findFirst()
                .orElse(null);
    }

    private static long cost(List<Action> actions) {
        return actions.stream().mapToLong(Action::cost).sum();
    }

    private record Context(State state, List<Action> actions) {
    }

    private record State(Cell position, int direction) {
    }
}

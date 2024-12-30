package fr.ninauve.renaud.adventofcode.year2024.day20;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Part01 {

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part01.class
                .getResource("/year2024/day20/input.txt").toURI()), StandardCharsets.UTF_8);
        System.out.println(Part01.solve(input));
    }

    public static long solve(List<String> input) {
        Grid grid = Grid.fromInput(input);
        List<Cell> shortestRegular = findShortest(grid);
        Collection<CheatPath> shortestCheats = findShortestCheats(grid, shortestRegular.size() - 100);
        return shortestCheats.size();
    }

    private static List<Cell> findShortest(Grid grid) {
        Cell start = grid.find(CellContent.START).getFirst();
        Cell exit = grid.find(CellContent.EXIT).getFirst();

        Map<Cell, List<Cell>> allShortest = new HashMap<>();
        Queue<List<Cell>> queue = new LinkedList<>();
        queue.offer(List.of(start));
        List<Cell> current;
        while ((current = queue.poll()) != null) {
            Cell last = current.getLast();
            List<Cell> shortest = allShortest.get(last);
            if (shortest == null || current.size() < shortest.size()) {
                allShortest.put(last, current);
            } else {
                continue;
            }
            if (last.equals(exit)) {
                continue;
            }
            List<Cell> shortestToEnd = allShortest.get(exit);
            if (shortestToEnd != null && current.size() >= shortestToEnd.size()) {
                continue;
            }
            for (Cell newLast : grid.neighbours(last)) {
                CellContent newContent = grid.get(newLast);
                if (newContent.equals(CellContent.WALL)) {
                    continue;
                }
                if (current.contains(newLast)) {
                    continue;
                }
                List<Cell> newPath = new ArrayList<>(current);
                newPath.add(newLast);
                queue.offer(newPath);
            }
        }
        return allShortest.get(exit);
    }

    private static Collection<CheatPath> findShortestCheats(Grid grid, long maxDistance) {
        Cell start = grid.find(CellContent.START).getFirst();
        Cell exit = grid.find(CellContent.EXIT).getFirst();

        Collection<CheatPath> shortestToExit = new HashSet<>();
        Map<Cell, List<Cell>> allShortestRegular = new HashMap<>();
        Map<Cell, List<Cell>> allShortestCheats = new HashMap<>();
        Queue<CheatPath> queue = new LinkedList<>();
        queue.offer(CheatPath.regularOf(start));
        CheatPath current;
        while ((current = queue.poll()) != null) {
            Cell last = current.getLast();
            Map<Cell, List<Cell>> allShortest = current.containsCheats() ? allShortestCheats : allShortestRegular;
            List<Cell> shortest = allShortest.get(last);
            if (last.equals(exit) && current.size() < maxDistance) {
                shortestToExit.add(current);
            }
            if (shortest == null || current.size() < shortest.size()) {
                allShortest.put(last, current.cells());
            } else {
                continue;
            }
            if (last.equals(exit)) {
                continue;
            }
            if (current.size() >= maxDistance) {
                continue;
            }
            for (Cell newLast : grid.neighbours(last)) {
                CellContent newContent = grid.get(newLast);
                boolean isWall = newContent.equals(CellContent.WALL);
                if (isWall && !current.canCheat()) {
                    continue;
                }
                if (current.contains(newLast)) {
                    continue;
                }
                CheatPath newCheatPath = current.concat(newLast, isWall);
                queue.offer(newCheatPath);
            }
        }
        return shortestToExit;
    }

    private record CheatPath(List<Cell> cells, Collection<Cell> cheats) {
        private static CheatPath regularOf(Cell start) {
            return new CheatPath(List.of(start), Set.of());
        }

        private Cell getLast() {
            return cells.getLast();
        }

        private int size() {
            return cells.size();
        }

        private boolean contains(Cell cell) {
            return cells.contains(cell);
        }

        private CheatPath concat(Cell cell, boolean cheat) {
            List<Cell> concatCells = new ArrayList<>(cells);
            concatCells.add(cell);
            Collection<Cell> concatCheats = new HashSet<>(cheats);
            if (cheat || endsWithOneCheatOnly()) {
                concatCheats.add(cell);
            }
            return new CheatPath(concatCells, concatCheats);
        }

        private boolean containsCheats() {
            return !cheats.isEmpty();
        }

        private boolean endsWithOneCheatOnly() {
            if (cells.isEmpty()) {
                return false;
            }
            int size = cells.size();
            if (size == 1) {
                return cheats.contains(cells.getFirst());
            }
            Cell beforeLast = cells.get(size - 2);
            Cell last = cells.getLast();
            return !cheats.contains(beforeLast) && cheats.contains(last);
        }

        private boolean canCheat() {
            return !containsCheats() || endsWithOneCheatOnly();
        }
    }
}

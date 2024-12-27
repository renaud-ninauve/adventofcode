package fr.ninauve.renaud.adventofcode.year2024.day18;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Part01 {

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part01.class
                .getResource("/year2024/day18/input.txt").toURI()), StandardCharsets.UTF_8);
        System.out.println(Part01.solve(input, 71, 71, 1024));
    }

    public static long solve(List<String> input, int nbRows, int nbCols, int time) {
        return findPath(input, nbRows, nbCols, time).size() - 1;
    }

    public static List<Cell> findPath(List<String> input, int nbRows, int nbCols, int time) {
        Grid grid = Grid.fromInput(input.subList(0, time), nbRows, nbCols);

        Cell start = new Cell(0, 0);
        Cell end = new Cell(nbRows - 1, nbCols - 1);
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
            if (last.equals(end)) {
                continue;
            }
            List<Cell> shortestToEnd = allShortest.get(end);
            if (shortestToEnd != null && current.size() >= shortestToEnd.size()) {
                continue;
            }
            for (Cell newLast : grid.neighbours(last)) {
                CellContent newContent = grid.get(newLast);
                if (newContent.equals(CellContent.CORRUTED)) {
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
        return allShortest.get(end);
    }
}

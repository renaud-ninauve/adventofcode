package fr.ninauve.renaud.adventofcode.year2023.day03;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Part01 {
    public static void main(String... args) throws Exception {
        List<String> input = Files.readAllLines(Path.of(Part01.class
                .getResource("/year2023/day03/input.txt").toURI()));
        System.out.println(Part01.solve(input));
    }

    public static long solve(List<String> input) {
        Grid<CellContent> grid = Grid.fromInput(input);
        List<ColumnsGroup> groups = grid.columnsGroups(CellContent::isDigit);

        List<ColumnsGroup> validGroups = groups.stream().filter(group -> nearSymbol(grid, group)).toList();
        return validGroups.stream()
                .mapToLong(group -> valueOf(grid, group))
                .sum();
    }

    private static boolean nearSymbol(Grid<CellContent> grid, ColumnsGroup group) {
        return grid.neighbours(group).stream()
                .map(grid::get)
                .anyMatch(CellContent::isSymbol);
    }

    private static long valueOf(Grid<CellContent> grid, ColumnsGroup group) {
        String stringValue = group.coordinates().stream()
                .map(grid::get)
                .map(CellContent::value)
                .collect(Collectors.joining());
        return Long.parseLong(stringValue);
    }
}

package fr.ninauve.renaud.adventofcode.year2023.day03;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Part02 {
    public static void main(String... args) throws Exception {
        List<String> input = Files.readAllLines(Path.of(Part02.class
                .getResource("/year2023/day03/input.txt").toURI()));
        System.out.println(Part02.solve(input));
    }

    public static long solve(List<String> input) {
        Grid<CellContent> grid = Grid.fromInput(input);
        List<ColumnsGroup> groups = grid.columnsGroups(CellContent::isDigit);

        final Map<Coordinates, List<ColumnsGroup>> groupBySymbols = groups.stream()
                .flatMap(group -> symbols(grid, group).stream()
                        .map(symbol -> new AbstractMap.SimpleEntry<>(symbol, group))
                ).collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        return groupBySymbols.values().stream()
                .filter(groupsSharingSymbol -> groupsSharingSymbol.size() == 2)
                .mapToLong(groupsSharingSymbol -> valueOf(grid, groupsSharingSymbol.getFirst()) * valueOf(grid, groupsSharingSymbol.getLast()))
                .sum();
    }

    private static Collection<Coordinates> symbols(Grid<CellContent> grid, ColumnsGroup group) {
        return grid.neighbours(group).stream()
                .filter(neighbour -> grid.get(neighbour).isSymbol())
                .toList();
    }

    private static long valueOf(Grid<CellContent> grid, ColumnsGroup group) {
        String stringValue = group.coordinates().stream()
                .map(grid::get)
                .map(CellContent::value)
                .collect(Collectors.joining());
        return Long.parseLong(stringValue);
    }
}

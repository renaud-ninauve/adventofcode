package fr.ninauve.renaud.adventofcode.year2024.day12;

import javax.swing.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Region(CellContent content, Collection<Coordinates> coordinates) {
    public static Collection<Region> fromGrid(Grid<CellContent> grid) {
        final Collection<Coordinates> visited = new ArrayList<>();
        final Collection<Region> regions = new HashSet<>();
        for (int row = 0; row < grid.getNbRows(); row++) {
            for (int col = 0; col < grid.getNbCols(); col++) {
                Coordinates coordinates = new Coordinates(row, col);
                if (visited.contains(coordinates)) {
                    continue;
                }
                Region region = visitRegion(grid, coordinates);
                regions.add(region);
                visited.addAll(region.coordinates());
            }
        }
        return regions;
    }

    private static Region visitRegion(Grid<CellContent> grid, Coordinates start) {
        Set<Coordinates> visited = new HashSet<>();
        CellContent content = grid.get(start);
        visitRegion(grid, content, start, visited);
        return new Region(content, visited);
    }

    private static void visitRegion(Grid<CellContent> grid, CellContent content, Coordinates current, Set<Coordinates> visited) {
        if (visited.contains(current)) {
            return;
        }
        visited.add(current);
        grid.neighbours(current).stream()
                .filter(n -> content.equals(grid.get(n)))
                .forEach(n -> visitRegion(grid, content, n, visited));
    }

    public Collection<Edge> edges() {
        Map<Edge, Long> edgesCounts = coordinates.stream()
                .map(Coordinates::edges)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return edgesCounts.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public long area() {
        return coordinates().size();
    }

    public long perimeter() {
        return edges().size();
    }
}

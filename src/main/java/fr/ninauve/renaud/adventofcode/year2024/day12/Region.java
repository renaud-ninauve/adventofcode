package fr.ninauve.renaud.adventofcode.year2024.day12;

import javax.swing.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fr.ninauve.renaud.adventofcode.year2024.day12.Edge.edge;

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

    public Collection<Edge> sides() {
        Set<Coordinates> edgesCoordinates = edges().stream()
                .flatMap(edge -> Stream.of(edge.a(), edge.b()))
                .collect(Collectors.toSet());

        Collection<Edge> sides = new HashSet<>();
        Collection<Coordinates> visitedVertical = new HashSet<>();
        for(Coordinates coordinates: edgesCoordinates) {
            if (visitedVertical.contains(coordinates)) {
                continue;
            }
            Set<Coordinates> vertical = new HashSet<>();
            visitSide(edgesCoordinates, coordinates, vertical, Coordinates.UP);
            visitSide(edgesCoordinates, coordinates, vertical, Coordinates.DOWN);

            if (vertical.size() < 2) {
                continue;
            }
            int sideCol = vertical.iterator().next().getCol();
            IntSummaryStatistics rowStats = vertical.stream().mapToInt(Coordinates::getRow).summaryStatistics();
            Edge side = edge(new Coordinates(rowStats.getMin(), sideCol), new Coordinates(rowStats.getMax(), sideCol));
            sides.add(side);
            visitedVertical.addAll(vertical);
        }
        Collection<Coordinates> visitedHorizontal = new HashSet<>();
        for(Coordinates coordinates: edgesCoordinates) {
            if (visitedHorizontal.contains(coordinates)) {
                continue;
            }
            Set<Coordinates> horizontal = new HashSet<>();
            visitSide(edgesCoordinates, coordinates, horizontal, Coordinates.LEFT);
            visitSide(edgesCoordinates, coordinates, horizontal, Coordinates.RIGHT);

            if (horizontal.size() < 2) {
                continue;
            }
            int sideRow = horizontal.iterator().next().getRow();
            IntSummaryStatistics colStats = horizontal.stream().mapToInt(Coordinates::getCol).summaryStatistics();
            Edge side = edge(new Coordinates(sideRow, colStats.getMin()), new Coordinates(sideRow, colStats.getMax()));
            sides.add(side);
            visitedHorizontal.addAll(horizontal);
        }
        return sides;
    }

    private void visitSide(Set<Coordinates> edgesCoordinates, Coordinates current, Collection<Coordinates> visited, Coordinates delta) {
        if (visited.contains(current)) {
            return;
        }
        visited.add(current);
        Coordinates next = current.moveOf(delta);
        if (edgesCoordinates.contains(next)) {
            visitSide(edgesCoordinates, next, visited, delta);
        }
    }

    public long area() {
        return coordinates().size();
    }

    public long perimeter() {
        return edges().size();
    }
}

package fr.ninauve.renaud.adventofcode.year2024.day12;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Region(CellContent content, Collection<Coordinates> coordinates) {
    public static Collection<Region> fromGrid(Grid<CellContent> grid) {
        int previousId = -1;
        final Map<Coordinates, Integer> regions = new HashMap<>();
        final Map<Integer, Integer> aliases = new HashMap<>();
        for (int row = 0; row < grid.getNbRows(); row++) {
            for (int col = 0; col < grid.getNbCols(); col++) {
                Coordinates coordinates = new Coordinates(row, col);
                CellContent cellContent = grid.get(coordinates);
                Set<Integer> regionIds = Stream.of(coordinates.moveOf(Coordinates.UP), coordinates.moveOf(Coordinates.LEFT))
                        .filter(grid::isValid)
                        .filter(n -> cellContent.equals(grid.get(n)))
                        .map(regions::get)
                        .collect(Collectors.toSet());

                if (regionIds.isEmpty()) {
                    previousId++;
                    regions.put(coordinates, previousId);
                } else {
                    List<Integer> sortedIds = regionIds.stream().sorted().toList();
                    Integer regionId = sortedIds.getFirst();
                    regions.put(coordinates, regionId);
                    sortedIds.stream().skip(1).forEach(alias -> aliases.put(alias, regionId));
                }
            }
        }

        Map<Integer, Set<Coordinates>> regionsById = regions.entrySet().stream()
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), regionId(aliases, e.getValue())))
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toSet())));
        return regionsById.values().stream()
                .map(region -> new Region(grid.get(region.iterator().next()), region))
                .collect(Collectors.toSet());
    }

    private static int regionId(Map<Integer, Integer> alias, int id) {
        int previous = id;
        Integer current = null;
        while((current = alias.get(previous)) != null) {
            previous = current;
        }
        return previous;
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

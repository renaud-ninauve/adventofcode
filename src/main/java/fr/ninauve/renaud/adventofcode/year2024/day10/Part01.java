package fr.ninauve.renaud.adventofcode.year2024.day10;

import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
public class Part01 {
    private static final Height MIN_HEIGHT = new Height(0);
    private static final Height MAX_HEIGHT = new Height(9);
    private static final Height DELTA_HEIGHT = new Height(1);

    private final Grid<Height> grid;

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(fr.ninauve.renaud.adventofcode.year2024.day03.Part01.class
                .getResource("/year2024/day10/input.txt").toURI()), StandardCharsets.UTF_8);

        Part01 part01 = Part01.parse(input);
        System.out.println(part01.solve());
    }

    public static Part01 parse(List<String> input) {
        return new Part01(Grid.fromInput(input));
    }

    public long solve() {
        Map<Coordinates, List<List<Coordinates>>> paths = paths();
        return paths.values().stream()
                .mapToLong(pathsFromStart -> {
                    List<Coordinates> distinctEnds = pathsFromStart.stream().map(List::getLast).distinct().toList();
                    return (long) distinctEnds.size();
                }).sum();
    }

    private Map<Coordinates, List<List<Coordinates>>> paths() {
        Map<Coordinates, List<List<Coordinates>>> allPaths = new TreeMap<>(Coordinates.READING_ORDER);
        List<Coordinates> starts = grid.find(MIN_HEIGHT);
        for (Coordinates start : starts) {
            List<List<Coordinates>> paths = new ArrayList<>();
            path(paths, start, List.of());
            allPaths.put(start, paths);
        }
        return allPaths;
    }

    private void path(List<List<Coordinates>> result, Coordinates current, List<Coordinates> path) {
        Height currentHeight = grid.get(current);
        if (MAX_HEIGHT.equals(currentHeight)) {
            result.add(path);
            return;
        }
        Height newHeight = currentHeight.add(DELTA_HEIGHT);
        for (Coordinates neighbour : grid.neighboursWithValue(current, newHeight)) {
            final List<Coordinates> newPath = new ArrayList<>(path);
            newPath.add(neighbour);
            path(result, neighbour, newPath);
        }
    }
}

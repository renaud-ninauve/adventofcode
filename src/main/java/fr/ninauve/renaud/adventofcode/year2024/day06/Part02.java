package fr.ninauve.renaud.adventofcode.year2024.day06;

import lombok.AllArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Part02 {
    private final Map<Coordinates, Boolean> map;
    private Coordinates guard;
    private Direction guardDirection;
    private final int nbRows;
    private final int nbCols;

    public static Part02 parse(List<String> lines) {
        final Map<Coordinates, Boolean> map = new HashMap<>();
        Coordinates guard = null;
        Direction guardDirection = null;
        int nbRows = lines.size();
        int nbCols = lines.get(0).length();

        for (int row = 0; row < nbRows; row++) {
            final String line = lines.get(row);
            for (int col = 0; col < nbCols; col++) {
                char c = line.charAt(col);
                Coordinates coordinates = Coordinates.builder()
                        .col(col)
                        .row(row)
                        .build();

                if (c == '#') {
                    map.put(coordinates, true);
                } else if (c == '^') {
                    guard = coordinates;
                    guardDirection = Direction.UP;
                } else if (c == '>') {
                    guard = coordinates;
                    guardDirection = Direction.LEFT;
                } else if (c == 'v') {
                    guard = coordinates;
                    guardDirection = Direction.DOWN;
                } else if (c == '<') {
                    guard = coordinates;
                    guardDirection = Direction.RIGHT;
                }
            }
        }
        return new Part02(map, guard, guardDirection, nbRows, nbCols);
    }

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(fr.ninauve.renaud.adventofcode.year2024.day03.Part01.class
                .getResource("/year2024/day06/input.txt").toURI()), StandardCharsets.UTF_8);

        long xmas = parse(input).solve();
        System.out.println(xmas);
    }

    public long solve() {
        Walk initial = walk(map);
        long sum = 0L;
        for (Coordinates coordinates : initial.coordinatesAndDirections().stream().map(CoordinatesAndDirection::coordinates).collect(Collectors.toSet())) {
            if (coordinates.equals(guard)) {
                continue;
            }
            final Map<Coordinates, Boolean> newMap = new LinkedHashMap<>(map);
            newMap.put(coordinates, true);
            Walk newWalk = walk(newMap);
            if (newWalk.isLoop()) {
                sum++;
            }
        }
        return sum;
    }

    private Walk walk(Map<Coordinates, Boolean> map) {
        Direction currentDirection = this.guardDirection;
        Coordinates currentCoordinates = this.guard;

        final Set<CoordinatesAndDirection> guardPositions = new LinkedHashSet<>();
        guardPositions.add(new CoordinatesAndDirection(currentCoordinates, currentDirection));

        while (currentCoordinates.getCol() >= 0 && currentCoordinates.getRow() >= 0 && currentCoordinates.getCol() < nbCols && currentCoordinates.getRow() < nbRows) {
            Direction nextDirection = currentDirection;
            Coordinates nextCoordinates = currentCoordinates.moveOf(nextDirection.getIncrement());
            while (map.getOrDefault(nextCoordinates, false)) {
                nextDirection = nextDirection.turn90();
                nextCoordinates = currentCoordinates.moveOf(nextDirection.getIncrement());
            }

            currentCoordinates = nextCoordinates;
            currentDirection = nextDirection;

            CoordinatesAndDirection newPosition = new CoordinatesAndDirection(currentCoordinates, currentDirection);
            if (guardPositions.contains(newPosition)) {
                return new Walk(guardPositions, true);
            }
            guardPositions.add(newPosition);
        }
        return new Walk(guardPositions.stream().limit(guardPositions.size() - 1).collect(Collectors.toSet()), false);
    }

    private record CoordinatesAndDirection(Coordinates coordinates, Direction direction) {
    }

    private record Walk(Set<CoordinatesAndDirection> coordinatesAndDirections, boolean isLoop) {
    }
}

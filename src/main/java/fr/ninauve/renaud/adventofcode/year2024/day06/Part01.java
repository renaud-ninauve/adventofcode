package fr.ninauve.renaud.adventofcode.year2024.day06;

import lombok.AllArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@AllArgsConstructor
public class Part01 {
    private static Node<Coordinates> DIRECTIONS;

    static {
        Node<Coordinates> up = new Node<>(Coordinates.UP, null);
        Node<Coordinates> right = new Node<>(Coordinates.RIGHT, null);
        Node<Coordinates> down = new Node<>(Coordinates.DOWN, null);
        Node<Coordinates> left = new Node<>(Coordinates.LEFT, null);

        up.setNext(right);
        right.setNext(down);
        down.setNext(left);
        left.setNext(up);

        DIRECTIONS = up;
    }

    private final Map<Coordinates, Boolean> map;
    private Coordinates guard;
    private Node<Coordinates> guardDirection;
    private final int nbRows;
    private final int nbCols;

    public static Part01 parse(List<String> lines) {
        final Map<Coordinates, Boolean> map = new HashMap<>();
        Coordinates guard = null;
        Coordinates guardDirection = null;
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
                    guardDirection = Coordinates.UP;
                } else if (c == '>') {
                    guard = coordinates;
                    guardDirection = Coordinates.LEFT;
                } else if (c == 'v') {
                    guard = coordinates;
                    guardDirection = Coordinates.DOWN;
                } else if (c == '<') {
                    guard = coordinates;
                    guardDirection = Coordinates.RIGHT;
                }
            }
        }
        return new Part01(map, guard, findDirection(guardDirection), nbRows, nbCols);
    }

    private static Node<Coordinates> findDirection(Coordinates coordinates) {
        Node<Coordinates> current = DIRECTIONS;
        while (!coordinates.equals(current.getValue())) {
            current = current.getNext();
        }
        return current;
    }

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(fr.ninauve.renaud.adventofcode.year2024.day03.Part01.class
                .getResource("/year2024/day06/input.txt").toURI()), StandardCharsets.UTF_8);

        long xmas = parse(input).solve();
        System.out.println(xmas);
    }

    public long solve() {
        final Set<Coordinates> guardPositions = new HashSet<>();
        guardPositions.add(guard);

        while (guard.getCol() >= 0 && guard.getRow() >= 0 && guard.getCol() < nbCols && guard.getRow() < nbRows) {
            Node<Coordinates> nextDirection = guardDirection;
            Coordinates nextCoordinate = guard.moveOf(nextDirection.getValue());
            while (map.getOrDefault(nextCoordinate, false)) {
                nextDirection = guardDirection.getNext();
                nextCoordinate = guard.moveOf(nextDirection.getValue());
            }
            guardDirection = nextDirection;
            guard = nextCoordinate;
            guardPositions.add(guard);
        }
        return guardPositions.size() - 1;
    }
}

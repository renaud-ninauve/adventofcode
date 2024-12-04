package fr.ninauve.renaud.adventofcode.year2024.day04;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Part02 {

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(fr.ninauve.renaud.adventofcode.year2024.day03.Part01.class
                .getResource("/year2024/day04/input.txt").toURI()), StandardCharsets.UTF_8);
        long xmas = new Part02().xmas(input);
        System.out.println(xmas);
    }

    public long xmas(List<String> values) {
        final Grid grid = new Grid();
        for (int row = 0; row < values.size(); row++) {
            final String line = values.get(row);
            for (int col = 0; col < line.length(); col++) {
                grid.put(Grid.Coordinates.builder()
                        .col(col)
                        .row(row)
                        .build(), line.charAt(col));
            }
        }

        long count = 0;
        for (int row = 0; row < grid.getNbRows(); row++) {
            for (int col = 0; col < grid.getNbCols(); col++) {
                Grid.Coordinates origin = Grid.Coordinates.builder()
                        .col(col)
                        .row(row)
                        .build();

                if (!grid.contains(origin, 'A')) {
                    continue;
                }

                long crossCount = 0L;
                for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
                    for (int deltaCol = -1; deltaCol <= 1; deltaCol++) {
                        if (deltaRow == 0 || deltaCol == 0) {
                            continue;
                        }
                        Grid.Coordinates delta = Grid.Coordinates.builder()
                                .col(deltaCol)
                                .row(deltaRow)
                                .build();
                        Grid.Coordinates mOrigin = origin.moveOf(delta.multiply(-1));
                        if (grid.containsInLine(List.of('M', 'A', 'S'), mOrigin, delta)) {
                            crossCount++;
                        }
                    }
                }
                if (crossCount >= 2) {
                    count++;
                }
            }
        }
        return count;
    }
}

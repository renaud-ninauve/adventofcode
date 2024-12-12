package fr.ninauve.renaud.adventofcode.year2024.day12;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

public class Part02 {

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part02.class
                .getResource("/year2024/day12/input.txt").toURI()), StandardCharsets.UTF_8);
        System.out.println(Part02.solve(input));
    }

    public static long solve(List<String> input) {
        Grid<CellContent> grid = Grid.fromInput(input);
        Collection<Region> regions = Region.fromGrid(grid);
        return regions.stream()
                .mapToLong(region -> region.area() * region.sides().size())
                .sum();
    }
}

package fr.ninauve.renaud.adventofcode.year2023.day02;

import java.util.Map;

public record Cubes(Map<Color, Integer> cubes) {

    public long total() {
        return cubes.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public long totalOfColor(Color color) {
        return cubes.getOrDefault(color, 0);
    }
}

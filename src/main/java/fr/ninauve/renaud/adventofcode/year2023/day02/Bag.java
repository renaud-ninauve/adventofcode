package fr.ninauve.renaud.adventofcode.year2023.day02;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public record Bag(Cubes cubes) {
    public static final Bag DEFAULT = new Bag(new Cubes(Map.of(
            Color.RED, 12,
            Color.GREEN, 13,
            Color.BLUE, 14
    )));

    public boolean areValidDraws(List<Cubes> draws) {
        return draws.stream()
                .allMatch(this::isDrawValid);
    }

    private boolean isDrawValid(Cubes draw) {
        if (cubes.total() < draw.total()) {
            return false;
        }
        return Arrays.stream(Color.values())
                .allMatch(c -> cubes.totalOfColor(c) >= draw.totalOfColor(c));
    }
}

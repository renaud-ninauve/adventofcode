package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import java.util.List;

public class ZeroRule implements Rule {
    @Override
    public List<Number> apply(Number value) {
        return List.of(Number.ONE);
    }

    @Override
    public boolean matches(Number value) {
        return value.equals(Number.ZERO);
    }
}

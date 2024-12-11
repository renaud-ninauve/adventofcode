package fr.ninauve.renaud.adventofcode.year2024.day11.part01;

import java.util.List;

public class ZeroRule implements Rule {
    @Override
    public List<Long> apply(long value) {
        return List.of(1L);
    }

    @Override
    public boolean matches(long value) {
        return value == 0;
    }
}

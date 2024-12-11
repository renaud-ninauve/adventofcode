package fr.ninauve.renaud.adventofcode.year2024.day11;

import java.util.List;

public class DefaultRule implements Rule {
    @Override
    public List<Long> apply(long value) {
        return List.of(value * 2024);
    }

    @Override
    public boolean matches(long value) {
        return true;
    }
}

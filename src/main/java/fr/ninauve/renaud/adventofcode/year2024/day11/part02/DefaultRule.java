package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import java.util.List;

public class DefaultRule implements Rule {
    @Override
    public List<Number> apply(Number value) {
        long newValue = Long.parseLong(value.value()) * 2024L;
        return List.of(new Number(Long.toString(newValue)));
    }

    @Override
    public boolean matches(Number value) {
        return true;
    }
}

package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import java.util.List;

public class EvenDigitsRule implements Rule {
    @Override
    public List<Number> apply(Number value) {
        return value.splitIn2();
    }

    @Override
    public boolean matches(Number value) {
        return value.hasEvenLength();
    }
}

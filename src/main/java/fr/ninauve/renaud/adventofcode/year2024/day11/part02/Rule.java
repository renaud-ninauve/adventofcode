package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import java.util.List;

public interface Rule {
    List<Number> apply(Number value);
    boolean matches(Number value);
}

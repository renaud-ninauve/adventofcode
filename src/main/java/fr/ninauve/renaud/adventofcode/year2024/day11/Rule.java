package fr.ninauve.renaud.adventofcode.year2024.day11;

import java.util.List;

public interface Rule {
    List<Long> apply(long value);
    boolean matches(long value);
}

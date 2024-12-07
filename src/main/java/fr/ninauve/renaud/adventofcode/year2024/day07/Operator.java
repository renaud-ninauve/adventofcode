package fr.ninauve.renaud.adventofcode.year2024.day07;

import java.util.function.BiFunction;

public enum Operator implements BiFunction<Long, Long, Long> {
    ADD, MULTIPLY;

    @Override
    public Long apply(Long a, Long b) {
        return switch (this) {
            case ADD -> a + b;
            case MULTIPLY -> a * b;
        };
    }
}

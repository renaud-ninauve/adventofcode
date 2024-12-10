package fr.ninauve.renaud.adventofcode.year2024.day10;

public record Height(int value) {

    public Height add(Height other) {
        return new Height(value + other.value());
    }
}

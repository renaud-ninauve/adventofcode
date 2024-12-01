package fr.ninauve.renaud.adventofcode.year2024.day01;

public record Pair (long left, long right) {

    public long distance() {
        return Math.abs(right - left);
    }
}

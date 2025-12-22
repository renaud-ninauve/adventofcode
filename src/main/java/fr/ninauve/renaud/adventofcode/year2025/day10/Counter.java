package fr.ninauve.renaud.adventofcode.year2025.day10;

public record Counter(int index, int value) {

    public static Counter incr(Counter counter) {
        return new Counter(counter.index(), counter.value() + 1);
    }

    public boolean lte(Counter other) {
        return value() <= other.value();
    }
}

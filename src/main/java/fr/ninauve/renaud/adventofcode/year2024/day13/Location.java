package fr.ninauve.renaud.adventofcode.year2024.day13;

public record Location(long x, long y) {

    public Location add(Location delta) {
        return new Location(x+delta.x, y+delta.y);
    }

    public boolean greaterThan(Location other) {
        return x > other.x || y > other.y;
    }
}

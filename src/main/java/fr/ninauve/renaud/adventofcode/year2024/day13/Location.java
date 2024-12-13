package fr.ninauve.renaud.adventofcode.year2024.day13;

public record Location(long x, long y) {

    public Location add(Location delta) {
        return new Location(x + delta.x, y + delta.y);
    }

    public Location substract(Location other) {
        return add(other.mult(-1L));
    }

    public boolean greaterThan(Location other) {
        return x > other.x || y > other.y;
    }

    public Location mult(long value) {
        return new Location(x * value, y * value);
    }
}

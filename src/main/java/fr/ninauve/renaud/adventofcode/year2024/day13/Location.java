package fr.ninauve.renaud.adventofcode.year2024.day13;

public record Location(int x, int y) {

    public Location add(Location delta) {
        return new Location(x+delta.x, y+delta.y);
    }

    public boolean greaterThan(Location other) {
        return x > other.x || y > other.y;
    }
}

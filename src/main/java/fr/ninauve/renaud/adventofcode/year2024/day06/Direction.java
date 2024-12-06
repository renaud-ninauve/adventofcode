package fr.ninauve.renaud.adventofcode.year2024.day06;

public enum Direction {
    UP(Coordinates.UP),
    RIGHT(Coordinates.RIGHT),
    DOWN(Coordinates.DOWN),
    LEFT(Coordinates.LEFT);

    private final Coordinates increment;

    Direction(Coordinates increment) {
        this.increment = increment;
    }

    public Direction turn90() {
        return switch (this) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }

    public Coordinates getIncrement() {
        return increment;
    }
}

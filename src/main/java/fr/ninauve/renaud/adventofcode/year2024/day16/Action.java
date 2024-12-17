package fr.ninauve.renaud.adventofcode.year2024.day16;

public enum Action {
    TURN_CLOCKWISE(1000L),
    TURN_COUNTERCLOCKWISE(1000L),
    MOVE_STRAIGHT(1L);

    private final long cost;

    Action(long cost) {
        this.cost = cost;
    }

    public long cost() {
        return cost;
    }
}
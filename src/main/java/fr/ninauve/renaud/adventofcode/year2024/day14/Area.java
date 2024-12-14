package fr.ninauve.renaud.adventofcode.year2024.day14;

public record Area(long width, long height) {

    public Quadrant quadrantOf(Location location) {
        long middleX = width / 2;
        long middleY = height / 2;
        if (location.x() == middleX || location.y() == middleY) {
            return Quadrant.NONE;
        }
        boolean left = location.x() < middleX;
        boolean up = location.y() < middleY;
        if (up && left) {
            return Quadrant.UP_LEFT;
        }
        if (up) {
            return Quadrant.UP_RIGHT;
        }
        if (left) {
            return Quadrant.DOWN_LEFT;
        }
        return Quadrant.DOWN_RIGHT;
    }

    public enum Quadrant {
        UP_LEFT, UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, NONE
    }
}

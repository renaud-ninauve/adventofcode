package fr.ninauve.renaud.adventofcode.year2024.day02;

public class GapValidator implements SafetyValidator {
    private boolean safe = true;
    private Long previousLevel;

    @Override
    public boolean isSafe() {
        return safe;
    }

    @Override
    public void addLevel(long level) {
        if (previousLevel == null) {
            this.previousLevel = level;
            return;
        }

        long gap = Math.abs(level - previousLevel);
        this.safe &= (gap >= 1 && gap <= 3);
        this.previousLevel = level;
    }
}

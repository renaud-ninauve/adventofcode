package fr.ninauve.renaud.adventofcode.year2024.day02;

public class AllDecreasingValidator implements SafetyValidator {
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

        this.safe &= (level < previousLevel);
        this.previousLevel = level;
    }
}

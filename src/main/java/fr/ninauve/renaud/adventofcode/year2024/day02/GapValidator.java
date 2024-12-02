package fr.ninauve.renaud.adventofcode.year2024.day02;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class GapValidator implements SafetyValidator {
    private boolean safe = true;
    private Long previousLevel;

    public static GapValidator copy(GapValidator gapValidator) {
        return new GapValidator(gapValidator.safe, gapValidator.previousLevel);
    }

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

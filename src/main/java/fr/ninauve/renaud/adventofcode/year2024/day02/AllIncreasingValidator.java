package fr.ninauve.renaud.adventofcode.year2024.day02;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class AllIncreasingValidator implements SafetyValidator {
    private boolean safe = true;
    private Long previousLevel;

    public static AllIncreasingValidator copy(AllIncreasingValidator allIncreasingValidator) {
        return new AllIncreasingValidator(allIncreasingValidator.safe, allIncreasingValidator.previousLevel);
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

        this.safe &= (level > previousLevel);
        this.previousLevel = level;
    }
}

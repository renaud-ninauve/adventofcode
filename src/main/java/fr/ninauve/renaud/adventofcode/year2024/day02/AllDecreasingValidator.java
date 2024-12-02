package fr.ninauve.renaud.adventofcode.year2024.day02;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class AllDecreasingValidator implements SafetyValidator {
    private boolean safe = true;
    private Long previousLevel;

    public static AllDecreasingValidator copy(AllDecreasingValidator allDecreasingValidator) {
        return new AllDecreasingValidator(allDecreasingValidator.safe, allDecreasingValidator.previousLevel);
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

        this.safe &= (level < previousLevel);
        this.previousLevel = level;
    }
}

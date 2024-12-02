package fr.ninauve.renaud.adventofcode.year2024.day02;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AllSafetyValidator implements SafetyValidator {
    private final AllIncreasingValidator allIncreasingValidator;
    private final AllDecreasingValidator allDecreasingValidator;
    private final GapValidator gapValidator;

    public static AllSafetyValidator copy(AllSafetyValidator allValidator) {
        return new AllSafetyValidator(
                AllIncreasingValidator.copy(allValidator.allIncreasingValidator),
                AllDecreasingValidator.copy(allValidator.allDecreasingValidator),
                GapValidator.copy(allValidator.gapValidator));
    }

    public AllSafetyValidator() {
        this.allIncreasingValidator = new AllIncreasingValidator();
        this.allDecreasingValidator = new AllDecreasingValidator();
        this.gapValidator = new GapValidator();
    }

    @Override
    public boolean isSafe() {
        return (allIncreasingValidator.isSafe() || allDecreasingValidator.isSafe()) && gapValidator.isSafe();
    }

    @Override
    public void addLevel(long level) {
        allIncreasingValidator.addLevel(level);
        allDecreasingValidator.addLevel(level);
        gapValidator.addLevel(level);
    }
}

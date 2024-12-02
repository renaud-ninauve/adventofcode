package fr.ninauve.renaud.adventofcode.year2024.day02;

public class AllSafetyValidator implements SafetyValidator {
    private AllIncreasingValidator allIncreasingValidator = new AllIncreasingValidator();
    private AllDecreasingValidator allDecreasingValidator = new AllDecreasingValidator();
    private GapValidator gapValidator = new GapValidator();

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

package fr.ninauve.renaud.adventofcode.year2024.day02;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part02 {

    public static void main(String... args) throws Exception {
        BufferedReader reader = Files.newBufferedReader(Path.of(fr.ninauve.renaud.adventofcode.year2024.day01.Part02.class
                .getResource("/fr/ninauve/renaud/adventofcode/year2024/day02/input.txt").toURI()), StandardCharsets.UTF_8);

        long count = reader.lines()
                .map(Part02::isSafe)
                .filter(safe -> safe)
                .count();

        System.out.println(count);
    }

    private static boolean isSafe(String line) {
        Scanner scanner = new Scanner(line);
        List<SafetyValidator> safetyValidators = new ArrayList<>();
        AllSafetyValidator allLevelsValidator = new AllSafetyValidator();
        safetyValidators.add(allLevelsValidator);

        while(scanner.hasNextLong()) {
            long nextLevel = scanner.nextLong();
            AllSafetyValidator withoutLevel = AllSafetyValidator.copy(allLevelsValidator);
            for(SafetyValidator safetyValidator: safetyValidators) {
                safetyValidator.addLevel(nextLevel);
            }
            safetyValidators.add(withoutLevel);
        }
        return safetyValidators.stream().anyMatch(SafetyValidator::isSafe);
    }
}

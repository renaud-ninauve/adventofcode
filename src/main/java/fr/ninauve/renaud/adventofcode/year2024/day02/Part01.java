package fr.ninauve.renaud.adventofcode.year2024.day02;

import fr.ninauve.renaud.adventofcode.year2024.day01.Part02;

import java.io.BufferedReader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Part01 {

    public static void main(String... args) throws Exception {
        BufferedReader reader = Files.newBufferedReader(Path.of(Part02.class
                .getResource("/year2024/day02/input.txt").toURI()), StandardCharsets.UTF_8);

        long count = reader.lines()
                .map(Part01::isSafe)
                .filter(safe -> safe)
                .count();

        System.out.println(count);
    }

    private static boolean isSafe(String line) {
        Scanner scanner = new Scanner(line);
        SafetyValidator safetyValidator = new AllSafetyValidator();
        while(scanner.hasNextLong()) {
            safetyValidator.addLevel(scanner.nextLong());
        }
        return safetyValidator.isSafe();
    }
}

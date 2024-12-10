package fr.ninauve.renaud.adventofcode.year2023.day01;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part01 {
    private static final Pattern NUMBER = Pattern.compile("\\d");

    public static void main(String... args) throws Exception {
        List<String> input = Files.readAllLines(Path.of(fr.ninauve.renaud.adventofcode.year2024.day01.Part01.class
                .getResource("/year2023/day01/input.txt").toURI()));
        System.out.println(Part01.solve(input));
    }

    public static long solve(List<String> input) {
        return calibrationValues(input)
                .stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    public static List<Long> calibrationValues(List<String> input) {
        return input.stream()
                .map(Part01::calibrationValue)
                .toList();
    }

    public static long calibrationValue(String line) {
        Matcher matcher = NUMBER.matcher(line);
        matcher.find();
        String firstDigit = matcher.group(0);
        String secondDigit = firstDigit;
        while (matcher.find()) {
            secondDigit = matcher.group(0);
        }
        return Long.parseLong(firstDigit + secondDigit);
    }
}

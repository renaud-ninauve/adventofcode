package fr.ninauve.renaud.adventofcode.year2023.day01;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part02 {
    private static final Map<String, String> NUMBERS = new HashMap<>();

    static {
        NUMBERS.put("one", "1");
        NUMBERS.put("two", "2");
        NUMBERS.put("three", "3");
        NUMBERS.put("four", "4");
        NUMBERS.put("five", "5");
        NUMBERS.put("six", "6");
        NUMBERS.put("seven", "7");
        NUMBERS.put("eight", "8");
        NUMBERS.put("nine", "9");
    }

    private static final Pattern NUMBER = Pattern.compile(Stream.concat(
                    Stream.of("\\d"),
                    NUMBERS.keySet().stream()
            ).map(str -> "(" + str + ")")
            .collect(Collectors.joining("|")));

    public static void main(String... args) throws Exception {
        List<String> input = Files.readAllLines(Path.of(Part02.class
                .getResource("/year2023/day01/input.txt").toURI()));
        System.out.println(Part02.solve(input));
    }

    public static long solve(List<String> input) {
        return calibrationValues(input)
                .stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    public static List<Long> calibrationValues(List<String> input) {
        return input.stream()
                .map(Part02::calibrationValue)
                .toList();
    }

    public static long calibrationValue(String line) {
        Matcher matcher = NUMBER.matcher(line);
        matcher.find();
        String firstMatch = matcher.group(0);
        String firstDigit = digitFromMatch(firstMatch);
        String secondDigit = firstDigit;
        int findFrom = matcher.start() + 1;
        while (matcher.find(findFrom)) {
            String secondMatch = matcher.group(0);
            secondDigit = digitFromMatch(secondMatch);
            findFrom++;
        }
        return Long.parseLong(firstDigit + secondDigit);
    }

    private static String digitFromMatch(String match) {
        if (NUMBERS.containsKey(match)) {
            return NUMBERS.get(match);
        }
        return match;
    }
}

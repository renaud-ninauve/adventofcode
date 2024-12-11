package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public record Number(String value) {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("0*(\\d+)");

    public static final Number ZERO = new Number("0");
    public static final Number ONE = new Number("1");

    public List<Number> splitIn2() {
        int halfLength = value.length() / 2;
        String firstHalf = value.substring(0, halfLength);
        String secondtHalf = value.substring(halfLength);
        return Stream.of(firstHalf, secondtHalf)
                .map(this::stripZeros)
                .map(Number::new)
                .toList();
    }

    public boolean hasEvenLength() {
        return value.length() % 2 == 0;
    }

    private String stripZeros(String value) {
        Matcher matcher = NUMBER_PATTERN.matcher(value);
        matcher.find();
        return matcher.group(1);
    }
}

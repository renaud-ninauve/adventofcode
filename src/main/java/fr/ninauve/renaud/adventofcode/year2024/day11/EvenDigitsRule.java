package fr.ninauve.renaud.adventofcode.year2024.day11;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class EvenDigitsRule implements Rule {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("0*(\\d+)");

    @Override
    public List<Long> apply(long value) {
        String valueString = Long.toString(value);
        int halfLength = valueString.length() / 2;
        String firstHalf = valueString.substring(0, halfLength);
        String secondHalf = valueString.substring(halfLength);
        return Stream.of(firstHalf, secondHalf)
                .map(this::parse)
                .toList();
    }

    @Override
    public boolean matches(long value) {
        return Long.toString(value).length() % 2 == 0;
    }

    private long parse(String value) {
        Matcher matcher = NUMBER_PATTERN.matcher(value);
        matcher.find();
        return Long.parseLong(matcher.group(1));
    }
}

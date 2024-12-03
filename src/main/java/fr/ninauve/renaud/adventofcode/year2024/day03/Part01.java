package fr.ninauve.renaud.adventofcode.year2024.day03;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part01 {
    public static final Pattern MULTIPLY_PATTERN = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");

    public static void main(String... args) throws Exception {
        final String input = Files.readString(Path.of(Part01.class
                .getResource("/year2024/day03/input.txt").toURI()), StandardCharsets.UTF_8);


        Matcher matcher = Part01.MULTIPLY_PATTERN.matcher(input);
        long sum = 0;
        while(matcher.find()) {
            long a = Long.valueOf(matcher.group(1));
            long b = Long.valueOf(matcher.group(2));
            sum += (a*b);
        }

        System.out.println(sum);
    }
}

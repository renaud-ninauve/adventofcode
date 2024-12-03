package fr.ninauve.renaud.adventofcode.year2024.day03;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part02 {
    private static final String DO_INSTRUCTION = "do()";
    private static final String DONT_INSTRUCTION = "don't()";

    private static final String DO = "do\\(\\)";
    private static final String DONT = "don't\\(\\)";
    private static final String MULTIPLY = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
    private static final String INSTRUCTIONS = Stream.of(DO, DONT, MULTIPLY)
            .map(str -> "(" + str + ")")
            .collect(Collectors.joining("|"));

    public static Pattern INSTRUCTIONS_PATTERN = Pattern.compile(INSTRUCTIONS);

    public static void main(String... args) throws Exception {
        final String input = Files.readString(Path.of(Part02.class
                .getResource("/year2024/day03/input.txt").toURI()), StandardCharsets.UTF_8);


        Matcher matcher = Part02.INSTRUCTIONS_PATTERN.matcher(input);
        long sum = 0;
        boolean enabled = true;
        while(matcher.find()) {
            switch(matcher.group()) {
                case DO_INSTRUCTION: {
                    enabled = true;
                    break;
                }
                case DONT_INSTRUCTION: {
                    enabled = false;
                    break;
                }
                default: {
                    if (enabled) {
                        long a = Long.valueOf(matcher.group(4));
                        long b = Long.valueOf(matcher.group(5));
                        sum += (a*b);
                    }
                    break;
                }
            }
        }

        System.out.println(sum);
    }
}

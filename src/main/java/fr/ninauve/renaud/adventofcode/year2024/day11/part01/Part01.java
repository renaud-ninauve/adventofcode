package fr.ninauve.renaud.adventofcode.year2024.day11.part01;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Part01 {
    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part01.class
                .getResource("/year2024/day11/input.txt").toURI()), StandardCharsets.UTF_8);

        Rules rules = new Rules();
        List<Long> values = Part01.parse(input.getFirst());
        for(int i=0; i< 25; i++) {
            values = rules.apply(values);
        }
        System.out.println(values.size());
    }

    public static List<Long> parse(String input) {
        return Arrays.stream(input.split(" "))
                .map(Long::parseLong)
                .toList();
    }
}

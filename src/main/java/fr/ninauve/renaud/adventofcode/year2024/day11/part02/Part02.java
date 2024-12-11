package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Part02 {
    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part02.class
                .getResource("/year2024/day11/input.txt").toURI()), StandardCharsets.UTF_8);

        final Rules rules = new Rules();
        Numbers numbers = Numbers.from(input.getFirst());
        for (int i = 0; i < 75; i++) {
            numbers = rules.apply(numbers);
        }
        System.out.println(numbers.size());
    }
}

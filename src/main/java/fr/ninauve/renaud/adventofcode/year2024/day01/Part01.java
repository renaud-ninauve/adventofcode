package fr.ninauve.renaud.adventofcode.year2024.day01;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part01 {

    public static void main(String... args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of(Part01.class
                .getResource("/fr/ninauve/renaud/adventofcode/year2024/day01/input.txt").toURI()));

        List<Long> left = new ArrayList<>();
        List<Long> right = new ArrayList<>();

        for (String line : lines) {
            String[] splitted = line.split("\\s+");
            if (splitted.length < 2) {
                continue;
            }
            left.add(Long.parseLong(splitted[0]));
            right.add(Long.parseLong(splitted[1]));
        }

        Collections.sort(left);
        Collections.sort(right);

        long sum = 0;
        for (int i = 0; i < left.size(); i++) {
            Long leftValue = left.get(i);
            Long rightValue = right.get(i);
            long distance = Math.abs(rightValue - leftValue);
            sum += distance;
        }

        System.out.println(sum);
    }
}

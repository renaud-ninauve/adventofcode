package fr.ninauve.renaud.adventofcode.year2024.day01;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part02 {

    public static void main(String... args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of(Part02.class
                .getResource("/year2024/day01/input.txt").toURI()));

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

        final Map<Long, Long> rightFreq = right.stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long sum = 0;
        for (int i = 0; i < left.size(); i++) {
            Long leftValue = left.get(i);
            Long freq = rightFreq.getOrDefault(leftValue, 0L);
            long similarity = leftValue * freq;
            sum += similarity;
        }

        System.out.println(sum);
    }
}

package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Numbers {
    private final Map<Number, Long> counts;

    public record NumberCount(Number number, Long count) {
    }

    public static Numbers from(String input) {
        Map<Number, Long> counts = Arrays.stream(input.split(" "))
                .map(Number::new)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return new Numbers(counts);
    }

    public Numbers() {
        this.counts = new HashMap<>();
    }

    public Collection<NumberCount> counts() {
        return counts.entrySet().stream()
                .map(e -> new NumberCount(e.getKey(), e.getValue()))
                .toList();
    }

    public void add(NumberCount numberCount) {
        Long currentCount = counts.getOrDefault(numberCount.number, 0L);
        counts.put(numberCount.number(), currentCount + numberCount.count());
    }

    public long size() {
        return counts.values().stream()
                .mapToLong(Long::longValue)
                .sum();
    }
}

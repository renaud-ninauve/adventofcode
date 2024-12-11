package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class RulesTest {

    @Test
    void apply1() {
        Numbers numbers = Numbers.from("125 17");
        Rules rules = new Rules();
        for (int i = 0; i < 1; i++) {
            numbers = rules.apply(numbers);
        }

        assertNumbers(numbers, List.of("253000", "1", "7"));
    }

    @Test
    void apply6() {
        Numbers numbers = Numbers.from("125 17");
        Rules rules = new Rules();
        for (int i = 0; i < 6; i++) {
            numbers = rules.apply(numbers);
        }

        assertNumbers(numbers, List.of("2097446912", "14168", "4048", "2", "0", "2", "4", "40", "48", "2024", "40", "48", "80", "96", "2", "8", "6", "7", "6", "0", "3", "2"));
    }

    @Test
    void apply25() {
        Numbers numbers = Numbers.from("125 17");
        Rules rules = new Rules();
        for (int i = 0; i < 25; i++) {
            numbers = rules.apply(numbers);
        }

        assertThat(numbers.size()).isEqualTo(55312L);
    }

    private void assertNumbers(Numbers actual, List<String> expected) {
        Map<Number, Long> expectedCounts = expected.stream()
                .map(Number::new)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Numbers.NumberCount> expectedNumberCounts = expectedCounts.entrySet().stream().map(e -> new Numbers.NumberCount(e.getKey(), e.getValue())).toList();
        assertThat(actual.counts()).containsAll(expectedNumberCounts);
    }
}
package fr.ninauve.renaud.adventofcode.year2024.day05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {
    private Part02 part02;

    @BeforeEach
    void setUp() {
        part02 = Part02.parse(List.of(
                "47|53",
                "97|13",
                "97|61",
                "97|47",
                "75|29",
                "61|13",
                "75|53",
                "29|13",
                "97|29",
                "53|29",
                "61|53",
                "97|53",
                "61|29",
                "47|13",
                "75|47",
                "97|75",
                "47|61",
                "75|61",
                "47|29",
                "75|13",
                "53|13",
                "",
                "75,47,61,53,29",
                "97,61,53,29,13",
                "75,29,13",
                "75,97,47,61,53",
                "61,13,29",
                "97,13,75,29,47"
        ));
    }

    static Stream<Arguments> fix() {
        return Stream.of(
                Arguments.of(List.of(75L, 97L, 47L, 61L, 53L), List.of(97L, 75L, 47L, 61L, 53L)),
                Arguments.of(List.of(61L, 13L, 29L), List.of(61L, 29L, 13L)),
                Arguments.of(List.of(97L, 13L, 75L, 29L, 47L), List.of(97L, 75L, 47L, 29L, 13L))
        );
    }

    @ParameterizedTest
    @MethodSource
    void fix(List<Long> pages,
             List<Long> expected) {

        List<Long> actual = part02.fix(pages);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void sum() {
        assertThat(part02.printQueue()).isEqualTo(123L);
    }
}
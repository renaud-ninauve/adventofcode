package fr.ninauve.renaud.adventofcode.year2024.day04;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {

    static Stream<Arguments> xmas() {
        return Stream.of(
                Arguments.of("basic", List.of(
                        "M.S",
                        ".A.",
                        "M.S"
                ), 1L),
                Arguments.of("advent of code", List.of(
                        ".M.S......",
                        "..A..MSMS.",
                        ".M.S.MAA..",
                        "..A.ASMSM.",
                        ".M.S.M....",
                        "..........",
                        "S.S.S.S.S.",
                        ".A.A.A.A..",
                        "M.M.M.M.M.",
                        ".........."
                ), 9L)
        );
    }

    @ParameterizedTest
    @MethodSource
    void xmas(String titile, List<String> values, long expected) {
        long actual = new Part02().xmas(values);
        assertThat(actual).isEqualTo(expected);
    }
}
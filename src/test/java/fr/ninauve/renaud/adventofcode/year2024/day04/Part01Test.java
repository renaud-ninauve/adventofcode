package fr.ninauve.renaud.adventofcode.year2024.day04;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {

    static Stream<Arguments> xmas() {
        return Stream.of(
                Arguments.of("left -> right", List.of(
                        "XMAS"
                ), 1L),
                Arguments.of("right -> left", List.of(
                        "SAMX"
                ), 1L),
                Arguments.of("top -> down", List.of(
                        "X",
                        "M",
                        "A",
                        "S"
                ), 1L),
                Arguments.of("down -> up", List.of(
                        "S",
                        "A",
                        "M",
                        "X"
                ), 1L),
                Arguments.of("diagonal up right", List.of(
                        "...S",
                        "..A.",
                        ".M..",
                        "X..."
                ), 1L),
                Arguments.of("diagonal bottom right", List.of(
                        "X...",
                        ".M..",
                        "..A.",
                        "...S"
                ), 1L),
                Arguments.of("diagonal down left", List.of(
                        "...X",
                        "..M.",
                        ".A..",
                        "S..."
                ), 1L),
                Arguments.of("diagonal up left", List.of(
                        "S...",
                        ".A..",
                        "..M.",
                        "...X"
                ), 1L),
                Arguments.of("advent of code", List.of(
                        "MMMSXXMASM",
                        "MSAMXMSMSA",
                        "AMXSXMAAMM",
                        "MSAMASMSMX",
                        "XMASAMXAMM",
                        "XXAMMXXAMA",
                        "SMSMSASXSS",
                        "SAXAMASAAA",
                        "MAMMMXMMMM",
                        "MXMXAXMASX"
                ), 18L)
        );
    }

    @ParameterizedTest
    @MethodSource
    void xmas(String titile, List<String> values, long expected) {
        long actual = new Part01().xmas(values);
        assertThat(actual).isEqualTo(expected);
    }
}
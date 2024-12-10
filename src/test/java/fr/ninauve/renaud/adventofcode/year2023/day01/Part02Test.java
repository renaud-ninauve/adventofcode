package fr.ninauve.renaud.adventofcode.year2023.day01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {

    @Test
    void calibrationValue() {
        long actual = Part02.calibrationValue("two1nine");
        assertThat(actual).isEqualTo(29L);
    }

    @Test
    void calibrationValue2() {
        long actual = Part02.calibrationValue("sjpqvfmx9twonep");
        assertThat(actual).isEqualTo(91L);
    }

    @Test
    void calibrationValues() {
        List<Long> actual = Part02.calibrationValues(List.of(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen"
        ));
        assertThat(actual).isEqualTo(List.of(
                29L, 83L, 13L, 24L, 42L, 14L, 76L
        ));
    }

    @Test
    void solve() {
        long actual = Part02.solve(List.of(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen"
        ));
        assertThat(actual).isEqualTo(281L);
    }
}
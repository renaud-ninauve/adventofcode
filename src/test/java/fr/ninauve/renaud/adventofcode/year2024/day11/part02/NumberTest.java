package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NumberTest {

    @ParameterizedTest
    @CsvSource(delimiter = ' ', value = {"12 1 2", "1234 12 34", "1002 10 2", "1000 10 0"})
    void splitIn2(String value, String expected1, String expected2) {
        List<Number> actual = new Number(value).splitIn2();
        assertThat(actual).containsExactly(
                new Number(expected1),
                new Number(expected2)
        );
    }

    @ParameterizedTest
    @CsvSource(delimiter = ' ', value = {"1 false", "12 true", "123 false", "1234 true"})
    void hasEvenLength(String value, boolean expected) {
        boolean actual = new Number(value).hasEvenLength();
        assertThat(actual).isEqualTo(expected);
    }
}
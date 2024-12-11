package fr.ninauve.renaud.adventofcode.year2024.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultRuleTest {

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {"1;2024", "2;4048"})
    void apply(long value, long expected) {
        List<Long> actual = new DefaultRule().apply(value);
        assertThat(actual).containsExactly(expected);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {"42;true", "1;true"})
    void matches(long value, boolean expected) {
        boolean actual = new DefaultRule().matches(value);
        assertThat(actual).isEqualTo(expected);
    }
}
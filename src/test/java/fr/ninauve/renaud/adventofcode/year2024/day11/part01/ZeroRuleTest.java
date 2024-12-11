package fr.ninauve.renaud.adventofcode.year2024.day11.part01;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ZeroRuleTest {

    @ParameterizedTest
    @CsvSource(delimiter = ';', value= {"0;1"})
    void apply(long value, long expected) {
        List<Long> actual = new ZeroRule().apply(value);
        assertThat(actual).containsExactly(expected);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value= {"0;true", "1;false", "2;false", "10;false"})
    void matches(long value, boolean expected) {
        boolean actual = new ZeroRule().matches(value);
        assertThat(actual).isEqualTo(expected);
    }
}
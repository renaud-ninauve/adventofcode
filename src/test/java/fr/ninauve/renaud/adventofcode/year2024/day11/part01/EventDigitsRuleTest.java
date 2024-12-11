package fr.ninauve.renaud.adventofcode.year2024.day11.part01;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EventDigitsRuleTest {

    @ParameterizedTest
    @CsvSource(delimiter = ';', value= {"12;1;2", "1234;12;34", "1000;10;0", "1001;10;1"})
    void apply(long value, long expected1, long expected2) {
        List<Long> actual = new EvenDigitsRule().apply(value);
        assertThat(actual).containsExactly(expected1, expected2);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value= {"12;true", "1;false", "123;false"})
    void matches(long value, boolean expected) {
        boolean actual = new EvenDigitsRule().matches(value);
        assertThat(actual).isEqualTo(expected);
    }
}
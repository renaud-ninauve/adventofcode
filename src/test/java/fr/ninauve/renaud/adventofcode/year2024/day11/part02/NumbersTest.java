package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import fr.ninauve.renaud.adventofcode.year2024.day11.part02.Numbers.NumberCount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumbersTest {

    @Test
    void add() {
        Numbers numbers = new Numbers();
        numbers.add(new NumberCount(new Number("3"), 2L));
        numbers.add(new NumberCount(new Number("3"), 3L));
        assertThat(numbers.counts()).containsExactly(
                new NumberCount(new Number("3"), 5L)
        );
    }
}
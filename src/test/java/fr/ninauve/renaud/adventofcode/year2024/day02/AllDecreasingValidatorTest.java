package fr.ninauve.renaud.adventofcode.year2024.day02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AllDecreasingValidatorTest {

    static Stream<Arguments> isSafe() {
        return Stream.of(
                Arguments.of(List.of(1L, 2L, 3L), false),
                Arguments.of(List.of(3L, 2L, 1L), true),
                Arguments.of(List.of(3L, 1L, 2L), false),
                Arguments.of(List.of(1L, 3L, 2L), false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void isSafe(List<Long> levels, boolean expected) {
        SafetyValidator safetyValidator = new AllDecreasingValidator();
        for (long level : levels) {
            safetyValidator.addLevel(level);
        }

        boolean actual = safetyValidator.isSafe();

        assertThat(actual).isEqualTo(expected);
    }
}
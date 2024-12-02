package fr.ninauve.renaud.adventofcode.year2024.day02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GapValidatorTest {

    static Stream<Arguments> isSafe() {
        return Stream.of(
                Arguments.of(List.of(1L, 2L, 3L), true),
                Arguments.of(List.of(3L, 2L, 1L), true),
                Arguments.of(List.of(1L, 1L, 2L), false),
                Arguments.of(List.of(1L, 4L, 6L), true),
                Arguments.of(List.of(6L, 4L, 1L, 2L), true),
                Arguments.of(List.of(1L, 4L, 8L), false),
                Arguments.of(List.of(8L, 4L, 1L), false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void isSafe(List<Long> levels, boolean expected) {
        SafetyValidator safetyValidator = new GapValidator();
        for (long level : levels) {
            safetyValidator.addLevel(level);
        }

        boolean actual = safetyValidator.isSafe();

        assertThat(actual).isEqualTo(expected);
    }
}
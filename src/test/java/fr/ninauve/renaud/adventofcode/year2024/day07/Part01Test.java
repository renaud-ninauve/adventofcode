package fr.ninauve.renaud.adventofcode.year2024.day07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {

    static Stream<Arguments> expressionsWithAresultOf() {
        return Stream.of(
                Arguments.of(190L, List.of(10L, 19L),
                        List.of(new Expression(List.of(10L, 19L), List.of(Operator.MULTIPLY)))),
                Arguments.of(3267L, List.of(81L, 40L, 27L), List.of(
                        new Expression(List.of(81L, 40L, 27L), List.of(Operator.ADD, Operator.MULTIPLY)),
                        new Expression(List.of(81L, 40L, 27L), List.of(Operator.MULTIPLY, Operator.ADD))
                )),
                Arguments.of(292L, List.of(11L, 6L, 16L, 20L),
                        List.of(new Expression(List.of(11L, 6L, 16L, 20L), List.of(Operator.ADD, Operator.MULTIPLY, Operator.ADD)))),
                Arguments.of(83L, List.of(17L, 5L),
                        List.of())
        );
    }

    @ParameterizedTest
    @MethodSource
    void expressionsWithAresultOf(long result, List<Long> numbers, List<Expression> expected) {
        List<Expression> actual = Part01.expressionsWithAresultOf(result, numbers);
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void solve() {
        Part01 part01 = Part01.parse(List.of(
                "190: 10 19",
                "3267: 81 40 27",
                "83: 17 5",
                "156: 15 6",
                "7290: 6 8 6 15",
                "161011: 16 10 13",
                "192: 17 8 14",
                "21037: 9 7 18 13",
                "292: 11 6 16 20"
        ));
        long actual = part01.solve();
        assertThat(actual).isEqualTo(3749L);
    }
}
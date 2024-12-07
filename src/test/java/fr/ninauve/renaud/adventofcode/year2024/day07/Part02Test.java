package fr.ninauve.renaud.adventofcode.year2024.day07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {

    static Stream<Arguments> expressionsWithAresultOf() {
        return Stream.of(
                Arguments.of(156L, List.of(15L, 6L),
                        List.of(new Expression(List.of(15L, 6L), List.of(Operator.CONCAT)))),
                Arguments.of(7290L, List.of(6L, 8L, 6L, 15L), List.of(
                        new Expression(List.of(6L, 8L, 6L, 15L), List.of(Operator.MULTIPLY, Operator.CONCAT, Operator.MULTIPLY)
                        ))),
                Arguments.of(192L, List.of(17L, 8L, 14L),
                        List.of(new Expression(List.of(17L, 8L, 14L), List.of(Operator.CONCAT, Operator.ADD))))
        );
    }

    @ParameterizedTest
    @MethodSource
    void expressionsWithAresultOf(long result, List<Long> numbers, List<Expression> expected) {
        List<Expression> actual = Part02.expressionsWithAresultOf(result, numbers);
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void solve() {
        Part02 part02 = Part02.parse(List.of(
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
        long actual = part02.solve();
        assertThat(actual).isEqualTo(11387L);
    }
}
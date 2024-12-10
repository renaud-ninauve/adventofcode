package fr.ninauve.renaud.adventofcode.year2023.day02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BagTest {

    static Stream<Arguments> drawsValid() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Cubes(Map.of(
                                        Color.RED, 4,
                                        Color.BLUE, 3
                                )),
                                new Cubes(Map.of(
                                        Color.RED, 1,
                                        Color.GREEN, 2,
                                        Color.BLUE, 6
                                )),
                                new Cubes(Map.of(
                                        Color.BLUE, 2
                                ))),
                        true
                ),
                Arguments.of(
                        List.of(
                                new Cubes(Map.of(
                                        Color.BLUE, 1,
                                        Color.GREEN, 2
                                )),
                                new Cubes(Map.of(
                                        Color.RED, 1,
                                        Color.GREEN, 3,
                                        Color.BLUE, 4
                                )),
                                new Cubes(Map.of(
                                        Color.BLUE, 1,
                                        Color.GREEN, 1
                                ))),
                        true
                ),
                Arguments.of(
                        List.of(
                                new Cubes(Map.of(
                                        Color.RED, 20,
                                        Color.BLUE, 6,
                                        Color.GREEN, 8
                                )),
                                new Cubes(Map.of(
                                        Color.RED, 4,
                                        Color.BLUE, 5,
                                        Color.GREEN, 13
                                )),
                                new Cubes(Map.of(
                                        Color.RED, 1,
                                        Color.GREEN, 5
                                ))),
                        false
                ),
                Arguments.of(
                        List.of(
                                new Cubes(Map.of(
                                        Color.RED, 3,
                                        Color.BLUE, 6,
                                        Color.GREEN, 1
                                )),
                                new Cubes(Map.of(
                                        Color.RED, 6,
                                        Color.GREEN, 3
                                )),
                                new Cubes(Map.of(
                                        Color.RED, 14,
                                        Color.BLUE, 15,
                                        Color.GREEN, 3
                                ))),
                        false
                ), // 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
                Arguments.of(
                        List.of(
                                new Cubes(Map.of(
                                        Color.RED, 6,
                                        Color.BLUE, 1,
                                        Color.GREEN, 3
                                )),
                                new Cubes(Map.of(
                                        Color.RED, 1,
                                        Color.BLUE, 2,
                                        Color.GREEN, 2
                                ))),
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void drawsValid(List<Cubes> cubes, boolean expected) {
        boolean actual = Bag.DEFAULT.areValidDraws(cubes);
        assertThat(actual).isEqualTo(expected);
    }
}
package fr.ninauve.renaud.adventofcode.year2024.day12;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RegionSidesTest {

    static Stream<Arguments> sides() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                "....AAA...",
                                "....A.A...",
                                "....A.A...",
                                "....AAA..."
                        ),
                        8
                ),
                Arguments.of(
                        List.of(
                                ".........A",
                                "......AAAA",
                                "...AAAA...",
                                ".AAA......"
                        ),
                        14
                ),
                Arguments.of(
                        List.of(
                                "A"
                        ),
                        4
                ),
                Arguments.of(
                        List.of(
                                "....A.A...",
                                "....A.A...",
                                "....A.A...",
                                "....AAA..."
                        ),
                        8
                ),
                Arguments.of(
                        List.of(
                                "AA",
                                "AA"
                        ),
                        4
                ),
                Arguments.of(
                        List.of(
                                ".....A....",
                                "....AAA...",
                                "...AA.AA..",
                                "....AAA...",
                                ".....A...."
                        ),
                        21
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void sides(List<String> input, int expectedSides) {
        Grid<CellContent> grid = Grid.fromInput(input);

        Collection<Region> actual = Region.fromGrid(grid);

        Region actualA = actual.stream().filter(region -> region.content().value().equals("A")).findFirst().orElseThrow();
        assertThat(actualA.sides()).hasSize(expectedSides);
    }
}
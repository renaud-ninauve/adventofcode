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

class RegionTest {

    @Test
    void fromGrid() {
        Grid<CellContent> grid = Grid.fromInput(List.of(
                "AAAA",
                "BBCD",
                "BBCC",
                "EEEC"
        ));

        Collection<Region> actual = Region.fromGrid(grid);

        assertThat(actual).containsExactlyInAnyOrder(
                new Region(
                        new CellContent("A"),
                        Set.of(
                                new Coordinates(0, 0),
                                new Coordinates(0, 1),
                                new Coordinates(0, 2),
                                new Coordinates(0, 3)
                        )),
                new Region(
                        new CellContent("B"),
                        Set.of(
                                new Coordinates(1, 0),
                                new Coordinates(1, 1),
                                new Coordinates(2, 0),
                                new Coordinates(2, 1)
                        )),
                new Region(
                        new CellContent("C"),
                        Set.of(
                                new Coordinates(1, 2),
                                new Coordinates(2, 2),
                                new Coordinates(2, 3),
                                new Coordinates(3, 3)
                        )),
                new Region(
                        new CellContent("D"),
                        Set.of(
                                new Coordinates(1, 3)
                        )),
                new Region(
                        new CellContent("E"),
                        Set.of(
                                new Coordinates(3, 0),
                                new Coordinates(3, 1),
                                new Coordinates(3, 2)
                        ))
        );
    }

    @Test
    void fromGrid2() {
        Grid<CellContent> grid = Grid.fromInput(List.of(
                "RRRRIICCFF",
                "RRRRIICCCF",
                "VVRRRCCFFF",
                "VVRCCCJFFF",
                "VVVVCJJCFE",
                "VVIVCCJJEE",
                "VVIIICJJEE",
                "MIIIIIJJEE",
                "MIIISIJEEE",
                "MMMISSJEEE"
        ));

        Collection<Region> actual = Region.fromGrid(grid);

        Map<CellContent, List<Long>> areas = actual.stream().collect(Collectors.groupingBy(Region::content, Collectors.mapping(Region::area, Collectors.toList())));

        assertThat(areas.get(new CellContent("R"))).containsExactlyInAnyOrder(12L);
        assertThat(areas.get(new CellContent("I"))).containsExactlyInAnyOrder(4L, 14L);
        assertThat(areas.get(new CellContent("C"))).containsExactlyInAnyOrder(14L, 1L);
        assertThat(areas.get(new CellContent("F"))).containsExactlyInAnyOrder(10L);
        assertThat(areas.get(new CellContent("V"))).containsExactlyInAnyOrder(13L);
        assertThat(areas.get(new CellContent("J"))).containsExactlyInAnyOrder(11L);
        assertThat(areas.get(new CellContent("E"))).containsExactlyInAnyOrder(13L);
        assertThat(areas.get(new CellContent("M"))).containsExactlyInAnyOrder(5L);
        assertThat(areas.get(new CellContent("S"))).containsExactlyInAnyOrder(3L);
    }

    static Stream<Arguments> perimeter() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                "....AAA...",
                                "....A.A...",
                                "....A.A...",
                                "....AAA..."
                        ),
                        10L,
                        20L
                ),
                Arguments.of(
                        List.of(
                                ".........A",
                                "......AAAA",
                                "...AAAA...",
                                ".AAA......"
                        ),
                        12L,
                        26L
                ),
                Arguments.of(
                        List.of(
                                "A"
                        ),
                        1L,
                        4L
                ),
                Arguments.of(
                        List.of(
                                "....A.A...",
                                "....A.A...",
                                "....A.A...",
                                "....AAA..."
                        ),
                        9L,
                        20L
                ),
                Arguments.of(
                        List.of(
                                "AA",
                                "AA"
                        ),
                        4L,
                        8L
                ),
                Arguments.of(
                        List.of(
                                ".....A....",
                                "....AAA...",
                                "...AA.AA..",
                                "....AAA...",
                                ".....A...."
                        ),
                        12L,
                        24L
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void perimeter(List<String> input, long expectedArea, long expectedPerimiter) {
        Grid<CellContent> grid = Grid.fromInput(input);

        Collection<Region> actual = Region.fromGrid(grid);

        Region actualA = actual.stream().filter(region -> region.content().value().equals("A")).findFirst().orElseThrow();
        assertThat(actualA).extracting(Region::area).isEqualTo(expectedArea);
        assertThat(actualA).extracting(Region::perimeter).isEqualTo(expectedPerimiter);
    }

    static Stream<Arguments> regionsCount() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                "RRRRIICCFF",
                                "RRRRIICCCF",
                                "VVRRRCCFFF",
                                "VVRCCCJFFF",
                                "VVVVCJJCFE",
                                "VVIVCCJJEE",
                                "VVIIICJJEE",
                                "MIIIIIJJEE",
                                "MIIISIJEEE",
                                "MMMISSJEEE"
                        ),
                        11
                ),
                Arguments.of(
                        List.of(
                                "OOOOO",
                                "OXOXO",
                                "OOOOO",
                                "OXOXO",
                                "OOOOO"
                        ),
                        5
                ),
                Arguments.of(
                        List.of(
                                ".........A",
                                "......AAAA",
                                "...AAAA...",
                                ".AAA......"
                        ),
                        3
                ),
                Arguments.of(
                        List.of(
                                ".........A",
                                "......AAAA",
                                "...AAAA...",
                                ".AAA......",
                                ".........."
                        ),
                        2
                ),
                Arguments.of(
                        List.of(
                                "A.........",
                                "AAAA......",
                                "...AAAA...",
                                "......AAA."
                        ),
                        3
                ),
                Arguments.of(
                        List.of(
                                "A.........",
                                "AAAA......",
                                "...AAAA...",
                                "......AAA.",
                                ".........."
                        ),
                        2
                ),
                Arguments.of(
                        List.of(
                                "A.A",
                                ".A.",
                                "A.A"
                        ),
                        9
                ),
                Arguments.of(
                        List.of(
                                ".....",
                                ".A.A.",
                                "..A..",
                                ".A.A.",
                                "....."
                        ),
                        6
                ),
                Arguments.of(
                        List.of(
                                ".A.",
                                "AAA",
                                ".A."
                        ),
                        5
                ),
                Arguments.of(
                        List.of(
                                ".....",
                                "..A..",
                                ".AAA.",
                                "..A..",
                                "....."
                        ),
                        2
                ),
                Arguments.of(
                        List.of(
                                "....JJ......",
                                "....J.......",
                                "....JJJ.J...",
                                "......J.J...",
                                ".....JJJJJJ.",
                                ".....JJJJ...",
                                "......JJ....",
                                "......J....."
                        ),
                        3
                ),
                Arguments.of(
                        List.of(
                                ".......",
                                ".A...A.",
                                ".AA.AA.",
                                "..AAA..",
                                "...A...",
                                "......."
                        ),
                        2
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void regionsCount(List<String> input, int expectedRegions) {
        Grid<CellContent> grid = Grid.fromInput(input);

        Collection<Region> actual = Region.fromGrid(grid);
        assertThat(actual).hasSize(expectedRegions);
    }

    @Test
    void sides() {
        Grid<CellContent> grid = Grid.fromInput(List.of(
                "RRRRIICCFF",
                "RRRRIICCCF",
                "VVRRRCCFFF",
                "VVRCCCJFFF",
                "VVVVCJJCFE",
                "VVIVCCJJEE",
                "VVIIICJJEE",
                "MIIIIIJJEE",
                "MIIISIJEEE",
                "MMMISSJEEE"
        ));

        Collection<Region> actual = Region.fromGrid(grid);

        Map<CellContent, List<Integer>> allSides = actual.stream().collect(Collectors.groupingBy(Region::content, Collectors.mapping(r -> r.sides().size(), Collectors.toList())));

        assertThat(allSides.get(new CellContent("R"))).containsExactlyInAnyOrder(10);
        assertThat(allSides.get(new CellContent("I"))).containsExactlyInAnyOrder(4, 16);
        assertThat(allSides.get(new CellContent("C"))).containsExactlyInAnyOrder(22, 4);
        assertThat(allSides.get(new CellContent("F"))).containsExactlyInAnyOrder(12);
        //assertThat(allSides.get(new CellContent("V"))).containsExactlyInAnyOrder(13L);
        //assertThat(allSides.get(new CellContent("J"))).containsExactlyInAnyOrder(11L);
        //assertThat(allSides.get(new CellContent("E"))).containsExactlyInAnyOrder(13L);
        //assertThat(allSides.get(new CellContent("M"))).containsExactlyInAnyOrder(5L);
        //assertThat(allSides.get(new CellContent("S"))).containsExactlyInAnyOrder(3L);
    }
}
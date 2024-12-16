package fr.ninauve.renaud.adventofcode.year2024.day15;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GridTest {

    @Test
    void fromInput() {
        Grid actual = Grid.fromInput(List.of(
                "#O.@",
                "####"
        ));
        assertThat(actual.getNbRows()).isEqualTo(2);
        assertThat(actual.getNbCols()).isEqualTo(4);

        assertThat(actual.get(new Cell(0, 0))).isEqualTo(CellContent.WALL);
        assertThat(actual.get(new Cell(0, 1))).isEqualTo(CellContent.BOX);
        assertThat(actual.get(new Cell(0, 2))).isEqualTo(CellContent.EMPTY);
        assertThat(actual.get(new Cell(0, 3))).isEqualTo(CellContent.ROBOT);

        assertThat(actual.get(new Cell(1, 0))).isEqualTo(CellContent.WALL);
        assertThat(actual.get(new Cell(1, 1))).isEqualTo(CellContent.WALL);
        assertThat(actual.get(new Cell(1, 2))).isEqualTo(CellContent.WALL);
        assertThat(actual.get(new Cell(1, 3))).isEqualTo(CellContent.WALL);
    }

    static Stream<Arguments> move() {
        return Stream.of(
                Arguments.of(
                        "1",
                        List.of(
                                "########",
                                "#......#",
                                "#..@...#",
                                "#......#",
                                "########"
                        ),
                        Move.UP,
                        List.of(
                                "########",
                                "#..@...#",
                                "#......#",
                                "#......#",
                                "########"
                        )
                ),
                Arguments.of(
                        "2",
                        List.of(
                                "########",
                                "#..@...#",
                                "#......#",
                                "#......#",
                                "########"
                        ),
                        Move.UP,
                        List.of(
                                "########",
                                "#..@...#",
                                "#......#",
                                "#......#",
                                "########"
                        )
                ),
                Arguments.of(
                        "3",
                        List.of(
                                "########",
                                "#......#",
                                "#..O...#",
                                "#..@...#",
                                "########"
                        ),
                        Move.UP,
                        List.of(
                                "########",
                                "#..O...#",
                                "#..@...#",
                                "#......#",
                                "########"
                        )
                ),
                Arguments.of(
                        "4",
                        List.of(
                                "########",
                                "#......#",
                                "#..O...#",
                                "#..O...#",
                                "#..@...#",
                                "########"
                        ),
                        Move.UP,
                        List.of(
                                "########",
                                "#..O...#",
                                "#..O...#",
                                "#..@...#",
                                "#......#",
                                "########"
                        )
                ),
                Arguments.of(
                        "5",
                        List.of(
                                "########",
                                "#..O...#",
                                "#..O...#",
                                "#..@...#",
                                "########"
                        ),
                        Move.UP,
                        List.of(
                                "########",
                                "#..O...#",
                                "#..O...#",
                                "#..@...#",
                                "########"
                        )
                ),
                Arguments.of(
                        "6",
                        List.of(
                                "########",
                                "#......#",
                                "#..[]..#",
                                "#..@...#",
                                "########"
                        ),
                        Move.UP,
                        List.of(
                                "########",
                                "#..[]..#",
                                "#..@...#",
                                "#......#",
                                "########"
                        )
                ),
                Arguments.of(
                        "7",
                        List.of(
                                "########",
                                "#......#",
                                "#.[]...#",
                                "#..@...#",
                                "########"
                        ),
                        Move.UP,
                        List.of(
                                "########",
                                "#.[]...#",
                                "#..@...#",
                                "#......#",
                                "########"
                        )
                ),
                Arguments.of(
                        "8",
                        List.of(
                                "########",
                                "#......#",
                                "#..[]..#",
                                "#.[]...#",
                                "#..@...#",
                                "########"
                        ),
                        Move.UP,
                        List.of(
                                "########",
                                "#..[]..#",
                                "#.[]...#",
                                "#..@...#",
                                "#......#",
                                "########"
                        )
                ),
                Arguments.of(
                        "9",
                        List.of(
                                "########",
                                "#......#",
                                "#.@[]..#",
                                "#......#",
                                "#......#",
                                "########"
                        ),
                        Move.RIGHT,
                        List.of(
                                "########",
                                "#......#",
                                "#..@[].#",
                                "#......#",
                                "#......#",
                                "########"
                        )
                ),
                Arguments.of(
                        "10",
                        List.of(
                                "########",
                                "#......#",
                                "#@[][].#",
                                "#......#",
                                "#......#",
                                "########"
                        ),
                        Move.RIGHT,
                        List.of(
                                "########",
                                "#......#",
                                "#.@[][]#",
                                "#......#",
                                "#......#",
                                "########"
                        )
                ),
                Arguments.of(
                        "11",
                        List.of(
                                "########",
                                "#..[]..#",
                                "#.[]...#",
                                "#..@...#",
                                "########"
                        ),
                        Move.UP,
                        List.of(
                                "########",
                                "#..[]..#",
                                "#.[]...#",
                                "#..@...#",
                                "########"
                        )
                ),
                Arguments.of(
                        "12",
                        List.of(
                                "########",
                                "#......#",
                                "#.[][].#",
                                "#..[]..#",
                                "#.[]...#",
                                "#..@...#",
                                "########"
                        ),
                        Move.UP,
                        List.of(
                                "########",
                                "#.[][].#",
                                "#..[]..#",
                                "#.[]...#",
                                "#..@...#",
                                "#......#",
                                "########"
                        )
                ),
                Arguments.of(
                        "13",
                        List.of(
                                "########",
                                "#......#",
                                "#.[]#..#",
                                "#..[]..#",
                                "#.[]...#",
                                "#..@...#",
                                "########"
                        ),
                        Move.UP,
                        List.of(
                                "########",
                                "#......#",
                                "#.[]#..#",
                                "#..[]..#",
                                "#.[]...#",
                                "#..@...#",
                                "########"
                        )
                )
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource
    void move(String title, List<String> input, Move move, List<String> expected) {
        Grid grid = Grid.fromInput(input);

        Cell robot = grid.find(CellContent.ROBOT).getFirst();
        grid.move(robot, move);

        assertThat(grid.toOutput()).containsExactlyElementsOf(expected);
    }
}
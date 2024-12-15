package fr.ninauve.renaud.adventofcode.year2024.day15;

import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void move() {
        Grid grid = Grid.fromInput(List.of(
                "########",
                "#..O.O.#",
                "##@.O..#",
                "#...O..#",
                "#.#.O..#",
                "#...O..#",
                "#......#",
                "########"
        ));

        Cell robot = grid.find(CellContent.ROBOT).getFirst();
        grid.move(robot, Move.UP);

        assertThat(grid.toOutput()).containsExactly(
                "########",
                "#.@O.O.#",
                "##..O..#",
                "#...O..#",
                "#.#.O..#",
                "#...O..#",
                "#......#",
                "########"
        );
    }
}
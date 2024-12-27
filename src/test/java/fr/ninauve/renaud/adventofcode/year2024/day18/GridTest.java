package fr.ninauve.renaud.adventofcode.year2024.day18;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GridTest {
    public static final String PATH_SYMBOL = "O";
    public static final int SIMPLE_NB_ROWS = 7;
    public static final int SIMPLE_NB_COLS = 7;
    public static final int SIMPLE_TIME = 12;
    public static final List<String> SIMPLE_EXAMPLE = List.of(
            "5,4",
            "4,2",
            "4,5",
            "3,0",
            "2,1",
            "6,3",
            "2,4",
            "1,5",
            "0,6",
            "3,3",
            "2,6",
            "5,1",
            "1,2",
            "5,5",
            "2,5",
            "6,5",
            "1,4",
            "0,4",
            "6,4",
            "1,1",
            "6,1",
            "1,0",
            "0,5",
            "1,6",
            "2,0"
    );

    @Test
    void fromInput() {
        Grid grid = Grid.fromInput(SIMPLE_EXAMPLE.subList(0, SIMPLE_TIME), SIMPLE_NB_ROWS, SIMPLE_NB_COLS);

        List<String> actual = grid.toOutput();
        assertThat(actual).containsExactly(
                "...#...",
                "..#..#.",
                "....#..",
                "...#..#",
                "..#..#.",
                ".#..#..",
                "#.#...."
        );
    }

    @Test
    void toOutput() {
        Grid grid = Grid.fromInput(SIMPLE_EXAMPLE.subList(0, SIMPLE_TIME), SIMPLE_NB_ROWS, SIMPLE_NB_COLS);
        Cells cells = Cells.fromInput(List.of(
                "OO.#OOO",
                ".O#OO#O",
                ".OOO#OO",
                "...#OO#",
                "..#OO#.",
                ".#.O#..",
                "#.#OOOO"
        ), SIMPLE_NB_ROWS, SIMPLE_NB_COLS, PATH_SYMBOL);

        List<String> actual = grid.toOutput(cells, PATH_SYMBOL);
        assertThat(actual).containsExactly(
                "OO.#OOO",
                ".O#OO#O",
                ".OOO#OO",
                "...#OO#",
                "..#OO#.",
                ".#.O#..",
                "#.#OOOO"
        );
    }
}
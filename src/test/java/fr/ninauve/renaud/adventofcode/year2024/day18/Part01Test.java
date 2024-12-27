package fr.ninauve.renaud.adventofcode.year2024.day18;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {

    @Test
    void findPath() {
        Grid expectedGrid = Grid.fromInput(GridTest.SIMPLE_EXAMPLE_01, GridTest.SIMPLE_NB_ROWS, GridTest.SIMPLE_NB_COLS);
        Cells expectedPath = Cells.fromInput(GridTest.SIMPLE_PATH, GridTest.SIMPLE_NB_ROWS, GridTest.SIMPLE_NB_COLS, GridTest.PATH_SYMBOL);
        List<String> expected = expectedGrid.toOutput(expectedPath, GridTest.PATH_SYMBOL);

        List<Cell> actualPath = Part01.findPath(GridTest.SIMPLE_EXAMPLE, GridTest.SIMPLE_NB_ROWS, GridTest.SIMPLE_NB_COLS, GridTest.SIMPLE_TIME);

        List<String> actual = expectedGrid.toOutput(new Cells(new HashSet<>(actualPath)), GridTest.PATH_SYMBOL);
        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    void solve() {
        long actual = Part01.solve(GridTest.SIMPLE_EXAMPLE, GridTest.SIMPLE_NB_ROWS, GridTest.SIMPLE_NB_COLS, GridTest.SIMPLE_TIME);
        assertThat(actual).isEqualTo(22L);
    }
}
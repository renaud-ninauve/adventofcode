package fr.ninauve.renaud.adventofcode.year2024.day18;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {

    @Test
    void solve() {
        String actual = Part02.solve(GridTest.SIMPLE_EXAMPLE, GridTest.SIMPLE_NB_ROWS, GridTest.SIMPLE_NB_COLS, GridTest.SIMPLE_TIME);

        assertThat(actual).isEqualTo("6,1");
    }
}
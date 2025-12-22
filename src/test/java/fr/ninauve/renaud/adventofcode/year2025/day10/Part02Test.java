package fr.ninauve.renaud.adventofcode.year2025.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {
    private static final List<String> INPUT = """
            [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
            [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
            [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
            """.lines().toList();

    @Test
    void should_solve() {
        assertThat(Part02.solve(INPUT)).isEqualTo(33L);
    }
}
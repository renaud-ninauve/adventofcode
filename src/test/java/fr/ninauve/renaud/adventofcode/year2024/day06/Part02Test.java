package fr.ninauve.renaud.adventofcode.year2024.day06;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {

    @Test
    void solve() {
        Part02 part02 = Part02.parse(List.of(
                "....#.....",
                ".........#",
                "..........",
                "..#.......",
                ".......#..",
                "..........",
                ".#..^.....",
                "........#.",
                "#.........",
                "......#..."
        ));

        long actual = part02.solve();

        assertThat(actual).isEqualTo(6L);
    }
}
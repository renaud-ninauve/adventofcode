package fr.ninauve.renaud.adventofcode.year2024.day06;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {

    @Test
    void solve() {
        Part01 part01 = Part01.parse(List.of(
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

        long actual = part01.solve();

        assertThat(actual).isEqualTo(41L);
    }
}
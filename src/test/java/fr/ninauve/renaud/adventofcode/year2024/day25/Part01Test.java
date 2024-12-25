package fr.ninauve.renaud.adventofcode.year2024.day25;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {
    private static final List<String> SIMPLE_EXAMPLE = List.of(
            "#####",
            ".####",
            ".####",
            ".####",
            ".#.#.",
            ".#...",
            ".....",
            "",
            "#####",
            "##.##",
            ".#.##",
            "...##",
            "...#.",
            "...#.",
            ".....",
            "",
            ".....",
            "#....",
            "#....",
            "#...#",
            "#.#.#",
            "#.###",
            "#####",
            "",
            ".....",
            ".....",
            "#.#..",
            "###..",
            "###.#",
            "###.#",
            "#####",
            "",
            ".....",
            ".....",
            ".....",
            "#....",
            "#.#..",
            "#.#.#",
            "#####"
    );

    @Test
    void parse() {
        Part01.Input actual = Part01.parse(SIMPLE_EXAMPLE);

        assertThat(actual.locks()).containsExactlyInAnyOrder(
                List.of(0,5,3,4,3),
                List.of(1,2,0,5,3)
        );
        assertThat(actual.keys()).containsExactlyInAnyOrder(
                List.of(5,0,2,1,3),
                List.of(4,3,4,0,2),
                List.of(3,0,2,0,1)
        );
    }
}
package fr.ninauve.renaud.adventofcode.year2024.day16;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {
    @Test
    void solve1() {
        List<String> input = List.of(
                "###############",
                "#.......#....E#",
                "#.#.###.#.###.#",
                "#.....#.#...#.#",
                "#.###.#####.#.#",
                "#.#.#.......#.#",
                "#.#.#####.###.#",
                "#...........#.#",
                "###.#.#####.#.#",
                "#...#.....#.#.#",
                "#.#.#.###.#.#.#",
                "#.....#...#.#.#",
                "#.###.#.#.#.#.#",
                "#S..#.....#...#",
                "###############"
        );
        long actual = Part02.solve(input);
        assertThat(actual).isEqualTo(45L);
    }

    @Test
    void solve2() {
        List<String> input = List.of(
                "#################",
                "#...#...#...#..E#",
                "#.#.#.#.#.#.#.#.#",
                "#.#.#.#...#...#.#",
                "#.#.#.#.###.#.#.#",
                "#...#.#.#.....#.#",
                "#.#.#.#.#.#####.#",
                "#.#...#.#.#.....#",
                "#.#.#####.#.###.#",
                "#.#.#.......#...#",
                "#.#.###.#####.###",
                "#.#.#...#.....#.#",
                "#.#.#.#####.###.#",
                "#.#.#.........#.#",
                "#.#.#.#########.#",
                "#S#.............#",
                "#################"
        );
        long actual = Part02.solve(input);
        assertThat(actual).isEqualTo(64L);
    }
}
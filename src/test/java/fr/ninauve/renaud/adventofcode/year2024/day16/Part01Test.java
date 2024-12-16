package fr.ninauve.renaud.adventofcode.year2024.day16;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {

    @Test
    void toOutput() {
        Grid grid = Grid.fromInput(List.of(
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
        ));

        List<String> actual = Part01.toOutput(grid, List.of(Action.TURN_COUNTERCLOCKWISE, Action.MOVE_STRAIGHT, Action.MOVE_STRAIGHT, Action.TURN_CLOCKWISE, Action.MOVE_STRAIGHT));

        assertThat(actual).containsExactly(
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
                "#^>...#...#.#.#",
                "#^###.#.#.#.#.#",
                "#S..#.....#...#",
                "###############"
        );
    }
}
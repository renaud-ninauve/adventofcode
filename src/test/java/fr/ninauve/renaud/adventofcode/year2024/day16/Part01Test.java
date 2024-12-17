package fr.ninauve.renaud.adventofcode.year2024.day16;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

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

    @Test
    void findMinActions() {
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
        Grid grid = Grid.fromInput(input);
        List<Action> actions = Part01.findMinActions(input);

        List<String> actual = Part01.toOutput(grid, actions);

        assertThat(actual).containsExactly(
                "###############",
                "#.......#....E#",
                "#.#.###.#.###^#",
                "#.....#.#...#^#",
                "#.###.#####.#^#",
                "#.#.#.......#^#",
                "#.#.#####.###^#",
                "#..>>>>>>>>v#^#",
                "###^#.#####v#^#",
                "#>>^#.....#v#^#",
                "#^#.#.###.#v#^#",
                "#^....#...#v#^#",
                "#^###.#.#.#v#^#",
                "#S..#.....#>>^#",
                "###############"
        );
    }
}
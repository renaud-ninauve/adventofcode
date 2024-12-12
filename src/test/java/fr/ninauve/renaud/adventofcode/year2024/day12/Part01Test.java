package fr.ninauve.renaud.adventofcode.year2024.day12;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {

    @Test
    void solve() {
        long actual = Part01.solve(List.of(
                "RRRRIICCFF",
                "RRRRIICCCF",
                "VVRRRCCFFF",
                "VVRCCCJFFF",
                "VVVVCJJCFE",
                "VVIVCCJJEE",
                "VVIIICJJEE",
                "MIIIIIJJEE",
                "MIIISIJEEE",
                "MMMISSJEEE"
        ));

        assertThat(actual).isEqualTo(1930L);
    }

    @Test
    void solve2() {
        long actual = Part01.solve(List.of(
                "OOOOO",
                "OXOXO",
                "OOOOO",
                "OXOXO",
                "OOOOO"
        ));

        assertThat(actual).isEqualTo(772L);
    }

    @Test
    void solveMain() throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part01.class
                .getResource("/year2024/day12/input.txt").toURI()), StandardCharsets.UTF_8);
        long actual = Part01.solve(input);
        // 640 regions
        // part 2 ->  908042
        assertThat(actual).isEqualTo(1449902L);
    }
}
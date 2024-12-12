package fr.ninauve.renaud.adventofcode.year2024.day12;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {

    @Test
    void solve() {
        long actual = Part02.solve(List.of(
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

        assertThat(actual).isEqualTo(1206L);
    }

    @Test
    void solveMain() throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part02.class
                .getResource("/year2024/day12/input.txt").toURI()), StandardCharsets.UTF_8);
        long actual = Part02.solve(input);
        assertThat(actual).isEqualTo(908042L);
    }
}
package fr.ninauve.renaud.adventofcode.year2024.day08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {

    @Test
    void solveT() {
        Part02 part02 = Part02.parse(List.of(
                "T.........",
                "...T......",
                ".T........",
                "..........",
                "..........",
                "..........",
                "..........",
                "..........",
                "..........",
                ".........."
        ));

        long actual = part02.solve();

        assertThat(actual).isEqualTo(9L);
    }

    @Test
    void solve() {
        Part02 part02 = Part02.parse(List.of(
                "............",
                "........0...",
                ".....0......",
                ".......0....",
                "....0.......",
                "......A.....",
                "............",
                "............",
                "........A...",
                ".........A..",
                "............",
                "............"
        ));

        long actual = part02.solve();

        assertThat(actual).isEqualTo(34L);
    }
}
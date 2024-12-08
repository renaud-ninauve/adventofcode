package fr.ninauve.renaud.adventofcode.year2024.day08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Part01Test {

    @Test
    void solve() {
        Part01 part01 = Part01.parse(List.of(
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

        long actual = part01.solve();

        assertThat(actual).isEqualTo(14L);
    }
}
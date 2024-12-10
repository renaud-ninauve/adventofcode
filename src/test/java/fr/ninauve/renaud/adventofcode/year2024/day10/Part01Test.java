package fr.ninauve.renaud.adventofcode.year2024.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {

    @Test
    void solve() {
        Part01 part01 = Part01.parse(List.of(
                "89010123",
                "78121874",
                "87430965",
                "96549874",
                "45678903",
                "32019012",
                "01329801",
                "10456732"
        ));
        long actual = part01.solve();
        assertThat(actual).isEqualTo(36L);
    }
}
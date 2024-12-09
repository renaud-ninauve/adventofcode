package fr.ninauve.renaud.adventofcode.year2024.day09;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {

    @Test
    void solve() {
        Part02 part02 = Part02.parse(List.of("2333133121414131402"));
        long actual = part02.solve();
        assertThat(actual).isEqualTo(2858L);
    }
}
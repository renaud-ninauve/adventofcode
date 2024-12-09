package fr.ninauve.renaud.adventofcode.year2024.day09;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {

    @Test
    void toDisk() {
        List<Long> actual = Part01.toDisk(List.of(
                2L, 3L, 3L, 3L, 1L, 3L, 3L, 1L, 2L, 1L, 4L, 1L, 4L, 1L, 3L, 1L, 4L, 0L, 2L
        ));
        assertThat(actual).isEqualTo(List.of(
                0L, 0L, -1L, -1L, -1L, 1L, 1L, 1L, -1L, -1L, -1L, 2L, -1L, -1L, -1L, 3L, 3L, 3L, -1L, 4L, 4L, -1L, 5L, 5L, 5L, 5L, -1L, 6L, 6L, 6L, 6L, -1L, 7L, 7L, 7L, -1L, 8L, 8L, 8L, 8L, 9L, 9L
        ));
    }

    @Test
    void compact() {
        List<Long> actual = Part01.compact(List.of(0L, 0L, -1L, -1L, -1L, 1L, 1L, 1L, -1L, -1L, -1L, 2L, -1L, -1L, -1L, 3L, 3L, 3L, -1L, 4L, 4L, -1L, 5L, 5L, 5L, 5L, -1L, 6L, 6L, 6L, 6L, -1L, 7L, 7L, 7L, -1L, 8L, 8L, 8L, 8L, 9L, 9L));
        assertThat(actual).isEqualTo(List.of(
                0L, 0L, 9L, 9L, 8L, 1L, 1L, 1L, 8L, 8L, 8L, 2L, 7L, 7L, 7L, 3L, 3L, 3L, 6L, 4L, 4L, 6L, 5L, 5L, 5L, 5L, 6L, 6L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L, -1L
        ));
    }

    @Test
    void solve() {
        Part01 part01 = Part01.parse(List.of("2333133121414131402"));
        long actual = part01.solve();
        assertThat(actual).isEqualTo(1928L);
    }
}
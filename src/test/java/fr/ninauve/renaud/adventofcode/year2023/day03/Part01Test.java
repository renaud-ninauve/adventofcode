package fr.ninauve.renaud.adventofcode.year2023.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Part01Test {

    @Test
    void solve() {
        long actual = Part01.solve(List.of(
                "467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598.."
        ));
        assertThat(actual).isEqualTo(4361L);
    }
}
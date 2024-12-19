package fr.ninauve.renaud.adventofcode.year2024.day17;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Part02Test {

    @Test
    void solve() {
        int actual = Part02.solve(List.of(
                "Register A: 2024",
                "Register B: 0",
                "Register C: 0",
                "",
                "Program: 0,3,5,4,3,0"
        ));
        assertThat(actual).isEqualTo(117440);
    }
}
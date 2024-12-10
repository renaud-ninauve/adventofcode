package fr.ninauve.renaud.adventofcode.year2023.day01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Part01Test {

    @Test
    void calibrationValue() {
        long actual = Part01.calibrationValue("ab1cd2efg3h");
        assertThat(actual).isEqualTo(13L);
    }
    
    @Test
    void calibrationValues() {
        List<Long> actual = Part01.calibrationValues(List.of(
                "1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet"
        ));
        assertThat(actual).isEqualTo(List.of(
                12L, 38L, 15L, 77L
        ));
    }

    @Test
    void solve() {
        long actual = Part01.solve(List.of(
                "1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet"
        ));
        assertThat(actual).isEqualTo(142L);
    }
}
package fr.ninauve.renaud.adventofcode.year2024.day13;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Matcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Part01Test {

    @Test
    void buttonPattern() {
        Matcher actual = Part01.BUTTON_LINE.matcher("Button A: X+94, Y+34");
        assertThat(actual.matches()).isTrue();
        assertThat(actual.group(1)).isEqualTo("A");
        assertThat(actual.group(2)).isEqualTo("94");
        assertThat(actual.group(3)).isEqualTo("34");
    }

    @Test
    void prizePattern() {
        Matcher actual = Part01.PRIZE_LINE.matcher("Prize: X=8400, Y=5400");
        assertThat(actual.matches()).isTrue();
        assertThat(actual.group(1)).isEqualTo("8400");
        assertThat(actual.group(2)).isEqualTo("5400");
    }

    @Test
    void solve() {
        long actual = Part01.solve(List.of(
                "Button A: X+94, Y+34",
                "Button B: X+22, Y+67",
                "Prize: X=8400, Y=5400",
                "",
                "Button A: X+26, Y+66",
                "Button B: X+67, Y+21",
                "Prize: X=12748, Y=12176",
                "",
                "Button A: X+17, Y+86",
                "Button B: X+84, Y+37",
                "Prize: X=7870, Y=6450",
                "",
                "Button A: X+69, Y+23",
                "Button B: X+27, Y+71",
                "Prize: X=18641, Y=10279"
        ));

        assertThat(actual).isEqualTo(480L);
    }
}
package fr.ninauve.renaud.adventofcode.year2024.day03;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {

    @Test
    void parse_do() {
        Matcher matcher = Part02.INSTRUCTIONS_PATTERN.matcher("do()");
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.group()).isEqualTo("do()");
    }

    @Test
    void parse_dont() {
        Matcher matcher = Part02.INSTRUCTIONS_PATTERN.matcher("don't()");
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.group()).isEqualTo("don't()");
    }

    @Test
    void parse_multiply() {
        Matcher matcher = Part02.INSTRUCTIONS_PATTERN.matcher("mul(123,456)");
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.group(4)).isEqualTo("123");
        assertThat(matcher.group(5)).isEqualTo("456");
    }
}
package fr.ninauve.renaud.adventofcode.year2024.day03;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Matcher;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {

    static Stream<Arguments> parse_valid() {
        return Stream.of(
                Arguments.of("mul(1,2)", "1", "2"),
                Arguments.of("mul(12,21)", "12", "21"),
                Arguments.of("mul(123,321)", "123", "321")
        );
    }

    @ParameterizedTest
    @MethodSource
    void parse_valid(String str, String expectedA, String expectedB) {
        Matcher matcher = Part01.MULTIPLY_PATTERN.matcher(str);
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.group(1)).isEqualTo(expectedA);
        assertThat(matcher.group(2)).isEqualTo(expectedB);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "toto",
            "mul()",
            "mul(12)",
            "mul(1234,1)",
            "mul(1,1234)",
            "mul(corner1,corner2)"
    })
    void parse_invalid(String str) {
        Matcher matcher = Part01.MULTIPLY_PATTERN.matcher(str);
        assertThat(matcher.find()).isFalse();
    }
}
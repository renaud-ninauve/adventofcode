package fr.ninauve.renaud.adventofcode.year2023.day02;

import java.util.Arrays;
import java.util.Objects;

public enum Color {
    RED("red"), GREEN("green"), BLUE("blue");
    private final String input;

    Color(String input) {
        this.input = input;
    }

    public static Color fromInput(String input) {
        return Arrays.stream(Color.values())
                .filter(c -> Objects.equals(c.input, input))
                .findFirst()
                .orElseThrow();
    }
}

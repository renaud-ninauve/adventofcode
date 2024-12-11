package fr.ninauve.renaud.adventofcode.year2023.day03;

import java.util.List;

public record CellContent(String value) {
    private static final List<String> DIGITS = List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
    private static final String DOT = ".";

    public boolean isDigit() {
        return DIGITS.contains(value);
    }

    public boolean isSymbol() {
        return !isDigit() && !DOT.equals(value);
    }
}

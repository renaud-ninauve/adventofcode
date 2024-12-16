package fr.ninauve.renaud.adventofcode.year2024.day16;

import java.util.Arrays;

public enum CellContent {
    START("S"),
    WALL("#"),
    EMPTY("."),
    EXIT("E");

    private final String symbol;

    CellContent(String symbol) {
        this.symbol = symbol;
    }

    public static CellContent fromSymbol(String symbol) {
        return Arrays.stream(CellContent.values())
                .filter(content -> content.symbol.equals(symbol))
                .findFirst()
                .orElseThrow();
    }

    public String symbol() {
        return symbol;
    }
}

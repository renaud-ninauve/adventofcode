package fr.ninauve.renaud.adventofcode.year2024.day20;

import java.util.Arrays;

public enum CellContent {
    EMPTY("."), WALL("#"), START("S"), EXIT("E");

    private final String symbol;

    CellContent(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }

    public static CellContent fromSymbol(String symbol) {
        return Arrays.stream(CellContent.values())
                .filter(content -> content.symbol.equals(symbol))
                .findFirst()
                .orElseThrow();
    }
}

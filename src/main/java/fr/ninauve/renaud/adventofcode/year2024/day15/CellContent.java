package fr.ninauve.renaud.adventofcode.year2024.day15;

import java.util.Arrays;

public enum CellContent {
    ROBOT("@"), WALL("#"), EMPTY("."), BOX("O");

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
}

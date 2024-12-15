package fr.ninauve.renaud.adventofcode.year2024.day15;

import java.util.Arrays;

public enum CellContent {
    ROBOT("@", false), WALL("#", false), EMPTY(".", false), BOX("O", true);

    private final String symbol;
    private final boolean moveable;

    CellContent(String symbol, boolean moveable) {
        this.symbol = symbol;
        this.moveable = moveable;
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

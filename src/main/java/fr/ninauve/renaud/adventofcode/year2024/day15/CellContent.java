package fr.ninauve.renaud.adventofcode.year2024.day15;

import lombok.Getter;

import java.util.Arrays;

public enum CellContent {
    ROBOT("@", false), WALL("#", false), EMPTY(".", false),
    BOX("O", true), LEFT_BOX("[", true), RIGHT_BOX("]", true);

    private final String symbol;
    @Getter
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

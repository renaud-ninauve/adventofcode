package fr.ninauve.renaud.adventofcode.year2024.day18;

public enum CellContent {
    SAFE("."), CORRUTED("#");

    private final String symbol;

    CellContent(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }
}

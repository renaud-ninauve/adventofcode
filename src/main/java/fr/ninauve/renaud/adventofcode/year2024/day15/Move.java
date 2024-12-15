package fr.ninauve.renaud.adventofcode.year2024.day15;

import java.util.Arrays;
import java.util.List;

public enum Move {
    UP(Cell.builder()
            .row(-1)
            .col(0)
            .build(), "^"),
    RIGHT(Cell.builder()
            .row(0)
            .col(1)
            .build(), ">"),
    DOWN(Cell.builder()
            .row(1)
            .col(0)
            .build(), "v"),
    LEFT(Cell.builder()
            .row(0)
            .col(-1)
            .build(), "<");

    public static List<Move> fromInput(List<String> input) {
        return input.stream()
                .flatMapToInt(String::chars)
                .mapToObj(c -> String.valueOf((char) c))
                .map(Move::fromSymbol)
                .toList();
    }

    private final Cell delta;
    private final String symbol;

    Move(Cell delta, String symbol) {
        this.delta = delta;
        this.symbol = symbol;
    }

    public Cell delta() {
        return delta;
    }

    public static Move fromSymbol(String symbol) {
        return Arrays.stream(Move.values())
                .filter(m -> m.symbol.equals(symbol))
                .findFirst()
                .orElseThrow();
    }
}

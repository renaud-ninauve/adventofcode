package fr.ninauve.renaud.adventofcode.year2024.day15;

import java.util.List;
import java.util.stream.IntStream;

public record Game(Grid<CellContent> grid, List<Move> moves) {
    public static Game fromInput(List<String> input) {
        int emptyLine = IntStream.range(0, input.size())
                .filter(i -> input.get(i).isBlank())
                .findFirst()
                .orElseThrow();

        Grid<CellContent> grid = Grid.fromInput(input.subList(0, emptyLine));
        List<Move> moves = Move.fromInput(input.subList(emptyLine + 1, input.size()));
        return new Game(grid, moves);
    }
}

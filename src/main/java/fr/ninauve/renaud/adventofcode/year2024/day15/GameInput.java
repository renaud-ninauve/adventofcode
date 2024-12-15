package fr.ninauve.renaud.adventofcode.year2024.day15;

import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;

@Data
public class GameInput {
    private final Grid grid;
    private final List<Move> moves;

    public static GameInput fromInput(List<String> input) {
        int emptyLine = IntStream.range(0, input.size())
                .filter(i -> input.get(i).isBlank())
                .findFirst()
                .orElseThrow();

        Grid grid = Grid.fromInput(input.subList(0, emptyLine));
        List<Move> moves = Move.fromInput(input.subList(emptyLine + 1, input.size()));
        return new GameInput(grid, moves);
    }
}
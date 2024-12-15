package fr.ninauve.renaud.adventofcode.year2024.day15;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Part01 {

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part01.class
                .getResource("/year2024/day15/input.txt").toURI()), StandardCharsets.UTF_8);
        System.out.println(Part01.solve(input));
    }

    public static long solve(List<String> input) {
        GameInput gameInput = GameInput.fromInput(input);
        Grid grid = gameInput.getGrid();
        for (Move move : gameInput.getMoves()) {
            Cell robot = grid.find(CellContent.ROBOT).getFirst();
            grid.move(robot, move);
        }
        return grid.find(CellContent.BOX).stream()
                .mapToLong(cell -> cell.getRow() * 100L + cell.getCol())
                .sum();
    }
}

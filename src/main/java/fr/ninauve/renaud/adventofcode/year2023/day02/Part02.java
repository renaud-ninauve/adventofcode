package fr.ninauve.renaud.adventofcode.year2023.day02;

import lombok.Data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Data
public class Part02 {
    private final List<Game> games;

    public static void main(String... args) throws Exception {
        List<String> input = Files.readAllLines(Path.of(Part02.class
                .getResource("/year2023/day02/input.txt").toURI()));

        List<Game> games = input.stream().map(Game::fromInput).toList();
        System.out.println(new Part02(games).solve());
    }

    public long solve() {
        return games.stream()
                .map(Game::minimalBag)
                .mapToLong(cubes -> Arrays.stream(Color.values()).mapToLong(cubes::totalOfColor).reduce(1, (a, b) -> a * b ))
                .sum();
    }
}

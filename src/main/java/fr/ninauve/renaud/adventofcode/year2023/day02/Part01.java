package fr.ninauve.renaud.adventofcode.year2023.day02;

import lombok.Data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Data
public class Part01 {
    private final List<Game> games;

    public static void main(String... args) throws Exception {
        List<String> input = Files.readAllLines(Path.of(Part01.class
                .getResource("/year2023/day02/input.txt").toURI()));

        List<Game> games = input.stream().map(Game::fromInput).toList();
        System.out.println(new Part01(games).solve());
    }

    public long solve() {
        return games.stream()
                .filter(game -> Bag.DEFAULT.areValidDraws(game.draws()))
                .map(Game::id)
                .mapToLong(Integer::longValue)
                .sum();
    }
}

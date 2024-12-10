package fr.ninauve.renaud.adventofcode.year2023.day02;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record Game(int id, List<Cubes> draws) {
    private static final String GAME_PREFIX = "game ";
    private static final String GAME_DELIMITER = ":";
    private static final String DRAWS_DELIMITER = ";";
    private static final String CUBES_DELIMITER = ",";

    public Cubes minimalBag() {
        Map<Color, Integer> minBag = Arrays.stream(Color.values())
                .map(c -> {
                    int maxOfColor = draws.stream().mapToInt(draw -> (int) draw.totalOfColor(c)).max().orElseThrow();
                    return new AbstractMap.SimpleEntry<>(c, maxOfColor);
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new Cubes(minBag);
    }

    public static Game fromInput(String input) {
        int gameDelimiterIndex = input.indexOf(GAME_DELIMITER);
        String gameId = input.substring(GAME_PREFIX.length(), gameDelimiterIndex);
        String draws = input.substring(gameDelimiterIndex + 1);

        List<Cubes> drawCubes = Arrays.stream(draws.split(DRAWS_DELIMITER))
                .map(draw -> Arrays.stream(draw.split(CUBES_DELIMITER))
                        .map(String::strip)
                        .map(Game::parseCube)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .map(Cubes::new)
                .toList();

        return new Game(Integer.parseInt(gameId), drawCubes);
    }

    private static Map.Entry<Color, Integer> parseCube(String input) {
        String[] splitted = input.split(" ");
        int count = Integer.parseInt(splitted[0]);
        Color color = Color.fromInput(splitted[1]);
        return new AbstractMap.SimpleEntry<>(color, count);
    }
}

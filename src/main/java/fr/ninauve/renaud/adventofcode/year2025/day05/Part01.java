package fr.ninauve.renaud.adventofcode.year2025.day05;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Part01 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part01.class.getResource("/year2025/day05/input.txt").toURI()));
    System.out.println(Part01.solve(input));
  }

  public static long solve(List<String> input) {
    Grid grid = Grid.parse(input);

    return grid.locationsAndContents()
        .filter(locationAndContent -> locationAndContent.content() == LocationContent.ROLL_OF_PAPER)
        .map(locationAndContent -> {
          return grid.neighbours(locationAndContent.location())
              .filter(neighbour -> neighbour.content() == LocationContent.ROLL_OF_PAPER)
              .count();
        }).filter(rolls -> rolls < 4)
        .count();
  }
}

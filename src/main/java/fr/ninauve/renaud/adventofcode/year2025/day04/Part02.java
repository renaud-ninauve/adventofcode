package fr.ninauve.renaud.adventofcode.year2025.day04;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Part02 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part02.class.getResource("/year2025/day04/input.txt").toURI()));
    System.out.println(Part02.solve(input));
  }

  public static long solve(List<String> input) {
    Grid grid = Grid.parse(input);

    Set<Location> allRolls = grid.locationsAndContents()
        .filter(locationAndContent -> locationAndContent.content() == LocationContent.ROLL_OF_PAPER)
        .map(LocationAndContent::location)
        .collect(Collectors.toSet());

    Set<Location> rolls = new HashSet<>(allRolls);

    boolean isFirst = true;
    Set<Location> lastRemovedRolls = new HashSet<>();
    while (isFirst || !lastRemovedRolls.isEmpty()) {
      isFirst = false;

      lastRemovedRolls = rolls
          .stream()
          .filter(location ->
              grid.neighbours(location)
                  .filter(neighbour -> neighbour.content() == LocationContent.ROLL_OF_PAPER)
                  .map(LocationAndContent::location)
                  .filter(rolls::contains)
                  .count() < 4)
          .collect(Collectors.toSet());

      rolls.removeAll(lastRemovedRolls);
    }

    return allRolls.size() - rolls.size();
  }
}

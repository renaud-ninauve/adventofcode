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
    Inventory inventory = parse(input);
    return inventory.freshIngredientIDs().size();
  }

  private static Inventory parse(List<String> input) {
    List<Range> ranges = input.stream()
        .filter(line -> line.contains("-"))
        .map(line -> {
          String[] splitted = line.split("-");
          return new Range(Long.parseLong(splitted[0]), Long.parseLong(splitted[1]));
        }).toList();

    List<Long> ingredientIDs = input.stream()
        .filter(line -> !line.isBlank())
        .filter(line -> !line.contains("-"))
        .map(Long::parseLong)
        .toList();

    return new Inventory(ingredientIDs, ranges);
  }
}

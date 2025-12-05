package fr.ninauve.renaud.adventofcode.year2025.day05;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part01 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part01.class.getResource("/year2025/day05/input.txt").toURI()));
    System.out.println(Part01.solve(input));
  }

  public static long solve(List<String> input) {
    int rowsCount = input.size();
    int colsCount = input.getFirst().length();

    final Map<Location, LocationContent> cells = new HashMap<>();
    for (int row = 0; row < rowsCount; row++) {
      String line = input.get(row);
      for (int col = 0; col < colsCount; col++) {
        Location location = Location.builder().row(row).column(col).build();
        LocationContent content = line.charAt(col) == '@' ? LocationContent.ROLL_OF_PAPER : LocationContent.EMPTY;
        cells.put(location, content);
      }
    }

    Grid grid = Grid.builder()
        .cells(cells)
        .rowsCount(rowsCount)
        .columnsCount(colsCount)
        .build();

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

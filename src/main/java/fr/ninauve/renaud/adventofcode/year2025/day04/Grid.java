package fr.ninauve.renaud.adventofcode.year2025.day04;

import lombok.Builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Builder
public record Grid(int rowsCount, int columnsCount, Map<Location, LocationContent> cells) {

  public static Grid parse(List<String> input) {
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
    return Grid.builder()
        .cells(cells)
        .rowsCount(rowsCount)
        .columnsCount(colsCount)
        .build();
  }

  public Stream<LocationAndContent> locationsAndContents() {
    return cells.entrySet().stream()
        .map(e -> new LocationAndContent(e.getKey(), e.getValue()));
  }

  public Stream<LocationAndContent> neighbours(Location location) {
    return IntStream.rangeClosed(-1, 1)
        .boxed()
        .flatMap(deltaRow ->
            IntStream.rangeClosed(-1, 1)
                .boxed()
                .map(deltaCol ->
                    Location.builder()
                        .row(location.row() + deltaRow)
                        .column(location.column() + deltaCol)
                        .build()))
        .filter(this::isValid)
        .filter(neighbour -> !neighbour.equals(location))
        .map(neighbour -> new LocationAndContent(neighbour, cells.get(neighbour)));
  }

  public boolean isValid(Location location) {
    return location.row() >= 0 && location.row() < rowsCount()
        && location.column() >= 0 && location.column() < columnsCount();
  }
}
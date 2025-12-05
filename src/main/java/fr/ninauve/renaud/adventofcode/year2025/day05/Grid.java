package fr.ninauve.renaud.adventofcode.year2025.day05;

import lombok.Builder;

import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Builder
public record Grid(int rowsCount, int columnsCount, Map<Location, LocationContent> cells) {

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
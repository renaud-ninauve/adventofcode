package fr.ninauve.renaud.adventofcode.year2025.day07;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part02 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part02.class.getResource("/year2025/day07/input.txt").toURI()));
    System.out.println(Part02.solve(input));
  }

  public static long solve(List<String> input) {
    Location beamOrigin = null;
    Map<Location, Set<Location>> beamsChildren = new HashMap<>();
    Set<Location> beams = new HashSet<>();
    Location max =
        Location.builder()
            .row(input.size() - 1)
            .col(input.getFirst().length() - 1)
            .build();
    for (int row = 0; row < input.size(); row++) {
      String line = input.get(row);
      for (int col = 0; col < input.getFirst().length(); col++) {
        char c = line.charAt(col);
        Location location =
            Location.builder()
                .row(row)
                .col(col)
                .build();

        if (location.row() == 0) {
          if (c == 'S') {
            beamOrigin = location;
          }
        } else if (c == '^') {
          Location above = location.above();
          if (above.equals(beamOrigin) || beams.contains(above)) {
            Set<Location> newBeams = Stream.of(location.left(), location.right())
                .filter(l -> l.isValid(max))
                .collect(Collectors.toSet());
            beamsChildren.put(above, newBeams);
            beams.addAll(newBeams);
          }
        } else {
          if (location.above().equals(beamOrigin) || beams.contains(location.above())) {
            beamsChildren.put(location.above(), Set.of(location));
            beams.add(location);
          }
        }
      }
    }
    validate(beamsChildren);
    return solve(beamOrigin, max, beamsChildren);
  }

  private static void validate(Map<Location, Set<Location>> beams) {
    boolean allValid = beams.entrySet().stream()
        .flatMap(e -> e.getValue().stream().map(v -> new SimpleEntry<>(e.getKey(), v)))
        .allMatch(e -> e.getValue().row() == e.getKey().row() + 1);
    if (!allValid) {
      throw new IllegalArgumentException("invalid beams");
    }
  }

  public static long solve(Location origin, Location max, Map<Location, Set<Location>> beams) {
    Map<Location, Long> ways = new HashMap<>();
    ways.put(origin, 1L);
    int row = origin.row();
    while (row <= max.row()) {
      for (int col = 0; col <= max.col(); col++) {
        Location location = new Location(row, col);
        Long locationWays = ways.getOrDefault(location, 0L);
        for (Location neighbour : beams.getOrDefault(location, Set.of())) {
          Long oldNeightbourWays = ways.getOrDefault(neighbour, 0L);
          ways.put(neighbour, locationWays + oldNeightbourWays);
        }
      }
      row++;
    }

    Set<Location> lastBeams = beams.values()
        .stream()
        .flatMap(Set::stream)
        .filter(l -> !beams.containsKey(l))
        .collect(Collectors.toSet());

    return lastBeams
        .stream()
        .mapToLong(ways::get)
        .sum();
  }
}

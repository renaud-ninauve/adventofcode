package fr.ninauve.renaud.adventofcode.year2025.day07;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part01 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part01.class.getResource("/year2025/day07/input.txt").toURI()));
    System.out.println(Part01.solve(input));
  }

  public static long solve(List<String> input) {
    Location beamOrigin = null;
    Set<Location> beams = new HashSet<>();
    Location max =
        Location.builder()
            .row(input.size() - 1)
            .col(input.getFirst().length() - 1)
            .build();
    long result = 0;
    for (int row = 0; row < input.size(); row++) {
      String line = input.get(row);
      System.out.println(beams.size());
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
          if (location.above().equals(beamOrigin) || beams.contains(location.above())) {
            if (location.left().isValid(max)) {
              beams.add(location.left());
            }
            if (location.right().isValid(max)) {
              beams.add(location.right());
            }
            result++;
          }
        } else {
          if (location.above().equals(beamOrigin) || beams.contains(location.above())) {
            beams.add(location);
          }
        }
      }
    }
    return result;
  }

}

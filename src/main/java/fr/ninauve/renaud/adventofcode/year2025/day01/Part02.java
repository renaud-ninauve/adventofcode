package fr.ninauve.renaud.adventofcode.year2025.day01;

import fr.ninauve.renaud.adventofcode.year2025.day01.Rotation.LeftRotation;
import fr.ninauve.renaud.adventofcode.year2025.day01.Rotation.RightRotation;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static fr.ninauve.renaud.adventofcode.year2025.day01.Rotation.rotate;
import static java.lang.Integer.parseInt;

public class Part02 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part02.class.getResource("/year2025/day01/input.txt").toURI()));
    System.out.println(Part02.solve(input));
  }

  public static long solve(List<String> input) {
    List<Rotation> rotations = input.stream().map(Part02::parse).toList();
    List<Rotation.Rotated> init = new ArrayList<>();
    init.add(new Rotation.Rotated(new NormalizedDial(50), 0));
    List<Rotation.Rotated> rotateds = rotations.stream()
        .reduce(init, (rotatedList, rotation) -> {
              rotatedList.add(rotate(rotatedList.getLast().dial(), rotation));
              return rotatedList;
            },
            (a, b) -> {
              a.addAll(b);
              return a;
            });
    return rotateds.stream()
        .mapToInt(Rotation.Rotated::countZeros)
        .sum();
  }

  private static Rotation parse(String line) {
    String trimmed = line.trim();
    if (trimmed.charAt(0) == 'L') {
      return new LeftRotation(parseInt(trimmed.substring(1)));
    } else if (line.charAt(0) == 'R') {
      return new RightRotation(parseInt(trimmed.substring(1)));
    }
    throw new IllegalArgumentException("unexpected line");
  }
}

package fr.ninauve.renaud.adventofcode.year2025.day01;

import fr.ninauve.renaud.adventofcode.year2025.day01.Rotation.LeftRotation;
import fr.ninauve.renaud.adventofcode.year2025.day01.Rotation.RightRotation;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Part01 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part01.class.getResource("/year2025/day01/input.txt").toURI()));
    System.out.println(Part01.solve(input));
  }

  public static long solve(List<String> input) {
    List<Rotation> rotations = input.stream().map(Part01::parse).toList();
    List<UnboundedDial> init = new ArrayList<>();
    init.add(new UnboundedDial(50));
    List<UnboundedDial> unboundeds = rotations.stream()
        .reduce(init, (unbounded, rotation) -> {
              unbounded.add(rotate(unbounded.getLast(), rotation));
              return unbounded;
            },
            (a, b) -> {
              a.addAll(b);
              return a;
            });
    List<NormalizedDial> normalizeds = unboundeds.stream()
        .map(UnboundedDial::normalize)
        .toList();
    return normalizeds.stream()
        .filter(d -> d.value() == 0).count();
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

  private static UnboundedDial rotate(UnboundedDial normalizedDial, Rotation rotation) {
    return switch (rotation) {
      case LeftRotation left -> rotate(left, normalizedDial);
      case RightRotation right -> rotate(right, normalizedDial);
    };
  }

  private static UnboundedDial rotate(LeftRotation rotation, UnboundedDial dial) {
    return new UnboundedDial(dial.value() - rotation.value());
  }

  private static UnboundedDial rotate(RightRotation rotation, UnboundedDial dial) {
    return new UnboundedDial(dial.value() + rotation.value());
  }
}

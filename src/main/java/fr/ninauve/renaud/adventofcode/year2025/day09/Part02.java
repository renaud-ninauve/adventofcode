package fr.ninauve.renaud.adventofcode.year2025.day09;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Part02 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part02.class.getResource("/year2025/day09/input.txt").toURI()));
    System.out.println(Part02.solve(input));
  }

  public static long solve(List<String> input) {
    List<Point> points = parse(input);
    Map<Rectangle, Long> areas = areas(points);
    return areas.values()
        .stream()
        .mapToLong(l -> l)
        .max()
        .getAsLong();
  }

  private static List<Point> parse(List<String> input) {
    return input.stream()
        .map(line -> line.split(","))
        .map(splitted -> {
          return new Point(
              new Col(Long.parseLong(splitted[0])),
              new Row(Long.parseLong(splitted[1]))
          );
        }).toList();
  }

  private static Map<Rectangle, Long> areas(List<Point> points) {
    final Set<Point> remaining = new HashSet<>(points);
    final Map<Rectangle, Long> areas = new HashMap<>();
    for (Point origin : points) {
      remaining.remove(origin);
      for (Point target : remaining) {
        Rectangle rectangle = new Rectangle(origin, target);
        areas.put(rectangle, rectangle.area());
      }
    }
    return areas;
  }
}

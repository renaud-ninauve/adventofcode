package fr.ninauve.renaud.adventofcode.year2025.day05;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part02 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part02.class.getResource("/year2025/day05/input.txt").toURI()));
    System.out.println(Part02.solveInput(input));
  }

  public static long solveInput(List<String> input) {
    Set<Range> ranges = parse(input);
    return solve(ranges);
  }

  private static Set<Range> parse(List<String> input) {
    return input.stream()
        .filter(line -> line.contains("-"))
        .map(line -> {
          String[] splitted = line.split("-");
          return new Range(Long.parseLong(splitted[0]), Long.parseLong(splitted[1]));
        }).collect(Collectors.toSet());
  }

  private static long solve(Set<Range> ranges) {
    record OverlappingRanges(Range a, Range b) {
    }

    Map<Range, Set<Range>> intersections = ranges.stream()
        .flatMap(range ->
            ranges.stream()
                .filter(other -> !other.equals(range))
                .map(other ->
                    other.intersects(range) ? Optional.of(new OverlappingRanges(range, other)) : Optional.<OverlappingRanges>empty())
                .flatMap(Optional::stream))
        .collect(Collectors.groupingBy(
            OverlappingRanges::a,
            Collectors.mapping(OverlappingRanges::b, Collectors.toSet())));

    return mergeAndSolve(ranges, intersections);
  }

  private static long mergeAndSolve(Set<Range> allRanges, Map<Range, Set<Range>> intersections) {
    record AllOverlappingRanges(Range a, Set<Range> overlapings) {
    }

    Map<Range, Set<Range>> allIntersections = allRanges.stream()
        .map(r -> new AllOverlappingRanges(r, allIntersectionsForRange(intersections, r)))
        .collect(Collectors.toMap(AllOverlappingRanges::a, AllOverlappingRanges::overlapings));

    return Stream.concat(
            allIntersections.entrySet()
                .stream().filter(e -> e.getValue().isEmpty())
                .map(Map.Entry::getKey),
            allIntersections.values()
                .stream()
                .filter(s -> !s.isEmpty())
                .distinct()
                .map(ranges -> {
                  long minStart = ranges.stream().mapToLong(Range::start).min().getAsLong();
                  long maxStart = ranges.stream().mapToLong(Range::end).max().getAsLong();
                  return new Range(minStart, maxStart);
                }))
        .mapToLong(Range::size)
        .sum();
  }

  private static Set<Range> allIntersectionsForRange(Map<Range, Set<Range>> intersections, Range range) {
    final Queue<Range> toVisit = new LinkedList<>(intersections.getOrDefault(range, Set.of()));
    final Set<Range> visited = new HashSet<>();
    while (!toVisit.isEmpty()) {
      Range current = toVisit.poll();
      visited.add(current);

      Set<Range> next = new HashSet<>(intersections.getOrDefault(current, Set.of()));
      next.removeAll(visited);
      toVisit.addAll(next);
    }
    return visited;
  }
}

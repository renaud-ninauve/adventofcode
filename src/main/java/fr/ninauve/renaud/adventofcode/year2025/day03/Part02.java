package fr.ninauve.renaud.adventofcode.year2025.day03;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Part02 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part02.class.getResource("/year2025/day03/input.txt").toURI()));
    System.out.println(Part02.solve(input));
  }

  public static long solve(List<String> input) {
    return input.stream()
        .mapToLong(Part02::solve)
        .sum();
  }

  public static long solve(String input) {
    final List<JoltageAndIndex> bank = new ArrayList<>();
    for (int i = 0; i < input.length(); i++) {
      int joltage = Integer.parseInt(input.substring(i, i + 1));
      bank.add(new JoltageAndIndex(joltage, i));
    }
    List<JoltageAndIndex> picked = new ArrayList<>();
    for (int i = 0; i < 12; i++) {
      int startIndexInclusive = picked.isEmpty() ? 0 : (picked.getLast().index() + 1);
      int endIndexExclusive = bank.size() - (11 - i);

      JoltageAndIndex found = bank.subList(startIndexInclusive, endIndexExclusive)
          .stream()
          .max(Comparator.comparing(JoltageAndIndex::joltage).thenComparing(Comparator.comparing(JoltageAndIndex::index).reversed()))
          .get();
      picked.add(found);
    }

    String joltageStr = picked.stream()
        .map(JoltageAndIndex::joltage).map(Long::toString)
        .collect(Collectors.joining(""));
    return Long.parseLong(joltageStr);
  }
}

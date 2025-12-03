package fr.ninauve.renaud.adventofcode.year2025.day03;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Part01 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part01.class.getResource("/year2025/day03/input.txt").toURI()));
    System.out.println(Part01.solve(input));
  }

  public static long solve(List<String> input) {
    return input.stream()
        .mapToLong(Part01::solve)
        .sum();
  }

  public static long solve(String input) {
    final List<JoltageAndIndex> bank = new ArrayList<>();
    for (int i = 0; i < input.length(); i++) {
      int joltage = Integer.parseInt(input.substring(i, i + 1));
      bank.add(new JoltageAndIndex(joltage, i));
    }
    JoltageAndIndex first = bank.subList(0, bank.size() - 1)
        .stream()
        .max(Comparator.comparing(JoltageAndIndex::joltage).thenComparing(Comparator.comparing(JoltageAndIndex::index).reversed()))
        .get();

    JoltageAndIndex second = bank.subList(first.index() + 1, bank.size())
        .stream()
        .max(Comparator.comparing(JoltageAndIndex::joltage).thenComparing(Comparator.comparing(JoltageAndIndex::index).reversed()))
        .get();

    return first.joltage() * 10L + second.joltage();
  }
}

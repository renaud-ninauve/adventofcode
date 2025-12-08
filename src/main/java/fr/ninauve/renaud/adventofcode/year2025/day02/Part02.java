package fr.ninauve.renaud.adventofcode.year2025.day02;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Part02 {
  private static final Map<Integer, List<Integer>> DIVISORS = new HashMap<>();

  static {
    DIVISORS.put(2, List.of(2));
    DIVISORS.put(3, List.of(3));
    DIVISORS.put(4, List.of(2, 4));
    DIVISORS.put(5, List.of(5));
    DIVISORS.put(6, List.of(2, 3, 6));
    DIVISORS.put(7, List.of(7));
    DIVISORS.put(8, List.of(2, 4, 8));
    DIVISORS.put(9, List.of(3, 9));
    DIVISORS.put(10, List.of(2, 5, 10));
  }

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part02.class.getResource("/year2025/day02/input.txt").toURI()));
    System.out.println(Part02.solve(input.getFirst()));
  }

  public static long solve(String input) {
    List<Range> ranges = parse(input);
    return ranges.stream()
        .mapToLong(Part02::solve)
        .sum();
  }

  private static List<Range> parse(String input) {
    String[] ranges = input.split(",");
    return Arrays.stream(ranges)
        .map(r -> {
          String[] splittedRange = r.split("-");
          return new Range(splittedRange[0], splittedRange[1]);
        }).toList();
  }

  private static long solve(Range range) {
    long startLong = Long.parseLong(range.start());
    long endLong = Long.parseLong(range.end());
    return LongStream.rangeClosed(startLong, endLong)
        .mapToObj(Long::toString)
        .filter(Part02::isInvalid)
        .mapToLong(Long::parseLong)
        .sum();
  }

  public static boolean isInvalid(String number) {
    List<Integer> divisors = DIVISORS.getOrDefault(number.length(), List.of());
    return divisors.stream()
        .anyMatch(divisor -> {
          int repetitionDigitsCount = number.length() / divisor;
          String repeated = number.substring(0, repetitionDigitsCount);
          String invalid = IntStream.range(0, divisor)
              .mapToObj(i -> repeated)
              .collect(Collectors.joining());
          return number.equals(invalid);
        });
  }
}

package fr.ninauve.renaud.adventofcode.year2025.day02;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Part01 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part01.class.getResource("/year2025/day02/input.txt").toURI()));
    System.out.println(Part01.solve(input.getFirst()));
  }

  public static long solve(String input) {
    List<Range> ranges = parse(input);
    Map<Integer, TreeSet<Long>> allInvalids = allInvalids(10);
    return ranges.stream()
        .map(r -> solve(allInvalids, r.start(), r.end()))
        .mapToLong(Long::longValue)
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

  private static long solve(Map<Integer, TreeSet<Long>> allInvalids, String start, String end) {
    long startLong = Long.parseLong(start);
    long endLong = Long.parseLong(end);
    return IntStream.rangeClosed(start.length(), end.length())
        .mapToObj(allInvalids::get)
        .map(invalids -> invalids.subSet(startLong, true, endLong, true))
        .flatMap(Set::stream)
        .mapToLong(Long::longValue)
        .sum();
  }

  private static Map<Integer, TreeSet<Long>> allInvalids(int maxDigitsCount) {
    final Map<Integer, TreeSet<Long>> allInvalids = new HashMap<>();
    for (int digitsCount = 1; digitsCount <= maxDigitsCount; digitsCount++) {
      TreeSet<Long> invalids = allInvalidsHavingDigitsCount(digitsCount);
      allInvalids.put(digitsCount, invalids);
    }
    return allInvalids;
  }

  private static TreeSet<Long> allInvalidsHavingDigitsCount(int digitsCount) {
    if (digitsCount % 2 == 1) {
      return new TreeSet<>();
    }
    int middleDigitsCount = digitsCount / 2;
    long maxMiddle = Long.parseLong(IntStream.range(0, middleDigitsCount)
        .mapToObj(i -> "9")
        .collect(Collectors.joining()));
    return LongStream.rangeClosed(1, maxMiddle)
        .mapToObj(Long::toString)
        .map(middle -> {
          String endingZeros = IntStream.range(middle.length(), middleDigitsCount)
              .mapToObj(i -> "0")
              .collect(Collectors.joining());
          return middle + endingZeros;
        }).map(middle -> middle + middle)
        .map(Long::parseLong)
        .collect(Collectors.toCollection(TreeSet::new));
  }
}

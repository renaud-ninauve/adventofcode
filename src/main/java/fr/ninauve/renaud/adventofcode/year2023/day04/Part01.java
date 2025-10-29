package fr.ninauve.renaud.adventofcode.year2023.day04;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part01 {
  private static final Pattern NUMBER_OR_PIPE = Pattern.compile("(\\d+)|(\\|)");

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(fr.ninauve.renaud.adventofcode.year2023.day03.Part01.class.getResource("/year2023/day04/input.txt").toURI()));
    System.out.println(Part01.solve(input));
  }

  public static long solve(List<String> input) {
    return input.stream()
        .mapToLong(Part01::computePoints)
        .sum();
  }

  private static long computePoints(String line) {
    String withoutCardPrefix = line.substring(line.indexOf(':') + 1);
    Matcher matcher = NUMBER_OR_PIPE.matcher(withoutCardPrefix);
    Set<Integer> winningNumbers = new HashSet<>();
    boolean isWinning = true;
    int countMatching = 0;
    while (matcher.find()) {
      String found = matcher.group(0);
      if (found.equals("|")) {
        isWinning = false;
        continue;
      }
      Integer foundValue = Integer.valueOf(found);
      if (isWinning) {
        winningNumbers.add(foundValue);
      } else if (winningNumbers.contains(foundValue)) {
        countMatching++;
      }
    }
    return countMatching > 0L ? 1L << (countMatching - 1) : 0L;
  }
}

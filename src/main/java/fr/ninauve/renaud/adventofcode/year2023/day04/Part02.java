package fr.ninauve.renaud.adventofcode.year2023.day04;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part02 {
  private static final Pattern NUMBER_OR_PIPE = Pattern.compile("(\\d+)|(\\|)");

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(fr.ninauve.renaud.adventofcode.year2023.day03.Part01.class.getResource("/year2023/day04/input.txt").toURI()));
    System.out.println(Part02.solve(input));
  }

  public static long solve(List<String> input) {
    Map<Integer, Card> cards = new HashMap<>();
    for (int i = 0; i < input.size(); i++) {
      int cardNumber = i + 1;
      long points = computePoints(input.get(i));
      cards.put(cardNumber, new Card(cardNumber, points));
    }

    long result = 0;
    Deque<Card> queue = new LinkedList<>(cards.values());

    while (!queue.isEmpty()) {
      result++;
      Card card = queue.poll();
      if (card.points > 0) {
        for (int i = card.number() + 1; i <= card.number + card.points(); i++) {
          queue.offer(cards.get(i));
        }
      }
    }
    return result;
  }

  private record Card(int number, long points) {
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
    return countMatching;
  }
}

package fr.ninauve.renaud.adventofcode.year2023.day05.part02;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fr.ninauve.renaud.adventofcode.year2023.day05.part02.Range.rangeLength;
import static fr.ninauve.renaud.adventofcode.year2023.day05.part02.RangeMappers.rangeMappers;

public class Part02 {
  private static final String SEEDS_PREFIX = "seeds: ";
  private static final Pattern MAPPING_NAME_LINE = Pattern.compile("(\\S+) map:");
  private static final Pattern MAPPING_RANGE_LINE = Pattern.compile("(\\d+) (\\d+) (\\d+)");

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part02.class.getResource("/year2023/day05/input.txt").toURI()));
    System.out.println(Part02.solve(input));
  }

  public static long solve(List<String> input) {
    String[] seedsSplit = input.getFirst().substring(SEEDS_PREFIX.length()).split(" ");
    List<Range> seeds = new ArrayList<>();
    for (int i = 0; i < seedsSplit.length - 1; i += 2) {
      seeds.add(rangeLength(Long.parseLong(seedsSplit[i]), Long.parseLong(seedsSplit[i + 1])));
    }
    List<Range> source = seeds.stream()
        .sorted(Comparator.comparing(Range::start))
        .toList();

    List<RangeMappers> allMappers = new ArrayList<>();
    List<RangeMapper> current = new ArrayList<>();
    for (String line : input.subList(1, input.size())) {
      if (MAPPING_NAME_LINE.matcher(line).matches()) {
        if (!current.isEmpty()) {
          allMappers.add(rangeMappers(current));
        }
        current = new ArrayList<>();
        continue;
      }
      Matcher rangeLineMatcher = MAPPING_RANGE_LINE.matcher(line);
      if (rangeLineMatcher.find()) {
        long destinationStart = Long.parseLong(rangeLineMatcher.group(1));
        long sourceStart = Long.parseLong(rangeLineMatcher.group(2));
        long length = Long.parseLong(rangeLineMatcher.group(3));
        current.add(new RangeMapper(destinationStart, sourceStart, length));
      }
    }
    allMappers.add(rangeMappers(current));

    return solve(source, allMappers);
  }

  public static long solve(List<Range> seeds, List<RangeMappers> allMappers) {
    List<Range> sources;
    List<Range> destinations = seeds;
    for (RangeMappers mappers : allMappers) {
      sources = destinations;
      destinations = new ArrayList<>();
      for (Range source : sources) {
        destinations.addAll(mappers.map(source));
      }
    }

    return destinations
        .stream()
        .mapToLong(Range::start)
        .min()
        .orElse(0);
  }
}

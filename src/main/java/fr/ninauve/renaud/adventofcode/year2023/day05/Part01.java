package fr.ninauve.renaud.adventofcode.year2023.day05;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part01 {
  private static final String SEEDS_PREFIX = "seeds: ";
  private static final Pattern MAPPING_NAME_LINE = Pattern.compile("(\\S+) map:");
  private static final Pattern MAPPING_RANGE_LINE = Pattern.compile("(\\d+) (\\d+) (\\d+)");

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part01.class.getResource("/year2023/day05/input.txt").toURI()));
    System.out.println(Part01.solve(input));
  }

  public static long solve(List<String> input) {
    String[] seedsSplit = input.getFirst().substring(SEEDS_PREFIX.length()).split(" ");
    List<Long> seeds = Arrays.stream(seedsSplit)
        .map(Long::parseLong)
        .toList();

    List<Mapping> mappings = new ArrayList<>();
    List<MappingRange> current = new ArrayList<>();
    for (String line : input.subList(1, input.size())) {
      if (MAPPING_NAME_LINE.matcher(line).matches()) {
        if (!current.isEmpty()) {
          mappings.add(new Mapping(current));
        }
        current = new ArrayList<>();
        continue;
      }
      Matcher rangeLineMatcher = MAPPING_RANGE_LINE.matcher(line);
      if (rangeLineMatcher.find()) {
        long destinationStart = Long.parseLong(rangeLineMatcher.group(1));
        long sourceStart = Long.parseLong(rangeLineMatcher.group(2));
        long length = Long.parseLong(rangeLineMatcher.group(3));
        current.add(new MappingRange(destinationStart, sourceStart, length));
      }
    }
    mappings.add(new Mapping(current));

    return seeds.stream()
        .mapToLong(seed -> toDestination(mappings, seed))
        .min()
        .orElse(0L);
  }

  public static long toDestination(List<Mapping> mappings, long value) {
    long destination = value;
    for (Mapping mapping : mappings) {
      destination = mapping.toDestination(destination);
    }
    return destination;
  }

  public record Mapping(List<MappingRange> ranges) {
    public long toDestination(long source) {
      return ranges.stream()
          .filter(r -> r.matchesSource(source))
          .map(r -> r.toDestination(source))
          .findFirst()
          .orElse(source);
    }
  }

  public record MappingRange(long destinationStart, long sourceStart, long length) {
    public boolean matchesSource(long source) {
      return source >= sourceStart && source < sourceStart + length;
    }

    public long toDestination(long source) {
      return destinationStart + source - sourceStart;
    }
  }
}

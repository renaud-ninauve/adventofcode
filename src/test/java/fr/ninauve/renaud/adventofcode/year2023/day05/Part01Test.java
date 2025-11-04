package fr.ninauve.renaud.adventofcode.year2023.day05;

import fr.ninauve.renaud.adventofcode.year2023.day05.Part01.Mapping;
import fr.ninauve.renaud.adventofcode.year2023.day05.Part01.MappingRange;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {
  private static final List<String> INPUT = """
      seeds: 79 14 55 13
      
      seed-to-soil map:
      50 98 2
      52 50 48
      
      soil-to-fertilizer map:
      0 15 37
      37 52 2
      39 0 15
      
      fertilizer-to-water map:
      49 53 8
      0 11 42
      42 0 7
      57 7 4
      
      water-to-light map:
      88 18 7
      18 25 70
      
      light-to-temperature map:
      45 77 23
      81 45 19
      68 64 13
      
      temperature-to-humidity map:
      0 69 1
      1 0 69
      
      humidity-to-location map:
      60 56 37
      56 93 4
      """.lines().toList();

  @Test
  void should_solve() {
    assertThat(Part01.solve(INPUT)).isEqualTo(35);
  }

  @Test
  void should_map_seed_to_destination() {
    List<Mapping> mappings = List.of(
        new Mapping(List.of(new MappingRange(20L, 10L, 10L))),
        new Mapping(List.of(new MappingRange(30L, 20L, 10L))));

    long actual = Part01.toDestination(mappings, 12L);

    assertThat(actual).isEqualTo(32L);
  }
}
package fr.ninauve.renaud.adventofcode.year2023.day05;

import fr.ninauve.renaud.adventofcode.year2023.day05.Part01.MappingRange;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part01MappingTest {

  @ParameterizedTest
  @CsvSource(delimiterString = " -> ", value = {
      "3 -> true",
      "4 -> true",
      "5 -> true",
      "6 -> true",
      "2 -> false",
      "7 -> false",
      "42 -> false",
  })
  void should_matches(long value, boolean expectedMatches) {
    MappingRange range = new MappingRange(42L, 3L, 4L);
    assertThat(range.matchesSource(value)).isEqualTo(expectedMatches);
  }

  @ParameterizedTest
  @CsvSource(delimiterString = " -> ", value = {
      "3 -> 42",
      "4 -> 43",
      "5 -> 44",
      "6 -> 45"
  })
  void should_map_range_to_destination(long value, long expectedDestination) {
    MappingRange range = new MappingRange(42L, 3L, 4L);
    assertThat(range.toDestination(value)).isEqualTo(expectedDestination);
  }

  @ParameterizedTest
  @CsvSource(delimiterString = " -> ", value = {
      "2 -> 2",
      "3 -> 42",
      "4 -> 43",
      "5 -> 44",
      "6 -> 45",
      "7 -> 7",
      "40 -> 80",
      "41 -> 81",
      "42 -> 42"
  })
  void should_map_to_destination(long value, long expectedDestination) {
    MappingRange range1 = new MappingRange(42L, 3L, 4L);
    MappingRange range2 = new MappingRange(80L, 40L, 2L);
    Part01.Mapping mapping = new Part01.Mapping(List.of(range1, range2));
    assertThat(mapping.toDestination(value)).isEqualTo(expectedDestination);
  }
}
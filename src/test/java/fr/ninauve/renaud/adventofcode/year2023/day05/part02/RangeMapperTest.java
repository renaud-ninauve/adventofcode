package fr.ninauve.renaud.adventofcode.year2023.day05.part02;

import fr.ninauve.renaud.adventofcode.year2023.day05.part02.RangeMapper.MappedRange;
import org.junit.jupiter.api.Test;

import static fr.ninauve.renaud.adventofcode.year2023.day05.part02.Range.rangeLength;
import static fr.ninauve.renaud.adventofcode.year2023.day05.part02.Range.rangeStartEnd;
import static org.assertj.core.api.Assertions.assertThat;

class RangeMapperTest {

  @Test
  void should_map_empty_rangeLength() {
    RangeMapper rangeMapper = new RangeMapper(40L, 10L, 5L);
    MappedRange actual = rangeMapper.map(Range.EMPTY);
    assertThat(actual).isEqualTo(new MappedRange(Range.EMPTY, Range.EMPTY));
  }

  @Test
  void should_map_range_Length_of_size_3() {
    RangeMapper rangeMapper = new RangeMapper(40L, 10L, 5L);
    MappedRange actual = rangeMapper.map(rangeStartEnd(11L, 13L));
    assertThat(actual).isEqualTo(
        new MappedRange(
            rangeStartEnd(11L, 13L),
            rangeStartEnd(41L, 43L)));
  }

  @Test
  void should_map_range_Length_of_size_20() {
    RangeMapper rangeMapper = new RangeMapper(40L, 10L, 5L);
    MappedRange actual = rangeMapper.map(rangeStartEnd(5L, 25L));
    assertThat(actual).isEqualTo(
        new MappedRange(
            rangeStartEnd(10L, 14L),
            rangeStartEnd(40L, 44L)));
  }

  @Test
  void should_map_range_Length_before() {
    RangeMapper rangeMapper = new RangeMapper(40L, 10L, 5L);
    MappedRange actual = rangeMapper.map(rangeLength(1L, 3L));
    assertThat(actual).isEqualTo(
        new MappedRange(
            Range.EMPTY,
            Range.EMPTY));
  }

  @Test
  void should_map_range_Length_after() {
    RangeMapper rangeMapper = new RangeMapper(40L, 10L, 5L);
    MappedRange actual = rangeMapper.map(rangeStartEnd(51L, 53L));
    assertThat(actual).isEqualTo(
        new MappedRange(
            Range.EMPTY,
            Range.EMPTY));
  }
}

package fr.ninauve.renaud.adventofcode.year2023.day05.part02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.ninauve.renaud.adventofcode.year2023.day05.part02.Range.rangeStartEnd;
import static org.assertj.core.api.Assertions.assertThat;

class RangeTest {

  @Test
  void should_remove_empty() {
    Range range = rangeStartEnd(10, 20);
    List<Range> actual = range.remove(Range.EMPTY);
    assertThat(actual).isEqualTo(List.of(range));
  }

  @Test
  void should_remove_before() {
    Range range = rangeStartEnd(10, 20);
    Range before = rangeStartEnd(0, 5);
    List<Range> actual = range.remove(before);
    assertThat(actual).isEqualTo(List.of(range));
  }

  @Test
  void should_remove_after() {
    Range range = rangeStartEnd(10, 20);
    Range after = rangeStartEnd(50, 55);
    List<Range> actual = range.remove(after);
    assertThat(actual).isEqualTo(List.of(range));
  }

  @Test
  void should_remove_middle() {
    Range range = rangeStartEnd(10, 20);
    Range middle = rangeStartEnd(12, 14);
    List<Range> actual = range.remove(middle);
    assertThat(actual).isEqualTo(List.of(
        rangeStartEnd(10, 11),
        rangeStartEnd(15, 20)));
  }

  @Test
  void should_remove_before_middle() {
    Range range = rangeStartEnd(10, 20);
    Range middle = rangeStartEnd(5, 14);
    List<Range> actual = range.remove(middle);
    assertThat(actual).isEqualTo(List.of(
        rangeStartEnd(15, 20)));
  }

  @Test
  void should_remove_after_middle() {
    Range range = rangeStartEnd(10, 20);
    Range middle = rangeStartEnd(15, 25);
    List<Range> actual = range.remove(middle);
    assertThat(actual).isEqualTo(List.of(
        rangeStartEnd(10, 14)));
  }

  @Test
  void should_remove_full() {
    Range range = rangeStartEnd(10, 20);
    List<Range> actual = range.remove(rangeStartEnd(0, 100));
    assertThat(actual).isEqualTo(List.of());
  }

  @Test
  void should_remove_all() {
    Range range = rangeStartEnd(10, 90);
    List<Range> actual = range.removeAll(List.of(
        rangeStartEnd(80, 100),
        rangeStartEnd(20, 30),
        rangeStartEnd(0, 5),
        rangeStartEnd(60, 70)
    ));
    assertThat(actual).isEqualTo(List.of(
        rangeStartEnd(10, 19),
        rangeStartEnd(31, 59),
        rangeStartEnd(71, 79)
    ));
  }

  @Test
  void should_remove_all2() {
    Range range = rangeStartEnd(10, 90);
    List<Range> actual = range.removeAll(List.of(
        rangeStartEnd(0, 50),
        rangeStartEnd(20, 100)
    ));
    assertThat(actual).isEqualTo(List.of());
  }

  @Test
  void should_remove_all3() {
    Range range = rangeStartEnd(10, 90);
    List<Range> actual = range.removeAll(List.of(
        rangeStartEnd(50, 90),
        rangeStartEnd(10, 50)
    ));
    assertThat(actual).isEqualTo(List.of());
  }
}
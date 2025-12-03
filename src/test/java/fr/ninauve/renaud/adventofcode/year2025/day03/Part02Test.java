package fr.ninauve.renaud.adventofcode.year2025.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {
  private static final List<String> INPUT = """
      987654321111111
      811111111111119
      234234234234278
      818181911112111
      """.lines().toList();

  @Test
  void should_solve() {
    assertThat(Part02.solve(INPUT)).isEqualTo(3121910778619L);
  }

  @Test
  void should_solve1() {
    assertThat(Part02.solve(List.of("987654321111111"))).isEqualTo(987654321111L);
  }

  @Test
  void should_solve2() {
    assertThat(Part02.solve(List.of("811111111111119"))).isEqualTo(811111111119L);
  }

  @Test
  void should_solve3() {
    assertThat(Part02.solve(List.of("234234234234278"))).isEqualTo(434234234278L);
  }

  @Test
  void should_solve4() {
    assertThat(Part02.solve(List.of("818181911112111"))).isEqualTo(888911112111L);
  }
}
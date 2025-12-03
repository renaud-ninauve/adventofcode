package fr.ninauve.renaud.adventofcode.year2025.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {
  private static final List<String> INPUT = """
      987654321111111
      811111111111119
      234234234234278
      818181911112111
      """.lines().toList();

  @Test
  void should_solve() {
    assertThat(Part01.solve(INPUT)).isEqualTo(357);
  }

  @Test
  void should_solve2() {
    assertThat(Part01.solve(List.of("981111119111111"))).isEqualTo(98);
  }
}
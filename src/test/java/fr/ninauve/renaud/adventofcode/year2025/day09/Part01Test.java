package fr.ninauve.renaud.adventofcode.year2025.day09;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {
  private static final List<String> INPUT = """
      7,1
      11,1
      11,7
      9,7
      9,5
      2,5
      2,3
      7,3
      """.lines().toList();

  @Test
  void should_solve() {
    assertThat(Part01.solve(INPUT)).isEqualTo(50L);
  }
}
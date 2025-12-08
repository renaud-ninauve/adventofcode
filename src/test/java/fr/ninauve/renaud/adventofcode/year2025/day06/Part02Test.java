package fr.ninauve.renaud.adventofcode.year2025.day06;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {
  private static final List<String> INPUT = """
      123 328  51 64\s
       45 64  387 23\s
        6 98  215 314
      *   +   *   + \s
      """.lines().toList();

  @Test
  void should_solve() {
    assertThat(Part02.solve(INPUT)).isEqualTo(3263827L);
  }
}
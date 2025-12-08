package fr.ninauve.renaud.adventofcode.year2025.day04;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {
  private static final List<String> INPUT = """
      ..@@.@@@@.
      @@@.@.@.@@
      @@@@@.@.@@
      @.@@@@..@.
      @@.@@@@.@@
      .@@@@@@@.@
      .@.@.@.@@@
      @.@@@.@@@@
      .@@@@@@@@.
      @.@.@@@.@.
      """.lines().toList();

  @Test
  void should_solve() {
    assertThat(Part02.solve(INPUT)).isEqualTo(43);
  }
}
package fr.ninauve.renaud.adventofcode.year2025.day07;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {
  private static final List<String> INPUT = """
      123 328  51 64\s
       45 64  387 23\s
        6 98  215 314
      *   +   *   + \s
      """.lines().toList();

  @Test
  void should_solve() {
    assertThat(Part01.solve(INPUT)).isEqualTo(4277556L);
  }
}
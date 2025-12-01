package fr.ninauve.renaud.adventofcode.year2025.day01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {
  private static final List<String> INPUT = """
      L68
      L30
      R48
      L5
      R60
      L55
      L1
      L99
      R14
      L82
      """.lines().toList();

  @Test
  void should_solve() {
    assertThat(Part01.solve(INPUT)).isEqualTo(3);
  }
}
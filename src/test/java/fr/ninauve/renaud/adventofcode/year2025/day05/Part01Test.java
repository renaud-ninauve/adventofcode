package fr.ninauve.renaud.adventofcode.year2025.day05;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Part01Test {
  private static final List<String> INPUT = """
      3-5
      10-14
      16-20
      12-18
      
      1
      5
      8
      11
      17
      32
      """.lines().toList();

  @Test
  void should_solve() {
    assertThat(Part01.solve(INPUT)).isEqualTo(3L);
  }
}
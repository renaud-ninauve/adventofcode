package fr.ninauve.renaud.adventofcode.year2025.day01;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static fr.ninauve.renaud.adventofcode.year2025.day01.UnboundedDial.normalize;
import static org.assertj.core.api.Assertions.assertThat;

class UnboundedDialTest {

  @ParameterizedTest
  @CsvSource(delimiterString = " -> ", value = {
      "0 -> 0",
      "99 -> 99",
      "200 -> 0",
      "212 -> 12",
      "-5 -> 95",
      "-205 -> 95",
      "-200 -> 0",
  })
  void should_normalize(int value, int expected) {
    UnboundedDial unbounded = new UnboundedDial(value);
    NormalizedDial actual = normalize(unbounded);
    assertThat(actual).isEqualTo(new NormalizedDial(expected));
  }
}
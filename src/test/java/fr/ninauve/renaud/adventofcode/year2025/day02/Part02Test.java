package fr.ninauve.renaud.adventofcode.year2025.day02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class Part02Test {
  private static final String INPUT = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";

  @Test
  void should_solve() {
    assertThat(Part02.solve(INPUT)).isEqualTo(4174379265L);
  }

  @ParameterizedTest
  @CsvSource(delimiterString = " -> ", value = {
      "1 -> false",
      "11 -> true",
      "12 -> false",
      "111 -> true",
      "121 -> false",
      "123123 -> true",
      "123456 -> false",
  })
  void isInvalid(String number, boolean expected) {
    boolean actual = Part02.isInvalid(number);
    assertThat(actual).isEqualTo(expected);
  }
}
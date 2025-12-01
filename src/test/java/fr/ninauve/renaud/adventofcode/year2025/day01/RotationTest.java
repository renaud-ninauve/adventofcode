package fr.ninauve.renaud.adventofcode.year2025.day01;

import fr.ninauve.renaud.adventofcode.year2025.day01.Rotation.LeftRotation;
import fr.ninauve.renaud.adventofcode.year2025.day01.Rotation.RightRotation;
import fr.ninauve.renaud.adventofcode.year2025.day01.Rotation.Rotated;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static fr.ninauve.renaud.adventofcode.year2025.day01.Rotation.rotate;
import static org.assertj.core.api.Assertions.assertThat;

class RotationTest {

  @ParameterizedTest
  @CsvSource(delimiterString = " ", value = {
      "start=5 incr=90 end=95 zeros=0",
      "start=5 incr=100 end=5 zeros=1",
      "start=5 incr=200 end=5 zeros=2",
      "start=0 incr=200 end=0 zeros=2",
      "start=0 incr=0 end=0 zeros=0"
  })
  void should_rotate_right(String startStr, String incrementStr, String expectedDialStr, String expectedZerosStr) {
    int start = Integer.parseInt(startStr.split("=")[1]);
    int increment = Integer.parseInt(incrementStr.split("=")[1]);
    int expectedDial = Integer.parseInt(expectedDialStr.split("=")[1]);
    int expectedZeros = Integer.parseInt(expectedZerosStr.split("=")[1]);

    NormalizedDial startDial = new NormalizedDial(start);
    Rotated actual = rotate(startDial, new RightRotation(increment));
    assertThat(actual).isEqualTo(new Rotated(new NormalizedDial(expectedDial), expectedZeros));
  }

  @ParameterizedTest
  @CsvSource(delimiterString = " ", value = {
      "start=95 incr=90 end=5 zeros=0",
      "start=95 incr=100 end=95 zeros=1",
      "start=95 incr=200 end=95 zeros=2",
      "start=0 incr=200 end=0 zeros=2",
      "start=0 incr=0 end=0 zeros=0"
  })
  void should_rotate_left(String startStr, String incrementStr, String expectedDialStr, String expectedZerosStr) {
    int start = Integer.parseInt(startStr.split("=")[1]);
    int increment = Integer.parseInt(incrementStr.split("=")[1]);
    int expectedDial = Integer.parseInt(expectedDialStr.split("=")[1]);
    int expectedZeros = Integer.parseInt(expectedZerosStr.split("=")[1]);

    NormalizedDial startDial = new NormalizedDial(start);
    Rotated actual = rotate(startDial, new LeftRotation(increment));
    assertThat(actual).isEqualTo(new Rotated(new NormalizedDial(expectedDial), expectedZeros));
  }
}
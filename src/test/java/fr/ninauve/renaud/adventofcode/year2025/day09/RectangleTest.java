package fr.ninauve.renaud.adventofcode.year2025.day09;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RectangleTest {

  @Test
  void area() {
    Rectangle rectange = new Rectangle(
        new Point(new Col(7), new Row(1)),
        new Point(new Col(11), new Row(7)));

    long actual = rectange.area();

    assertThat(actual).isEqualTo(35L);
  }
}
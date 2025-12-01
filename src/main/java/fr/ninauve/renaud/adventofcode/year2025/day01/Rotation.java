package fr.ninauve.renaud.adventofcode.year2025.day01;

import static fr.ninauve.renaud.adventofcode.year2025.day01.UnboundedDial.normalize;

public sealed interface Rotation {
  record LeftRotation(int value) implements Rotation {
  }

  record RightRotation(int value) implements Rotation {
  }

  record Rotated(NormalizedDial dial, int countZeros) {
  }

  static Rotated rotate(NormalizedDial normalizedDial, Rotation rotation) {
    return switch (rotation) {
      case LeftRotation left -> rotate(left, normalizedDial);
      case RightRotation right -> rotate(right, normalizedDial);
    };
  }

  private static Rotated rotate(LeftRotation rotation, NormalizedDial start) {
    UnboundedDial unboundedEnd = new UnboundedDial(start.value() - rotation.value());
    NormalizedDial endDial = normalize(unboundedEnd);
    final int countZeros;
    if (unboundedEnd.value() <= 0) {
      countZeros = -unboundedEnd.value() / 100 + (start.value() != 0 ? 1 : 0);
    } else {
      countZeros = 0;
    }
    return new Rotated(endDial, countZeros);
  }

  private static Rotated rotate(RightRotation rotation, NormalizedDial dial) {
    UnboundedDial unbounded = new UnboundedDial(dial.value() + rotation.value());
    NormalizedDial endDial = normalize(unbounded);
    return new Rotated(endDial, unbounded.value() / 100);
  }
}
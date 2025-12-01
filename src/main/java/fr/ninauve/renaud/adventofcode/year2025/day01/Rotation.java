package fr.ninauve.renaud.adventofcode.year2025.day01;

public sealed interface Rotation {
  record LeftRotation(int value) implements Rotation {
  }

  record RightRotation(int value) implements Rotation {
  }
}
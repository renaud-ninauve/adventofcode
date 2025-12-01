package fr.ninauve.renaud.adventofcode.year2025.day01;

public record NormalizedDial(int value) {
  public NormalizedDial(int value) {
    if (value < 0 || value >= 100) {
      throw new IllegalArgumentException();
    }
    this.value = value;
  }
}

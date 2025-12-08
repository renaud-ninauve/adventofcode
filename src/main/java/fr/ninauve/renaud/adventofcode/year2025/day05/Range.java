package fr.ninauve.renaud.adventofcode.year2025.day05;

public record Range(long start, long end) {
  public boolean isIncluded(long value) {
    return value >= start && value <= end;
  }
}

package fr.ninauve.renaud.adventofcode.year2025.day05;

public record Range(long start, long end) {
  public boolean isIncluded(long value) {
    return value >= start && value <= end;
  }

  public long size() {
    return 1 + end - start;
  }

  public boolean intersects(Range other) {
    return !(other.end() < this.start()
        || other.start() > this.end());
  }
}
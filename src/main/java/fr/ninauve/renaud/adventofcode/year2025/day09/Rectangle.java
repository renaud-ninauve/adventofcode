package fr.ninauve.renaud.adventofcode.year2025.day09;

import java.util.Comparator;
import java.util.stream.Stream;

public record Rectangle(Point a, Point b) {
  public Rectangle(Point a, Point b) {
    Comparator<Point> comparator = Comparator.comparing(Point::row).thenComparing(Point::col);
    Point min = Stream.of(a, b).min(comparator).get();
    Point max = Stream.of(a, b).max(comparator).get();
    this.a = min;
    this.b = max;
  }

  public long width() {
    return distance(a.col().value(), b.col().value()) + 1;
  }

  public long height() {
    return distance(a.row().value(), b.row().value()) + 1;
  }

  public long area() {
    return width() * height();
  }

  private long distance(long a, long b) {
    long max = Math.max(a, b);
    long min = Math.min(a, b);
    return max - min;
  }
}

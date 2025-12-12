package fr.ninauve.renaud.adventofcode.year2025.day09;

import java.util.Comparator;

public record Row(long value) implements Comparable<Row> {
  @Override
  public int compareTo(Row o) {
    return Comparator.comparing(Row::value).compare(this, o);
  }
}

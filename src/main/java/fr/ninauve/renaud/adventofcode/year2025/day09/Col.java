package fr.ninauve.renaud.adventofcode.year2025.day09;

import java.util.Comparator;

public record Col(long value) implements Comparable<Col> {
  @Override
  public int compareTo(Col o) {
    return Comparator.comparing(Col::value).compare(this, o);
  }
}
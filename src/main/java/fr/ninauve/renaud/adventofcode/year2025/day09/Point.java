package fr.ninauve.renaud.adventofcode.year2025.day09;

public record Point(Row row, Col col) {
  public Point(Col col, Row row) {
    this(row, col);
  }
}
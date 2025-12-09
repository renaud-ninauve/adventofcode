package fr.ninauve.renaud.adventofcode.year2025.day07;

import lombok.Builder;

@Builder
public record Location(int row, int col) {
  public Location above() {
    return new Location(row - 1, col);
  }

  public Location right() {
    return new Location(row, col + 1);
  }

  public Location left() {
    return new Location(row, col - 1);
  }

  public boolean isValid(Location max) {
    if (row < 0 || col < 0) {
      return false;
    }
    if (row > max.row() || col > max.col()) {
      return false;
    }
    return true;
  }
}

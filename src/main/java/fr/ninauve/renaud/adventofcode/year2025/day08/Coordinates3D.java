package fr.ninauve.renaud.adventofcode.year2025.day08;

public record Coordinates3D(long x, long y, long z) {

  public static double distance(Coordinates3D a, Coordinates3D b) {
    return Math.sqrt(Math.pow(b.x() - a.x(), 2)
        + Math.pow(b.y() - a.y(), 2)
        + Math.pow(b.z() - a.z(), 2));
  }
}

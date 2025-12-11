package fr.ninauve.renaud.adventofcode.year2025.day08;

import java.util.Comparator;
import java.util.stream.Stream;

public record Vector3D(Coordinates3D a, Coordinates3D b) {
  public Vector3D(Coordinates3D a, Coordinates3D b) {
    Coordinates3D first = Stream.of(a, b)
        .min(Comparator.comparing(Coordinates3D::x)
            .thenComparing(Coordinates3D::y)
            .thenComparing(Coordinates3D::z))
        .get();

    Coordinates3D second = a == first ? b : a;
    this.a = first;
    this.b = second;
  }
}

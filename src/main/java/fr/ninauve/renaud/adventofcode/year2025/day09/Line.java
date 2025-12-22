package fr.ninauve.renaud.adventofcode.year2025.day09;

import java.util.stream.Stream;

public sealed interface Line permits Horizontal, Vertical {
    Stream<Point> endPoints();
}

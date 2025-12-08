package fr.ninauve.renaud.adventofcode.year2025.day06;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.LongBinaryOperator;

public enum Operator {
  ADD('+', Long::sum, 0L), MULT('*', (a, b) -> a * b, 1L);
  private final char value;
  private final LongBinaryOperator operation;
  private final long identity;

  Operator(char value, LongBinaryOperator operation, long identity) {
    this.value = value;
    this.operation = operation;
    this.identity = identity;
  }

  public long identity() {
    return identity;
  }

  public LongBinaryOperator operation() {
    return operation;
  }

  public static Optional<Operator> parse(char value) {
    return Arrays.stream(Operator.values())
        .filter(op -> op.value == value)
        .findFirst();
  }
}

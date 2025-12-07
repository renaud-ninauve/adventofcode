package fr.ninauve.renaud.adventofcode.year2025.day07;

import java.util.Arrays;
import java.util.function.LongBinaryOperator;

public enum Operator {
  ADD("+", Long::sum, 0L), MULT("*", (a, b) -> a * b, 1L);
  private final String value;
  private final LongBinaryOperator operation;
  private final long identity;

  Operator(String value, LongBinaryOperator operation, long identity) {
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

  public static Operator parse(String value) {
    return Arrays.stream(Operator.values())
        .filter(op -> op.value.equals(value))
        .findFirst()
        .orElseThrow();
  }
}

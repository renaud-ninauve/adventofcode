package fr.ninauve.renaud.adventofcode.year2025.day07;

import java.util.List;

public record Problem(List<Long> operands, Operator operator) {

  public long compute() {
    return operands.stream()
        .mapToLong(Long::longValue)
        .reduce(operator.identity(), operator().operation());
  }
}

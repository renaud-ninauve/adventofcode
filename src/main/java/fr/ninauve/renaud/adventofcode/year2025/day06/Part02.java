package fr.ninauve.renaud.adventofcode.year2025.day06;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part02 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part02.class.getResource("/year2025/day06/input.txt").toURI()));
    System.out.println(Part02.solve(input));
  }

  public static long solve(List<String> input) {
    List<Problem> problems = parse(input);
    return problems.stream()
        .mapToLong(Problem::compute)
        .sum();
  }

  public static List<Problem> parse(List<String> input) {
    List<String> operandsLines = input.subList(0, input.size() - 1);
    String operatorsLine = input.getLast();

    List<Operator> operators = parseOperators(operatorsLine).reversed();
    int lineLength = input.getFirst().length();

    List<List<Long>> operands = IntStream.iterate(lineLength - 1, index -> index >= 0, i -> i - 1)
        .mapToObj(index -> parseOperand(operandsLines, index))
        .collect(
            () -> {
              return new ArrayList<List<Long>>();
            },
            (result, operand) -> {
              if (operand.isEmpty()) {
                result.add(new ArrayList<>());
                return;
              }
              if (result.isEmpty()) {
                result.add(new ArrayList<>());
              }
              result.getLast().add(operand.get());
            },
            (a, b) -> {
            });

    return IntStream.range(0, operators.size())
        .mapToObj(i -> new Problem(operands.get(i), operators.get(i)))
        .toList();
  }

  private static Optional<Long> parseOperand(List<String> operandsLines, int index) {
    String operandStr = IntStream.range(0, operandsLines.size())
        .mapToObj(lineIndex -> "" + operandsLines.get(lineIndex).charAt(index))
        .filter(c -> !c.isBlank())
        .collect(Collectors.joining());
    return operandStr.isEmpty() ? Optional.empty() : Optional.of(Long.parseLong(operandStr));
  }

  private static List<Operator> parseOperators(String line) {
    return line.chars()
        .mapToObj(i -> (char) i)
        .map(Operator::parse)
        .flatMap(Optional::stream)
        .toList();
  }
}

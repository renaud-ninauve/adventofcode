package fr.ninauve.renaud.adventofcode.year2025.day07;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part01 {

  public static void main(String... args) throws Exception {
    List<String> input = Files.readAllLines(Path.of(Part01.class.getResource("/year2025/day07/input.txt").toURI()));
    System.out.println(Part01.solve(input));
  }

  public static long solve(List<String> input) {
    List<Problem> problems = parse(input);
    return problems.stream()
        .mapToLong(p -> -p.operands().stream()
            .mapToLong(Long::longValue)
            .reduce(p.operator().identity(), p.operator().operation()))
        .sum() * -1;
  }

  public static List<Problem> parse(List<String> input) {
    List<List<Long>> operands = new ArrayList<>();
    List<Operator> operators = null;
    for (String line : input) {
      if (operands.size() < input.size() - 1) {
        operands.add(parseOperands(line));
      } else {
        operators = parseOperators(line);
      }
    }
    List<Problem> problems = new ArrayList<>();
    long problemsCount = operands.getFirst().size();
    for (int problemIndex = 0; problemIndex < problemsCount; problemIndex++) {
      List<Long> problemOperands = new ArrayList<>();
      for (int operandIndex = 0; operandIndex < operands.size(); operandIndex++) {
        long operand = operands.get(operandIndex).get(problemIndex);
        problemOperands.add(operand);
      }
      Operator problemOperator = operators.get(problemIndex);
      problems.add(new Problem(problemOperands, problemOperator));
    }
    return problems;
  }

  private static List<Long> parseOperands(String line) {
    List<Long> operands = new ArrayList<>();
    Scanner scanner = new Scanner(line);
    while (scanner.hasNextLong()) {
      operands.add(scanner.nextLong());
    }
    return operands;
  }

  private static List<Operator> parseOperators(String line) {
    List<Operator> operators = new ArrayList<>();
    Scanner scanner = new Scanner(line);
    while (scanner.hasNext()) {
      operators.add(Operator.parse(scanner.next()));
    }
    return operators;
  }
}

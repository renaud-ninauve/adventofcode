package fr.ninauve.renaud.adventofcode.year2024.day07;

import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class Part01 {
    private final List<InputLine> lines;

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(fr.ninauve.renaud.adventofcode.year2024.day03.Part01.class
                .getResource("/year2024/day07/input.txt").toURI()), StandardCharsets.UTF_8);

        Part01 part01 = Part01.parse(input);
        System.out.println(part01.solve());
    }

    public static Part01 parse(List<String> lines) {
        List<InputLine> inputLines = lines.stream()
                .map(line -> {
                    String[] resultNumbers = line.split(":");
                    final long result = Long.parseLong(resultNumbers[0]);
                    List<Long> numbers = Arrays.stream(resultNumbers[1].split(" "))
                            .filter(str -> !str.isBlank() && !str.isEmpty())
                            .map(Long::parseLong).toList();
                    return new InputLine(result, numbers);
                }).toList();
        return new Part01(inputLines);
    }

    public long solve() {
        return lines.stream()
                .map(line -> expressionsWithAresultOf(line.result(), line.numbers()))
                .filter(list -> !list.isEmpty())
                .map(List::getFirst)
                .mapToLong(Expression::compute)
                .sum();
    }

    public static List<Expression> expressionsWithAresultOf(long result, List<Long> numbers) {
        final List<Expression> valid = new ArrayList<>();
        int operatorsSize = numbers.size() - 1;
        for (int i = 0; i < Math.pow(2, operatorsSize); i++) {
            String binaryString = Integer.toBinaryString(i);
            binaryString = "0".repeat(operatorsSize - binaryString.length()) + binaryString;
            System.out.println(binaryString);

            List<Operator> operators = binaryString.chars()
                    .mapToObj(c -> c == '0' ? Operator.ADD : Operator.MULTIPLY)
                    .toList();
            Expression expression = new Expression(numbers, operators);
            if (expression.compute() == result) {
                valid.add(expression);
            }
        }
        return valid;
    }
}

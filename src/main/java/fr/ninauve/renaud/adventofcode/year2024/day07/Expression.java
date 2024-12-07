package fr.ninauve.renaud.adventofcode.year2024.day07;

import java.util.List;

public record Expression(List<Long> numbers, List<Operator> operators) {
    public long compute() {
        if (operators.size() != numbers.size() - 1) {
            throw new IllegalArgumentException("mismatch between numbers and operators size");
        }

        long total = 0;
        for (int i = 0; i < numbers().size(); i++) {
            Long current = numbers.get(i);
            if (i == 0) {
                total = current;
                continue;
            }
            Operator pperator = operators.get(i - 1);
            total = pperator.apply(total, current);
        }
        return total;
    }
}

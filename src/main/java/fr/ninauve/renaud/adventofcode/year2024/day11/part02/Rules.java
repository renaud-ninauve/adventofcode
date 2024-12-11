package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import fr.ninauve.renaud.adventofcode.year2024.day11.part02.Numbers.NumberCount;

import java.util.List;

public class Rules {
    final List<Rule> rules = List.of(
            new ZeroRule(), new EvenDigitsRule(), new DefaultRule()
    );

    public Numbers apply(Numbers numbers) {
        final Numbers result = new Numbers();
        for (NumberCount currentNumberCount : numbers.counts()) {
            List<Number> newNumbers = apply(currentNumberCount.number());
            Long currentCount = currentNumberCount.count();
            for (Number newNumber : newNumbers) {
                result.add(new NumberCount(newNumber, currentCount));
            }
        }
        return result;
    }

    private List<Number> apply(Number value) {
        return rules.stream()
                .filter(rule -> rule.matches(value))
                .findFirst()
                .map(rule -> rule.apply(value))
                .orElseThrow();
    }
}

package fr.ninauve.renaud.adventofcode.year2024.day11;

import java.util.List;

public class Rules {
    final List<Rule> rules = List.of(
            new ZeroRule(), new EvenDigitsRule(), new DefaultRule()
    );

    public List<Long> apply(List<Long> values) {
        return values.stream()
                .map(this::apply)
                .flatMap(List::stream)
                .toList();
    }

    public List<Long> apply(long value) {
        return rules.stream()
                .filter(rule -> rule.matches(value))
                .findFirst()
                .map(rule -> rule.apply(value))
                .orElseThrow();
    }
}

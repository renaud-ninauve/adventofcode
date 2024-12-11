package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import java.util.List;

public class Rules {
    final List<Rule> rules = List.of(
            new ZeroRule(), new EvenDigitsRule(), new DefaultRule()
    );

    public void apply(Node<Long> head) {
        Node<Long> current = head;
        while (current != null) {
            Node<Long> transformedNode = applyToNode(current);
            current = transformedNode.next();
        }
    }

    public Node<Long> applyToNode(Node<Long> node) {
        return rules.stream()
                .filter(rule -> rule.matches(node))
                .findFirst()
                .map(rule -> rule.apply(node))
                .orElseThrow();
    }
}

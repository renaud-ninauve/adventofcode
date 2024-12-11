package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

public class ZeroRule implements Rule {
    @Override
    public Node<Long> apply(Node<Long> node) {
        node.setValue(1L);
        return node;
    }

    @Override
    public boolean matches(Node<Long> node) {
        return node.value() == 0;
    }
}

package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

public class DefaultRule implements Rule {
    @Override
    public Node<Long> apply(Node<Long> node) {
        node.setValue(node.value() * 2024);
        return node;
    }

    @Override
    public boolean matches(Node<Long> node) {
        return true;
    }
}

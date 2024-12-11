package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import java.util.List;

public interface Rule {
    Node<Long> apply(Node<Long> node);
    boolean matches(Node<Long> node);
}

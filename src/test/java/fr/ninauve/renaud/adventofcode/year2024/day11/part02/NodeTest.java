package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NodeTest {

    @Test
    void fromValues() {
        Node<Integer> actualHead = Node.fromValues(List.of(1, 2, 3, 4, 5));
        assertThat(actualHead.values()).containsExactly(1, 2, 3, 4, 5);
    }

    @Test
    void split() {
        Node<Integer> actualHead = Node.fromValues(List.of(1, 2, 3, 4, 5));
        Node<Integer> secondNode = actualHead.next();
        List<Node<Integer>> actual = secondNode.split(21, 22);
        assertThat(actualHead.values()).containsExactly(1, 21, 22, 3, 4, 5);
        assertThat(actual.get(0).value()).isEqualTo(21);
        assertThat(actual.get(1).value()).isEqualTo(22);
    }
}
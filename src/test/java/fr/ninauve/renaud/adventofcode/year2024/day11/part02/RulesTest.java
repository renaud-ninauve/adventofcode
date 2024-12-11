package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RulesTest {

    @Test
    void apply() {
        Rules rules = new Rules();
        List<Long> values = List.of(125L, 17L);
        Node<Long> head = Node.fromValues(values);
        for (int i = 0; i < 6; i++) {
            rules.apply(head);
        }
        assertThat(head.values()).containsExactly(2097446912L, 14168L, 4048L, 2L, 0L, 2L, 4L, 40L, 48L, 2024L, 40L, 48L, 80L, 96L, 2L, 8L, 6L, 7L, 6L, 0L, 3L, 2L);
    }

    @Test
    void apply25() {
        Rules rules = new Rules();
        List<Long> values = List.of(125L, 17L);
        Node<Long> head = Node.fromValues(values);
        for (int i = 0; i < 25; i++) {
            rules.apply(head);
        }
        assertThat(head.values()).hasSize(55312);
    }
}
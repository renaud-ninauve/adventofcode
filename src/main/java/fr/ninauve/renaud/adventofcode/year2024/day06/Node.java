package fr.ninauve.renaud.adventofcode.year2024.day06;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node<T> {
    private T value;
    private Node<T> next;
}

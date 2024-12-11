package fr.ninauve.renaud.adventofcode.year2024.day11.part02;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T value;
    private Node<T> next;

    public static <T> Node<T> fromValues(List<T> values) {
        Node<T> head = new Node<>();
        head.value = values.getFirst();
        Node<T> current = head;
        boolean first = true;
        for(T value: values) {
            if (first) {
                first = false;
                continue;
            }
            current = current.append(value);
        }
        return head;
    }

    public T value() {
        return value;
    }

    public Node<T> next() {
        return next;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<Node<T>> split(T value1, T value2) {
        Node<T> newNode = new Node<>();
        newNode.value = value2;
        newNode.next = this.next;
        this.value = value1;
        this.next = newNode;
        return List.of(this, newNode);
    }

    public Node<T> append(T value) {
        Node<T> newNode = new Node<>();
        newNode.value = value;
        this.next = newNode;
        return newNode;
    }

    public List<T> values() {
        final List<T> values = new ArrayList<>();
        Node<T> current = this;
        while(current != null) {
            values.add(current.value);
            current = current.next;
        }
        return values;
    }
}

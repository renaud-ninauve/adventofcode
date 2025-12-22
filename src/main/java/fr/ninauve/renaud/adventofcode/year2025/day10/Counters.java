package fr.ninauve.renaud.adventofcode.year2025.day10;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Counters(List<Counter> counters) {
    public String toString() {
        return "{" + counters.stream().map(Counter::value).map(Object::toString).collect(Collectors.joining(",")) + "}";
    }

    public Stream<Counter> stream() {
        return counters.stream();
    }

    public Counter counterAt(int index) {
        return counters.get(index);
    }

    public int count() {
        return counters.size();
    }
}

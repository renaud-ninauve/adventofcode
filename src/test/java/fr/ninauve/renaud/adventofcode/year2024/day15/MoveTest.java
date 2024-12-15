package fr.ninauve.renaud.adventofcode.year2024.day15;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MoveTest {

    @Test
    void fromInput() {
        List<Move> actual = Move.fromInput(List.of("^v<>", "<vv>"));
        assertThat(actual).containsExactly(Move.UP, Move.DOWN, Move.LEFT, Move.RIGHT, Move.LEFT, Move.DOWN, Move.DOWN, Move.RIGHT);
    }
}
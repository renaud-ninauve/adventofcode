package fr.ninauve.renaud.adventofcode.year2023.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ColumnsGroupTest {

    @Test
    void neighbours() {
        ColumnsGroup group = new ColumnsGroup(3, 4, 7);
        List<Coordinates> actual = group.neighbours();
        assertThat(actual).containsExactlyInAnyOrder(
                // up left
                new Coordinates(2, 3),

                new Coordinates(2, 4),
                new Coordinates(2, 5),
                new Coordinates(2, 6),
                new Coordinates(2, 7),

                // up right
                new Coordinates(2, 8),
                // right
                new Coordinates(3, 8),
                // down right
                new Coordinates(4, 8),

                new Coordinates(4, 7),
                new Coordinates(4, 6),
                new Coordinates(4, 5),
                new Coordinates(4, 4),

                // down left
                new Coordinates(4, 3),

                // left
                new Coordinates(3, 3)
        );
    }
}
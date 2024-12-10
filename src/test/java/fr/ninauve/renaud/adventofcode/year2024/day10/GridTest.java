package fr.ninauve.renaud.adventofcode.year2024.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void fromInput() {
        Grid<Height> actual = Grid.fromInput(List.of(
                "12",
                "34",
                "56"
        ));

        assertThat(actual.getNbRows()).isEqualTo(3);
        assertThat(actual.getNbCols()).isEqualTo(2);

        assertThat(actual.get(new Coordinates(0, 0))).isEqualTo(new Height(1));
        assertThat(actual.get(new Coordinates(0, 1))).isEqualTo(new Height(2));

        assertThat(actual.get(new Coordinates(1, 0))).isEqualTo(new Height(3));
        assertThat(actual.get(new Coordinates(1, 1))).isEqualTo(new Height(4));

        assertThat(actual.get(new Coordinates(2, 0))).isEqualTo(new Height(5));
        assertThat(actual.get(new Coordinates(2, 1))).isEqualTo(new Height(6));
    }
}
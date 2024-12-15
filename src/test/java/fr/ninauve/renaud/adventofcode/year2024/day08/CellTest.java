package fr.ninauve.renaud.adventofcode.year2024.day08;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void delta() {
        Coordinates origin = new Coordinates(4, 2);
        Coordinates target = new Coordinates(7, 1);

        Coordinates actual = origin.delta(target);

        assertThat(actual).isEqualTo(new Coordinates(3, -1));
    }
}
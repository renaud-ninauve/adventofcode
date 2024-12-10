package fr.ninauve.renaud.adventofcode.year2023.day02;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    @Test
    void fromInput() {
        Game actual = Game.fromInput("Game 42: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
        assertThat(actual.id()).isEqualTo(42);
        assertThat(actual.draws()).isEqualTo(List.of(
                new Cubes(Map.of(
                        Color.RED, 4,
                        Color.BLUE, 3
                )),
                new Cubes(Map.of(
                        Color.RED, 1,
                        Color.GREEN, 2,
                        Color.BLUE, 6
                )),
                new Cubes(Map.of(
                        Color.GREEN, 2
                ))));
    }
}
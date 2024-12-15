package fr.ninauve.renaud.adventofcode.year2024.day15;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void fromInput() {
        Game actual = Game.fromInput(List.of(
                "#O.@",
                "###O",
                "",
                "^v<>",
                "<vv>"
        ));

        assertThat(actual.grid().getNbRows()).isEqualTo(2);
        assertThat(actual.grid().getNbCols()).isEqualTo(4);
        assertThat(actual.grid().get(new Cell(0, 0))).isEqualTo(CellContent.WALL);
        assertThat(actual.grid().get(new Cell(1, 3))).isEqualTo(CellContent.BOX);

        assertThat(actual.moves()).hasSize(8);
        assertThat(actual.moves().getFirst()).isEqualTo(Move.UP);
        assertThat(actual.moves().getLast()).isEqualTo(Move.RIGHT);
    }
}
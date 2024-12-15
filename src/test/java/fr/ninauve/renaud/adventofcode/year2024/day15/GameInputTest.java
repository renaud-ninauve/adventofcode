package fr.ninauve.renaud.adventofcode.year2024.day15;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameInputTest {

    @Test
    void fromInput() {
        GameInput actual = GameInput.fromInput(List.of(
                "#O.@",
                "###O",
                "",
                "^v<>",
                "<vv>"
        ));

        assertThat(actual.getGrid().getNbRows()).isEqualTo(2);
        assertThat(actual.getGrid().getNbCols()).isEqualTo(4);
        assertThat(actual.getGrid().get(new Cell(0, 0))).isEqualTo(CellContent.WALL);
        assertThat(actual.getGrid().get(new Cell(1, 3))).isEqualTo(CellContent.BOX);

        assertThat(actual.getMoves()).hasSize(8);
        assertThat(actual.getMoves().getFirst()).isEqualTo(Move.UP);
        assertThat(actual.getMoves().getLast()).isEqualTo(Move.RIGHT);
    }
}
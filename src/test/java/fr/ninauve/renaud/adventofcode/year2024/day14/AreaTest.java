package fr.ninauve.renaud.adventofcode.year2024.day14;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AreaTest {

    @ParameterizedTest
    @CsvSource(delimiter = ' ', value = {
            "10 10 UP_LEFT",
            "90 10 UP_RIGHT",
            "90 90 DOWN_RIGHT",
            "10 90 DOWN_LEFT",
            "50 10 NONE",
            "10 51 NONE",
    })
    void quandrantOf(long x, long y, Area.Quadrant expected) {
        Area area = new Area(101L, 103L);
        Area.Quadrant actual = area.quadrantOf(new Location(x, y));
        assertThat(actual).isEqualTo(expected);
    }
}
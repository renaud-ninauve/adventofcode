package fr.ninauve.renaud.adventofcode.year2024.day17;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CpuTest {

    @Test
    void fromInput() {
        Cpu actual = Cpu.fromInput(List.of(
                "Register A: 729",
                "Register B: 0",
                "Register C: 0",
                "",
                "Program: 0,1,5,4,3,0"
        ));

        assertThat(actual.a()).isEqualTo(new BigWord(729));
        assertThat(actual.b()).isEqualTo(new BigWord(0));
        assertThat(actual.c()).isEqualTo(new BigWord(0));
        assertThat(actual.instructionPointer()).isEqualTo(new Word(0));
        assertThat(actual.program().values()).containsExactlyInAnyOrderEntriesOf(Map.of(
                new Word(0), new Word(0),
                new Word(1), new Word(1),
                new Word(2), new Word(5),
                new Word(3), new Word(4),
                new Word(4), new Word(3),
                new Word(5), new Word(0)
        ));
    }
}
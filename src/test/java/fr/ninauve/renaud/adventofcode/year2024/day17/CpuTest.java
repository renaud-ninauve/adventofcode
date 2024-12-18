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

        assertThat(actual.a()).isEqualTo(new Register(729));
        assertThat(actual.b()).isEqualTo(new Register(0));
        assertThat(actual.c()).isEqualTo(new Register(0));
        assertThat(actual.instructionPointer()).isEqualTo(new TriBit(0));
        assertThat(actual.program().values()).containsExactlyInAnyOrderEntriesOf(Map.of(
                new TriBit(0), new TriBit(0),
                new TriBit(1), new TriBit(1),
                new TriBit(2), new TriBit(5),
                new TriBit(3), new TriBit(4),
                new TriBit(4), new TriBit(3),
                new TriBit(5), new TriBit(0)
        ));
    }
}
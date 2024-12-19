package fr.ninauve.renaud.adventofcode.year2024.day17;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BxlTest {

    static Stream<Arguments> execute() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                "Register A: 0",
                                "Register B: 11",
                                "Register C: 0",
                                "",
                                "Program: 1,4"
                        ),
                        List.of(
                                "Register A: 0",
                                "Register B: 15",
                                "Register C: 0",
                                "",
                                "Program: 1,4"
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void execute(List<String> input, List<String> expected) {
        Cpu cpu = Cpu.fromInput(input);

        Result actual = cpu.execute();

        Cpu expectedCpu = Cpu.fromInput(expected)
                .incrementInstructionPointerBy2();

        assertThat(actual).isEqualTo(new Result(expectedCpu, Optional.empty()));
    }
}
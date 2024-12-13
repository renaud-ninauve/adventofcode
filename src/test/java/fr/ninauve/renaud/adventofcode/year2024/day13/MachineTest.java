package fr.ninauve.renaud.adventofcode.year2024.day13;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MachineTest {

    static Stream<Arguments> goTo() {
        return Stream.of(
                Arguments.of(
                        new Location(8400, 5400),
                        new Location(94, 34),
                        new Location(22, 67),
                        80,
                        40
                ),
                Arguments.of(
                        new Location(12748, 12176),
                        new Location(26, 66),
                        new Location(67, 21),
                        0,
                        0
                ),
                Arguments.of(
                        new Location(7870, 6450),
                        new Location(17, 86),
                        new Location(84, 37),
                        38,
                        86
                ),
                Arguments.of(
                        new Location(18641, 10279),
                        new Location(69, 23),
                        new Location(27, 71),
                        0,
                        0
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void goTo(Location prizeLocation, Location deltaA, Location deltaB, int clicksA, int clicksB) {
        Machine machineUsingDFS = new MachineUsingDFS(List.of(new Button(ButtonId.A, deltaA), new Button(ButtonId.B, deltaB)));
        Machine machineUsingLoop = new MachineUsingLoop(List.of(new Button(ButtonId.A, deltaA), new Button(ButtonId.B, deltaB)));
        Machine machineUsingMaths = new MachineUsingMaths(List.of(new Button(ButtonId.A, deltaA), new Button(ButtonId.B, deltaB)));
        for (Machine machine : List.of(machineUsingDFS, machineUsingMaths, machineUsingLoop)) {
            goTo(prizeLocation, machine, clicksA, clicksB);
        }
    }

    void goTo(Location prizeLocation, Machine machine, int clicksA, int clicksB) {
        Map<ButtonId, BigInteger> actual = machine.clicksFor(prizeLocation);
        assertThat(actual.getOrDefault(ButtonId.A, BigInteger.ZERO)).isEqualTo(BigInteger.valueOf(clicksA));
        assertThat(actual.getOrDefault(ButtonId.B, BigInteger.ZERO)).isEqualTo(BigInteger.valueOf(clicksB));
    }
}
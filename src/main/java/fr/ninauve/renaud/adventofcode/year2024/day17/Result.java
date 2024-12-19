package fr.ninauve.renaud.adventofcode.year2024.day17;

import java.util.Optional;

public record Result(Cpu cpu, Optional<BigWord> output) {
    public static Result noOutputResult(Cpu cpu) {
        return new Result(cpu, Optional.empty());
    }
    public static Result outputResult(Cpu cpu, BigWord output) {
        return new Result(cpu, Optional.of(output));
    }
}

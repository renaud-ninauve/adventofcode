package fr.ninauve.renaud.adventofcode.year2024.day17.instruction;

import fr.ninauve.renaud.adventofcode.year2024.day17.Cpu;
import fr.ninauve.renaud.adventofcode.year2024.day17.Operand;
import fr.ninauve.renaud.adventofcode.year2024.day17.Result;

import java.util.Optional;

public class Out implements Instruction {
    @Override
    public Result execute(Cpu cpu) {
        long combo = new Operand().combo(cpu);
        int result = (int) (combo % 8);
        return Result.outputResult(cpu.incrementInstructionPointerBy2(), String.valueOf(Optional.of(Integer.toString(result))));
    }
}

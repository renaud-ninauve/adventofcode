package fr.ninauve.renaud.adventofcode.year2024.day17.instruction;

import fr.ninauve.renaud.adventofcode.year2024.day17.*;

public class Jnz implements Instruction {
    @Override
    public Result execute(Cpu cpu) {
        if (cpu.a().equals(BigWord.ZERO)) {
            return Result.noOutputResult(cpu.incrementInstructionPointerBy2());
        }
        long operand = new Operand().litteral(cpu);
        return Result.noOutputResult(cpu.instructionPointer(Word.valueOf(operand)));
    }
}

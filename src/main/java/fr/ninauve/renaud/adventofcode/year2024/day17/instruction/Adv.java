package fr.ninauve.renaud.adventofcode.year2024.day17.instruction;

import fr.ninauve.renaud.adventofcode.year2024.day17.*;

public class Adv implements Instruction {

    @Override
    public Result execute(Cpu cpu) {
        Operand operand = new Operand();
        long numerator = cpu.a().asLong();
        long denominatorPow = operand.combo(cpu);
        long result = (long) (numerator / Math.pow(2, denominatorPow));
        return Result.noOutputResult(
                cpu.registerA(BigWord.valueOf(result))
                        .incrementInstructionPointerBy2());
    }
}

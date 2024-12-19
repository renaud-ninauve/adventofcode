package fr.ninauve.renaud.adventofcode.year2024.day17.instruction;

import fr.ninauve.renaud.adventofcode.year2024.day17.BigWord;
import fr.ninauve.renaud.adventofcode.year2024.day17.Cpu;
import fr.ninauve.renaud.adventofcode.year2024.day17.Operand;
import fr.ninauve.renaud.adventofcode.year2024.day17.Result;

public class Cdv implements Instruction {

    @Override
    public Result execute(Cpu cpu) {
        Operand operand = new Operand();
        long numerator = cpu.a().asLong();
        long denominatorPow = operand.combo(cpu);
        long result = (long) (numerator / Math.pow(2, denominatorPow));
        return Result.noOutputResult(
                cpu.registerC(BigWord.valueOf(result))
                        .incrementInstructionPointerBy2());
    }
}

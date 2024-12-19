package fr.ninauve.renaud.adventofcode.year2024.day17.instruction;

import fr.ninauve.renaud.adventofcode.year2024.day17.BigWord;
import fr.ninauve.renaud.adventofcode.year2024.day17.Cpu;
import fr.ninauve.renaud.adventofcode.year2024.day17.Operand;
import fr.ninauve.renaud.adventofcode.year2024.day17.Result;

public class Bst implements Instruction {
    @Override
    public Result execute(Cpu cpu) {
        long operand = new Operand().combo(cpu);
        long result = operand % 8;
        return Result.noOutputResult(cpu.registerB(BigWord.valueOf(result)).incrementInstructionPointerBy2());
    }
}

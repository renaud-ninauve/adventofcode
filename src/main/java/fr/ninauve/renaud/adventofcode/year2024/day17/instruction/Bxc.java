package fr.ninauve.renaud.adventofcode.year2024.day17.instruction;

import fr.ninauve.renaud.adventofcode.year2024.day17.BigWord;
import fr.ninauve.renaud.adventofcode.year2024.day17.Cpu;
import fr.ninauve.renaud.adventofcode.year2024.day17.Operand;
import fr.ninauve.renaud.adventofcode.year2024.day17.Result;

public class Bxc implements Instruction {
    @Override
    public Result execute(Cpu cpu) {
        int b = cpu.b().asInt();
        int c = cpu.c().asInt();
        int result = b ^ c;
        return Result.noOutputResult(cpu.registerB(BigWord.valueOf(result)).incrementInstructionPointerBy2());
    }
}

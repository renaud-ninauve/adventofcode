package fr.ninauve.renaud.adventofcode.year2024.day17;

import java.util.HashMap;
import java.util.Map;

public interface Instruction {
    static Instruction forOpcode(Word opcode) {
        Map<Word, Instruction> instructions = new HashMap<>();
        instructions.put(Word.ZERO, new Adv());
        Instruction instruction = instructions.get(opcode);
        if (instruction == null) {
            throw new IllegalArgumentException("unknown opcode " + opcode);
        }
        return instruction;
    }

    Result execute(Cpu cpu);
}

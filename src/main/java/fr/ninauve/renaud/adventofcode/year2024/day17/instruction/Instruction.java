package fr.ninauve.renaud.adventofcode.year2024.day17.instruction;

import fr.ninauve.renaud.adventofcode.year2024.day17.Cpu;
import fr.ninauve.renaud.adventofcode.year2024.day17.Result;
import fr.ninauve.renaud.adventofcode.year2024.day17.Word;

import java.util.HashMap;
import java.util.Map;

public interface Instruction {
    static Instruction forOpcode(Word opcode) {
        Map<Word, Instruction> instructions = new HashMap<>();
        instructions.put(Word.ZERO, new Adv());
        instructions.put(Word.ONE, new Bxl());
        instructions.put(Word.TWO, new Bst());
        instructions.put(Word.THREE, new Jnz());
        instructions.put(Word.FOUR, new Bxc());
        instructions.put(Word.FIVE, new Out());
        instructions.put(Word.SIX, new Bdv());
        instructions.put(Word.SEVEN, new Cdv());
        Instruction instruction = instructions.get(opcode);
        if (instruction == null) {
            throw new IllegalArgumentException("unknown opcode " + opcode);
        }
        return instruction;
    }

    Result execute(Cpu cpu);
}

package fr.ninauve.renaud.adventofcode.year2024.day17;

import java.util.List;

public record Cpu(BigWord a, BigWord b, BigWord c, Word instructionPointer, Program program) {

    public static Cpu fromInput(List<String> input) {
        BigWord a = BigWord.fromInputLine(input.get(0));
        BigWord b = BigWord.fromInputLine(input.get(1));
        BigWord c = BigWord.fromInputLine(input.get(2));
        Program program1 = Program.fromInputLine(input.get(4));
        return new Cpu(a, b, c, new Word(0), program1);
    }

    public Result execute() {
        Word opcode = program.dataAt(instructionPointer);
        Instruction instruction = Instruction.forOpcode(opcode);
        return instruction.execute(this);
    }

    public Word dataAt(Word address) {
        return program.dataAt(address);
    }

    public Cpu registerA(BigWord data) {
        return new Cpu(data, b, c, instructionPointer, program);
    }

    public Cpu instructionPointer(Word newAddress) {
        return new Cpu(a, b, c, newAddress, program);
    }

    public Cpu incrementInstructionPointerBy2() {
        Word newInstructionPointer = instructionPointer.increment().increment();
        return new Cpu(a, b, c, newInstructionPointer, program);
    }
}

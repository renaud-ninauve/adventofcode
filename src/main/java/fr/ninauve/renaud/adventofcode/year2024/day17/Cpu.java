package fr.ninauve.renaud.adventofcode.year2024.day17;

import fr.ninauve.renaud.adventofcode.year2024.day17.instruction.Instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public record Cpu(BigWord a, BigWord b, BigWord c, Address instructionPointer, Program program) {

    public static Cpu fromInput(List<String> input) {
        BigWord a = BigWord.fromInputLine(input.get(0));
        BigWord b = BigWord.fromInputLine(input.get(1));
        BigWord c = BigWord.fromInputLine(input.get(2));
        Program program1 = Program.fromInputLine(input.get(4));
        return new Cpu(a, b, c, Address.ZERO, program1);
    }

    public List<String> print() {
        List<String> output = new ArrayList<>();
        output.add("Register A: " + a.toString());
        output.add("Register B: " + b.toString());
        output.add("Register C: " + c.toString());
        output.add("");
        List<Word> program = this.program.asList();
        for (int i = 0; i < program.size(); i += 2) {
            Word opcode = program.get(i);
            Word data = program.get(i + 1);
            Instruction instruction = Instruction.forOpcode(opcode);
            String inst = instruction.getClass().getSimpleName().toUpperCase(Locale.ROOT);
            output.add(inst + " " + data);
        }
        return output;
    }

    public Result execute() {
        Word opcode = program.dataAt(instructionPointer);
        Instruction instruction = Instruction.forOpcode(opcode);
        return instruction.execute(this);
    }

    public Word dataAt(Address address) {
        return program.dataAt(address);
    }

    public Cpu registerA(BigWord data) {
        return new Cpu(data, b, c, instructionPointer, program);
    }

    public Cpu registerB(BigWord data) {
        return new Cpu(a, data, c, instructionPointer, program);
    }

    public Cpu registerC(BigWord data) {
        return new Cpu(a, b, data, instructionPointer, program);
    }

    public Cpu instructionPointer(Address newAddress) {
        return new Cpu(a, b, c, newAddress, program);
    }

    public Cpu incrementInstructionPointerBy2() {
        Address newInstructionPointer = instructionPointer.increment().increment();
        return new Cpu(a, b, c, newInstructionPointer, program);
    }

    public boolean isHalt() {
        return !program.containsDataAt(instructionPointer);
    }
}

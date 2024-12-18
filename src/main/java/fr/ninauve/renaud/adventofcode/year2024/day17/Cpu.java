package fr.ninauve.renaud.adventofcode.year2024.day17;

import java.util.List;

public record Cpu(Register a, Register b, Register c, TriBit instructionPointer, Program program) {

    public static Cpu fromInput(List<String> input) {
        Register a = Register.fromInputLine(input.get(0));
        Register b = Register.fromInputLine(input.get(1));
        Register c = Register.fromInputLine(input.get(2));
        Program program1 = Program.fromInputLine(input.get(4));
        return new Cpu(a, b, c, new TriBit(0), program1);
    }

    public Cpu execute() {
        return null;
    }
}

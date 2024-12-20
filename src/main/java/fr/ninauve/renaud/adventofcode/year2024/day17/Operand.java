package fr.ninauve.renaud.adventofcode.year2024.day17;

public class Operand {

    public long litteral(Cpu cpu) {
        return BigWord.valueOf(litteralTribit(cpu)).asLong();
    }

    public long combo(Cpu cpu) {
        Word data = litteralTribit(cpu);
        if (data.compareTo(Word.THREE) <= 0) {
            return BigWord.valueOf(data).asLong();
        }
        if (data.equals(Word.FOUR)) {
            return cpu.a().asLong();
        }
        if (data.equals(Word.FIVE)) {
            return cpu.b().asLong();
        }
        if (data.equals(Word.SIX)) {
            return cpu.c().asLong();
        }
        throw new IllegalArgumentException("illegal operand : " + data);
    }

    private Word litteralTribit(Cpu cpu) {
        Address instructionPointer = cpu.instructionPointer();
        Address operandAddress = instructionPointer.increment();
        return cpu.dataAt(operandAddress);
    }
}

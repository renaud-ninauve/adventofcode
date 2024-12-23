package fr.ninauve.renaud.adventofcode.year2024.day17;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Part02 {

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part02.class
                .getResource("/year2024/day17/input.txt").toURI()), StandardCharsets.UTF_8);
        long result = Part02.solve(input);
        System.out.println(result);
    }

    public static int solve(List<String> input) {
        Cpu cpu = Cpu.fromInput(input);
        List<String> print = cpu.print();
        for (String line : print) {
            System.out.println(line);
        }
        // 1245884033
        List<BigWord> program = cpu.program().asList().stream().map(Word::asBigWord).toList();
        int a = 0;
        for (int i = 0; i < program.size(); i++) {
            final BigWord data = program.get(i);
            a = find(cpu, i, a, data);
            System.out.println("[" + i + "] " + Integer.toBinaryString(a));
        }
        return a;
    }

    private static int find(Cpu cpu, int index, int currentA, BigWord expected) {
        int distance = index * 3;
        final int mask = Integer.rotateLeft(7, distance);
        final int maskA = Integer.MAX_VALUE ^ mask;
        //final int maskedA = currentA & maskA; // bits corresponding to index are 0
        int maskI = 0;
        for(int i= 0; i<index; i++) {
            final int maskIndex = Integer.rotateLeft(7, i * 3);
            maskI = maskI | maskIndex;
        }
        maskI = ~maskI;
        final int maskedA = currentA & (~maskI);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            int maskedI = Integer.rotateLeft(i, distance) & maskI; // bits not corresponding to index are 1

            int a = maskedI | maskedA;
            Cpu newCpu = cpu.registerA(BigWord.valueOf(a));
            List<BigWord> output = Part02.execute(newCpu);
            if (output.size() > index && output.get(index).equals(expected)) {
                return a;
            }
        }
        return -1;
    }

    private static List<BigWord> execute(Cpu cpu) {
        List<BigWord> output = new ArrayList<>();
        while (!cpu.isHalt()) {
            Result result = cpu.execute();
            if (result.output().isPresent()) {
                output.add(result.output().get());
            }
            cpu = result.cpu();
        }
        return output;
    }

//    Register A: 60589763
//    Register B: 0
//    Register C: 0
//
//    BST 4 => B = A % 8
//    BXL 5 => B = B XOR 5       B = 1
//    CDV 5 => C = A / 2^B       C = 0
//    BXL 6 => B = B XOR 6       B = 6 + 2 = 4
//    BXC 1 => B = B XOR C
//    OUT 5 => PRINT B            B = 4
//    ADV 3 => A + A / 2^3
//    JNZ 0 => JUMP if A != 0
}

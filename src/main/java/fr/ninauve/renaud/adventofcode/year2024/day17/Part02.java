package fr.ninauve.renaud.adventofcode.year2024.day17;

import fr.ninauve.renaud.adventofcode.year2024.day16.Part01;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Part02 {

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part02.class
                .getResource("/year2024/day17/input.txt").toURI()), StandardCharsets.UTF_8);
        long result = Part01.solve(input);
        System.out.println(result);
    }

    public static int solve(List<String> input) {
        Cpu cpu = Cpu.fromInput(input);
        List<BigWord> program = cpu.program().asList().stream().map(Word::asBigWord).toList();
        for(int a=0; a<Integer.MAX_VALUE; a++) {
            List<BigWord> output = Part02.execute(cpu.registerA(BigWord.valueOf(a)));
            if (output.equals(program)) {
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
}

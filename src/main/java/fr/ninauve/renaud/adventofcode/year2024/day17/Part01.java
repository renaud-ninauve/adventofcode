package fr.ninauve.renaud.adventofcode.year2024.day17;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Part01 {

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(Part01.class
                .getResource("/year2024/day17/input.txt").toURI()), StandardCharsets.UTF_8);

        Cpu cpu = Cpu.fromInput(input);
        List<String> output = new ArrayList<>();
        while(!cpu.isHalt()) {
            Result result = cpu.execute();
            if (result.output().isPresent()) {
                output.add(result.output().get().toString());
            }
            cpu = result.cpu();
        }
        System.out.println(String.join(",", output));
    }
}

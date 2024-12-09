package fr.ninauve.renaud.adventofcode.year2024.day09;

import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class Part02 {
    private final List<Long> input;

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(fr.ninauve.renaud.adventofcode.year2024.day03.Part01.class
                .getResource("/year2024/day09/input.txt").toURI()), StandardCharsets.UTF_8);

        Part02 part01 = Part02.parse(input);
        System.out.println(part01.solve());
    }

    public static Part02 parse(List<String> lines) {
        return new Part02(lines.getFirst().chars()
                .mapToObj(c -> Character.toString((char) c))
                .map(Long::parseLong)
                .toList());
    }

    public long solve() {
        Disk disk = Disk.fromInput(input);
        disk.compact();
        return disk.checksum();
    }
}

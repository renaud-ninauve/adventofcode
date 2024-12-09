package fr.ninauve.renaud.adventofcode.year2024.day09;

import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class Part01 {
    private final List<Long> input;

    public static void main(String... args) throws Exception {
        final List<String> input = Files.readAllLines(Path.of(fr.ninauve.renaud.adventofcode.year2024.day03.Part01.class
                .getResource("/year2024/day09/input.txt").toURI()), StandardCharsets.UTF_8);

        Part01 part01 = Part01.parse(input);
        System.out.println(part01.solve());
    }

    public static Part01 parse(List<String> lines) {
        return new Part01(lines.getFirst().chars()
                .mapToObj(c -> Character.toString((char) c))
                .map(Long::parseLong)
                .toList());
    }

    public static List<Long> toDisk(List<Long> input) {
        final List<Long> disk = new ArrayList<>();
        for (int i = 0; i < input.size(); i += 2) {
            long fileId = i / 2;
            IntStream.range(0, input.get(i).intValue())
                    .forEach(f -> disk.add(fileId));

            if (i + 1 < input.size()) {
                IntStream.range(0, input.get(i + 1).intValue())
                        .forEach(f -> disk.add(-1L));
            }
        }
        return disk;
    }

    public static List<Long> compact(List<Long> disk) {
        final List<Long> compact = new ArrayList<>(disk);
        Indexes indexes = new Indexes(-1, disk.size());
        while ((indexes = Part01.findNext(compact, indexes)).isValid()) {
            Long startBlock = compact.get(indexes.start());
            compact.set(indexes.start(), compact.get(indexes.end()));
            compact.set(indexes.end, startBlock);
        }
        return compact;
    }

    private static Indexes findNext(List<Long> disk, Indexes current) {
        int nextStart = findFreeSpace(disk, current.start() + 1);
        int nextEnd = findFilledSpace(disk, current.end - 1);
        return new Indexes(nextStart, nextEnd);
    }

    private record Indexes(int start, int end) {
        public boolean isValid() {
            return start < end;
        }
    }

    private static int findFreeSpace(List<Long> disk, int from) {
        int index = from;
        while (disk.get(index) != -1L) {
            index++;
        }
        return index;
    }

    private static int findFilledSpace(List<Long> disk, int from) {
        int index = from;
        while (disk.get(index) == -1L) {
            index--;
        }
        return index;
    }

    public long solve() {
        List<Long> disk = toDisk(input);
        List<Long> compact = compact(disk);
        long sum = 0L;
        for (int i = 0; i < compact.size(); i++) {
            Long block = compact.get(i);
            if (block == -1L) {
                continue;
            }
            sum += i * block;
        }
        return sum;
    }
}

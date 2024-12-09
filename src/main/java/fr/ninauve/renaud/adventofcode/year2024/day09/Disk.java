package fr.ninauve.renaud.adventofcode.year2024.day09;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Disk {
    private final List<Blocks> blocksList;

    public static Disk fromInput(List<Long> input) {
        final List<Blocks> blocksList = new ArrayList<>();
        for (int i = 0; i < input.size(); i += 2) {
            long fileId = i / 2;
            long fileLength = input.get(i);
            blocksList.add(new FilledBlocks(fileLength, fileId));

            if (i + 1 < input.size()) {
                long freeLength = input.get(i + 1);
                blocksList.add(new EmptyBlocks(freeLength));
            }
        }
        return new Disk(blocksList);
    }

    public void compact() {
        FilledBlocks file = null;
        int fileIndex = -1;
        while ((fileIndex = findNextFile(file)) > 0) {
            file = (FilledBlocks) blocksList.get(fileIndex);
            int freeIndex = findFreeSpaceOfLength(file.length(), fileIndex);
            if (freeIndex < 0) {
                continue;
            }
            EmptyBlocks freeBlock = (EmptyBlocks) blocksList.get(freeIndex);
            blocksList.set(fileIndex, new EmptyBlocks(file.length()));
            blocksList.set(freeIndex, file);

            if (freeBlock.length() > file.length()) {
                long remaining = freeBlock.length() - file.length();
                EmptyBlocks newFreeBlocks = new EmptyBlocks(remaining);
                blocksList.add((int) freeIndex + 1, newFreeBlocks);
            }
        }
    }

    public List<Long> content() {
        final List<Long> content = new ArrayList<>();
        for (Blocks blocks : blocksList) {
            long value = blocks.isFree() ? -1L : ((FilledBlocks) blocks).fileId();
            for (int i = 0; i < blocks.length(); i++) {
                content.add(value);
            }
        }
        return content;
    }

    public long checksum() {
        long checksum = 0L;
        List<Long> content = content();
        for (int i = 0; i < content.size(); i++) {
            Long value = content.get(i);
            if (value == -1L) {
                continue;
            }
            checksum += value * i;
        }
        return checksum;
    }

    private int findNextFile(FilledBlocks previous) {
        for (int i = blocksList.size() - 1; i >= 0; i--) {
            Blocks blocks = blocksList.get(i);
            if (blocks instanceof FilledBlocks filledBlocks) {
                if (previous == null || filledBlocks.fileId() == previous.fileId() - 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int findFreeSpaceOfLength(long length, int maxIndex) {
        for (int i = 0; i < maxIndex; i++) {
            Blocks blocks = blocksList.get(i);
            if (!blocks.isFree()) {
                continue;
            }
            if (blocks.length() >= length) {
                return i;
            }
        }
        return -1;
    }
}

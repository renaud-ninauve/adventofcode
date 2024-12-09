package fr.ninauve.renaud.adventofcode.year2024.day09;

public record FilledBlocks(long length, long fileId) implements Blocks {
    @Override
    public boolean isFree() {
        return false;
    }
}

package fr.ninauve.renaud.adventofcode.year2024.day09;

public record EmptyBlocks(long length) implements Blocks{
    @Override
    public boolean isFree() {
        return true;
    }
}

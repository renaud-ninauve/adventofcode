package fr.ninauve.renaud.adventofcode.year2024.day09;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DiskTest {

    @Test
    void fromInput() {
        Disk disk = Disk.fromInput(List.of(2L, 3L, 3L, 3L, 1L, 3L, 3L, 1L, 2L, 1L, 4L, 1L, 4L, 1L, 3L, 1L, 4L, 0L, 2L));
        assertThat(disk.getBlocksList()).isEqualTo(List.of(
                new FilledBlocks(2, 0),
                new EmptyBlocks(3),
                new FilledBlocks(3, 1),
                new EmptyBlocks(3),
                new FilledBlocks(1, 2),
                new EmptyBlocks(3),
                new FilledBlocks(3, 3),
                new EmptyBlocks(1),
                new FilledBlocks(2, 4),
                new EmptyBlocks(1),
                new FilledBlocks(4, 5),
                new EmptyBlocks(1),
                new FilledBlocks(4, 6),
                new EmptyBlocks(1),
                new FilledBlocks(3, 7),
                new EmptyBlocks(1),
                new FilledBlocks(4, 8),
                new EmptyBlocks(0),
                new FilledBlocks(2, 9)
        ));
    }

    @Test
    void compact2() {
        Disk disk = Disk.fromInput(List.of(2L, 3L, 3L, 3L, 1L, 3L, 3L, 1L, 2L, 1L, 4L, 1L, 4L, 1L, 3L, 1L, 4L, 0L, 2L));
        disk.compact();
        assertThat(disk.getBlocksList()).isEqualTo(List.of(
                new FilledBlocks(2, 0),
                new FilledBlocks(2, 9),
                new FilledBlocks(1, 2),
                new FilledBlocks(3, 1),
                new FilledBlocks(3, 7),
                new EmptyBlocks(1),
                new FilledBlocks(2, 4),
                new EmptyBlocks(1),
                new FilledBlocks(3, 3),
                new EmptyBlocks(1),
                new EmptyBlocks(2),
                new EmptyBlocks(1),
                new FilledBlocks(4, 5),
                new EmptyBlocks(1),
                new FilledBlocks(4, 6),
                new EmptyBlocks(1),
                new EmptyBlocks(3),
                new EmptyBlocks(1),
                new FilledBlocks(4, 8),
                new EmptyBlocks(0),
                new EmptyBlocks(2)
        ));
    }

    @Test
    void compact() {
        Disk disk = Disk.fromInput(List.of(2L, 3L, 3L, 3L, 1L, 3L, 3L, 1L, 2L, 1L, 4L, 1L, 4L, 1L, 3L, 1L, 4L, 0L, 2L));
        disk.compact();
        assertThat(disk.content()).isEqualTo(List.of(
                0L, 0L, 9L, 9L, 2L, 1L, 1L, 1L, 7L, 7L, 7L, -1L, 4L, 4L, -1L, 3L, 3L, 3L, -1L, -1L, -1L, -1L, 5L, 5L, 5L, 5L, -1L, 6L, 6L, 6L, 6L, -1L, -1L, -1L, -1L, -1L, 8L, 8L, 8L, 8L, -1L, -1L
        ));
    }
}
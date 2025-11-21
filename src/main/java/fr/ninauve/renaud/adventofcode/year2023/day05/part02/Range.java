package fr.ninauve.renaud.adventofcode.year2023.day05.part02;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public record Range(long start, long length) {
  public static Range EMPTY = new Range(0, 0);

  public static Range rangeLength(long start, long length) {
    return new Range(start, length);
  }

  public static Range rangeStartEnd(long start, long end) {
    return new Range(start, end - start + 1);
  }

  public long end() {
    return start + length - 1;
  }

  public boolean isEmpty() {
    return length == 0;
  }

  public List<Range> remove(Range toBeRemoved) {
    if (toBeRemoved.isEmpty()) {
      return List.of(this);
    }
    if (toBeRemoved.end() < start()) {
      return List.of(this);
    }
    if (toBeRemoved.start() > end()) {
      return List.of(this);
    }
    if (toBeRemoved.start() <= start() && toBeRemoved.end() >= end()) {
      return List.of();
    }
    List<Range> result = new ArrayList<>();
    if (toBeRemoved.start() > start()) {
      result.add(rangeStartEnd(start(), toBeRemoved.start() - 1));
    }
    if (toBeRemoved.end() < end()) {
      result.add(rangeStartEnd(toBeRemoved.end() + 1, end()));
    }
    return result;
  }

  public List<Range> removeAll(List<Range> toBeRemovedList) {
    Deque<Range> remaining = new LinkedList<>();
    remaining.offer(this);
    for (Range toBeRemoved : toBeRemovedList) {
      if (remaining.isEmpty()) {
        return List.of();
      }
      Range lastRange = remaining.pollLast();
      List<Range> afterRemove = lastRange.remove(toBeRemoved);
      remaining.addAll(afterRemove);
    }
    return new ArrayList<>(remaining);
  }
}
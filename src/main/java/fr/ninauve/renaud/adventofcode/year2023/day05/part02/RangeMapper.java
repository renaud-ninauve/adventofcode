package fr.ninauve.renaud.adventofcode.year2023.day05.part02;

import java.util.List;

import static fr.ninauve.renaud.adventofcode.year2023.day05.part02.Range.EMPTY;
import static fr.ninauve.renaud.adventofcode.year2023.day05.part02.Range.rangeStartEnd;

public record RangeMapper(long destinationStart, long sourceStart, long length) {

  public MappedRange map(Range toBeMapped) {
    if (toBeMapped.isEmpty()) {
      return new MappedRange(EMPTY, EMPTY);
    }
    if (toBeMapped.end() < sourceStart) {
      return new MappedRange(EMPTY, EMPTY);
    }
    if (toBeMapped.start() > sourceEnd()) {
      return new MappedRange(EMPTY, EMPTY);
    }

    long start = Math.min(Math.max(sourceStart, toBeMapped.start()), sourceEnd());
    long end = Math.max(Math.min(sourceEnd(), toBeMapped.end()), sourceStart);

    long mappedStart = destinationStart + (start - sourceStart);
    long mappedEnd = destinationStart + (end - sourceStart);

    return new MappedRange(
        rangeStartEnd(start, end),
        rangeStartEnd(mappedStart, mappedEnd));
  }

  public record MappedRange(Range source, Range destination) {
  }

  public record MappingResult(Range destination, List<Range> notMappedSource) {
  }

  private long sourceEnd() {
    return sourceStart + length - 1;
  }
}

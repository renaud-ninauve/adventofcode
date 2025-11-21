package fr.ninauve.renaud.adventofcode.year2023.day05.part02;

import fr.ninauve.renaud.adventofcode.year2023.day05.part02.RangeMapper.MappedRange;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public record RangeMappers(List<RangeMapper> mappers) {

  public static RangeMappers rangeMappers(List<RangeMapper> mappers) {
    return new RangeMappers(mappers.stream()
        .sorted(Comparator.comparing(RangeMapper::sourceStart))
        .toList());
  }

  public List<Range> map(Range source) {
    List<MappedRange> mappedRanges = mappers.stream().map(m -> m.map(source)).toList();
    List<Range> toBeRemoved = mappedRanges.stream().map(MappedRange::source).toList();
    List<Range> notMapped = source.removeAll(toBeRemoved);
    return Stream.concat(
            mappedRanges.stream().map(MappedRange::destination),
            notMapped.stream()
        ).filter(r -> !r.isEmpty())
        .sorted(Comparator.comparing(Range::start))
        .toList();
  }
}
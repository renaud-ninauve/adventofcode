package fr.ninauve.renaud.adventofcode.year2025.day05;

import java.util.List;

public record Inventory(List<Long> ingredientIDs, List<Range> freshRanges) {

  public List<Long> freshIngredientIDs() {
    return ingredientIDs.stream()
        .filter(id -> freshRanges.stream()
            .anyMatch(r -> r.isIncluded(id)))
        .toList();
  }
}

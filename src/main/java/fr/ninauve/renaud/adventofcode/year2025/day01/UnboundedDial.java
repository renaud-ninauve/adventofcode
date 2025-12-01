package fr.ninauve.renaud.adventofcode.year2025.day01;

public record UnboundedDial(int value) {

  public static NormalizedDial normalize(UnboundedDial unbounded) {
    int unboundedValue = unbounded.value();
    if (unboundedValue >= 0) {
      return new NormalizedDial(unboundedValue % 100);
    }
    int modulo = (-unboundedValue) % 100;
    return modulo == 0 ? new NormalizedDial(0) : new NormalizedDial(100 - modulo);
  }
}

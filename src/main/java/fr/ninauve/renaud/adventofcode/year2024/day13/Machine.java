package fr.ninauve.renaud.adventofcode.year2024.day13;

import java.math.BigInteger;
import java.util.Map;

public interface Machine {
    Map<ButtonId, BigInteger> clicksFor(Location prizeLocation);

    default BigInteger price(Map<ButtonId, BigInteger> clicks) {
        BigInteger priceA = clicks.getOrDefault(ButtonId.A, BigInteger.ZERO).multiply(BigInteger.valueOf(3));
        BigInteger priceB = clicks.getOrDefault(ButtonId.B, BigInteger.ZERO);
        return priceA.add(priceB);
    }
}
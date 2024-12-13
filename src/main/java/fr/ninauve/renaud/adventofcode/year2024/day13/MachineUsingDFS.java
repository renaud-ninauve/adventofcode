package fr.ninauve.renaud.adventofcode.year2024.day13;

import lombok.Data;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
public class MachineUsingDFS implements Machine {
    private final Collection<Button> buttons;

    @Override
    public Map<ButtonId, BigInteger> clicksFor(Location prizeLocation) {
        Location start = new Location(0, 0);
        Map<Location, Map<ButtonId, BigInteger>> result = new HashMap<>();
        walk(result, prizeLocation, start, Map.of());
        return result.getOrDefault(prizeLocation, Map.of());
    }

    private void walk(Map<Location, Map<ButtonId, BigInteger>> result, Location target, Location current, Map<ButtonId, BigInteger> clicks) {
        Map<ButtonId, BigInteger> currentResult = result.get(current);
        if (currentResult == null || price(clicks).compareTo(price(currentResult)) < 0) {
            result.put(current, clicks);
        } else {
            return;
        }
        if (current.equals(target)) {
            return;
        }
        if (current.greaterThan(target)) {
            return;
        }
        for (Button button : buttons) {
            final Map<ButtonId, BigInteger> newClicks = new HashMap<>(clicks);
            BigInteger buttonClicks = clicks.getOrDefault(button.id(), BigInteger.ZERO);
            newClicks.put(button.id(), buttonClicks.add(BigInteger.ONE));
            walk(result, target, current.add(button.delta()), newClicks);
        }
    }
}

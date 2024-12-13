package fr.ninauve.renaud.adventofcode.year2024.day13;

import lombok.Data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
public class Machine {
    private final Collection<Button> buttons;

    public Map<ButtonId, Long> clicksFor(Location prizeLocation) {
        Location start = new Location(0, 0);
        Map<Location, Map<ButtonId, Long>> result = new HashMap<>();
        walk(result, prizeLocation, start, Map.of());
        return result.getOrDefault(prizeLocation, Map.of());
    }

    private void walk(Map<Location, Map<ButtonId, Long>> result, Location target, Location current, Map<ButtonId, Long> clicks) {
        Map<ButtonId, Long> currentResult = result.get(current);
        if (currentResult == null || price(clicks) < price(currentResult)) {
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
        for(Button button: buttons) {
            final Map<ButtonId, Long> newClicks = new HashMap<>(clicks);
            Long buttonClicks = clicks.getOrDefault(button.id(), 0L);
            newClicks.put(button.id(), buttonClicks + 1);
            walk(result, target, current.add(button.delta()), newClicks);
        }
    }

    public long price(Map<ButtonId, Long> clicks) {
        long priceA = clicks.getOrDefault(ButtonId.A, 0L) * 3L;
        long priceB = clicks.getOrDefault(ButtonId.B, 0L) * 1L;
        return priceA + priceB;
    }
}

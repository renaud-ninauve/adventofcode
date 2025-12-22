package fr.ninauve.renaud.adventofcode.year2025.day10;

import static fr.ninauve.renaud.adventofcode.year2025.day10.Light.LightStatus.ON;

public record Light(int index, LightStatus status) {
    public enum LightStatus {ON, OFF}

    public static Light toggle(Light light) {
        if (light.status == LightStatus.OFF) {
            return new Light(light.index(), ON);
        }
        return new Light(light.index(), LightStatus.OFF);
    }
}

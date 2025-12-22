package fr.ninauve.renaud.adventofcode.year2025.day10;

import java.util.List;

import static fr.ninauve.renaud.adventofcode.year2025.day10.Light.toggle;

public record Button(int index, List<Integer> lightIndexes) {

    public static MachineStatus pushButton(Button button, MachineStatus machineStatus) {
        List<Light> newMachineStatus = machineStatus.lights()
                .stream()
                .map(light ->
                        pushButtonForLight(button, light)
                ).toList();
        return new MachineStatus(newMachineStatus);
    }

    private static Light pushButtonForLight(Button button, Light light) {
        return button.lightIndexes.contains(light.index())
                ? toggle(light)
                : light;
    }
}

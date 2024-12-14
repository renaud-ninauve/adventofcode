package fr.ninauve.renaud.adventofcode.year2024.day14;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RobotTest {
    private static final Area AREA = new Area(101L, 103L);
    @Test
    void fromInput() {
        Robot actual = Robot.fromInput("p=0,4 v=3,-3", AREA);
        assertThat(actual).isEqualTo(new Robot(new Location(0L, 4L), new Location(3L, -3L), AREA));
    }

    @Test
    void advanceInTime() {
        Robot robot = Robot.fromInput("p=54,2 v=-1,3", AREA);
        Robot actual = robot.advanceInTime(10);
        assertThat(actual).isEqualTo(Robot.fromInput("p=44,32 v=-1,3", AREA));
    }

    @Test
    void advanceInTime_teleport() {
        Robot robot = Robot.fromInput("p=0,0 v=1,0", AREA);
        Robot actual = robot.advanceInTime(101);
        assertThat(actual).isEqualTo(Robot.fromInput("p=0,0 v=1,0", AREA));
    }

    @Test
    void advanceInTime_teleportZero() {
        Robot robot = Robot.fromInput("p=0,0 v=-1,0", AREA);
        Robot actual = robot.advanceInTime(1);
        assertThat(actual).isEqualTo(Robot.fromInput("p=100,0 v=-1,0", AREA));
    }
}
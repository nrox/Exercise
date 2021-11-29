import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RobotTest {

    @Test
    void test1() throws Exception {
        Robot robot = new Robot("2 3 E", 10, 10);
        Position position = robot.runMoves("RMRMRLMRMRMRMR");
        assertEquals("1 2 W", position.toString());
    }

    @Test
    void test2() throws Exception {
        Robot robot = new Robot("5 4 W", 10, 10);
        Position position = robot.runMoves("LMRMLMRMRLMRRM");
        assertEquals("3 2 E", position.toString());
    }

    @Test
    void outOfBoundaries() {
        assertThrows(RobotSquadException.class, () -> new Robot("0 5 W", 10, 10).runMoves("M"));
        assertThrows(RobotSquadException.class, () -> new Robot("5 0 S", 10, 10).runMoves("M"));
        assertThrows(RobotSquadException.class, () -> new Robot("9 0 E", 10, 10).runMoves("M"));
        assertThrows(RobotSquadException.class, () -> new Robot("0 9 N", 10, 10).runMoves("M"));
    }

}
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RobotSquadTest {

    @Test
    void test() throws Exception {
        String textInput = "" +
                "10 10\n" +
                "2 3 E\n" +
                "RMRMRLMRMRMRMR\n" +
                "5 4 W\n" +
                "LMRMLMRMRLMRRM";
        List<String> results = List.of("1 2 W", "3 2 E");

        RobotSquad robotSquad = new RobotSquad(textInput);
        List<String> positions = robotSquad.getRobots().stream()
                .map(Robot::getPosition)
                .map(Objects::toString)
                .collect(Collectors.toList());
        assertEquals(results, positions);
    }

    @Test
    void testNoRobots() throws Exception {
        RobotSquad squad = new RobotSquad("11 10");
        assertEquals(11, squad.getMaxX());
        assertEquals(10, squad.getMaxY());
        assertEquals(List.of(), squad.getRobots());
    }

    @Test
    void testExceptions() {
        assertThrows(RobotSquadException.class, () -> new RobotSquad(null));
        assertThrows(RobotSquadException.class, () -> new RobotSquad(""));
        assertThrows(RobotSquadException.class, () -> new RobotSquad("10"));
        assertThrows(RobotSquadException.class, () -> new RobotSquad("x 10"));
    }

}
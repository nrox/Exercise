import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoveTest {

    @Test
    void isValid() throws Exception {
        assertEquals(Move.LEFT, Move.fromInput('L'));
        assertEquals(Move.RIGHT, Move.fromInput('R'));
        assertEquals(Move.FORWARD, Move.fromInput('M'));
        assertThrows(RobotSquadException.class, () -> Move.fromInput(' '));
        assertThrows(RobotSquadException.class, () -> Move.fromInput('X'));
    }
}
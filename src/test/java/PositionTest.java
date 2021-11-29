import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PositionTest {
    @Test
    void constructor() throws Exception {
        Position position = new Position("10 12 N");
        assertEquals(Orientation.NORTH, position.getOrientation());
        assertEquals(position.getX(), 10);
        assertEquals(position.getY(), 12);
    }

    @Test
    void testWrongArguments() {
        assertThrows(RobotSquadException.class, () -> new Position(null));
        assertThrows(RobotSquadException.class, () -> new Position(""));
        assertThrows(RobotSquadException.class, () -> new Position("10 25 W 0"));
        assertThrows(RobotSquadException.class, () -> new Position("x 12 W"));
        assertThrows(RobotSquadException.class, () -> new Position("5 y W"));
        assertThrows(RobotSquadException.class, () -> new Position("10 12 K"));
    }

    @Test
    void incrementX() {
        assertEquals(
                new Position(11, 15, Orientation.NORTH),
                new Position(10, 15, Orientation.NORTH).incrementX());
    }

    @Test
    void decrementX() {
        assertEquals(
                new Position(9, 15, Orientation.NORTH),
                new Position(10, 15, Orientation.NORTH).decrementX());
    }

    @Test
    void incrementY() {
        assertEquals(
                new Position(10, 16, Orientation.NORTH),
                new Position(10, 15, Orientation.NORTH).incrementY());
    }

    @Test
    void decrementY() {
        assertEquals(
                new Position(10, 14, Orientation.NORTH),
                new Position(10, 15, Orientation.NORTH).decrementY());
    }

    @Test
    void rotateRight() {
        assertEquals(
                new Position(10, 15, Orientation.EAST),
                new Position(10, 15, Orientation.NORTH).rotateRight());
        assertEquals(
                new Position(10, 15, Orientation.SOUTH),
                new Position(10, 15, Orientation.EAST).rotateRight());
        assertEquals(
                new Position(10, 15, Orientation.WEST),
                new Position(10, 15, Orientation.SOUTH).rotateRight());
        assertEquals(
                new Position(10, 15, Orientation.NORTH),
                new Position(10, 15, Orientation.WEST).rotateRight());
    }

    @Test
    void rotateLeft() {
        assertEquals(
                new Position(10, 15, Orientation.WEST),
                new Position(10, 15, Orientation.NORTH).rotateLeft());
        assertEquals(
                new Position(10, 15, Orientation.SOUTH),
                new Position(10, 15, Orientation.WEST).rotateLeft());
        assertEquals(
                new Position(10, 15, Orientation.EAST),
                new Position(10, 15, Orientation.SOUTH).rotateLeft());
        assertEquals(
                new Position(10, 15, Orientation.NORTH),
                new Position(10, 15, Orientation.EAST).rotateLeft());
    }

    @Test
    void move() throws Exception {
        assertEquals(
                new Position(10, 15, Orientation.WEST),
                new Position(10, 15, Orientation.NORTH).move('L'));
        assertEquals(
                new Position(10, 15, Orientation.EAST),
                new Position(10, 15, Orientation.NORTH).move('R'));
        assertEquals(
                new Position(10, 16, Orientation.NORTH),
                new Position(10, 15, Orientation.NORTH).move('M'));
        assertEquals(
                new Position(10, 14, Orientation.SOUTH),
                new Position(10, 15, Orientation.SOUTH).move('M'));
        assertEquals(
                new Position(11, 15, Orientation.EAST),
                new Position(10, 15, Orientation.EAST).move('M'));
        assertEquals(
                new Position(9, 15, Orientation.WEST),
                new Position(10, 15, Orientation.WEST).move('M'));
    }

    @Test
    void moveException() {
        assertThrows(RobotSquadException.class, () -> new Position("10 12 N").move('A'));
    }
}
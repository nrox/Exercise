import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrientationTest {

    @Test
    void rotateLeft() {
        assertEquals(Orientation.WEST, Orientation.NORTH.rotateLeft());
        assertEquals(Orientation.SOUTH, Orientation.WEST.rotateLeft());
        assertEquals(Orientation.EAST, Orientation.SOUTH.rotateLeft());
        assertEquals(Orientation.NORTH, Orientation.EAST.rotateLeft());
    }

    @Test
    void rotateRight() {
        assertEquals(Orientation.EAST, Orientation.NORTH.rotateRight());
        assertEquals(Orientation.SOUTH, Orientation.EAST.rotateRight());
        assertEquals(Orientation.WEST, Orientation.SOUTH.rotateRight());
        assertEquals(Orientation.NORTH, Orientation.WEST.rotateRight());
    }
}
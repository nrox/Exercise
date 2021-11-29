
import java.util.Arrays;

/**
 * Orientations have this exact order N, E, S, W
 * to be able to apply rotations by increasing or decreasing the ordinal.
 */
enum Orientation {

    NORTH('N'),
    EAST('E'),
    SOUTH('S'),
    WEST('W');

    private final char character;

    Orientation(char character) {
        this.character = character;
    }

    /**
     * Return a Move parsed from the input.
     *
     * @param input should be a single character is one of {M,L,R}
     */
    static Orientation fromInput(char input) throws RobotSquadException {
        return Arrays.stream(values())
                .filter(orientation -> orientation.character == input)
                .findFirst()
                .orElseThrow(() -> new RobotSquadException(String.format("Could not parse orientation from %s", input)));
    }

    static Orientation fromInput(String input) throws RobotSquadException {
        return Orientation.fromInput(input.charAt(0));
    }

    /**
     * Rotating left means going backwards in the ring N, E, S, W
     */
    Orientation rotateLeft() {
        return values()[(this.ordinal() - 1 + values().length) % values().length];
    }

    /**
     * Rotating left means going forward in the ring N, E, S, W
     */
    Orientation rotateRight() {
        return values()[(this.ordinal() + 1) % values().length];
    }

    public char getCharacter() {
        return character;
    }
}

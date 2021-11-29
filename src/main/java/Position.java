import org.apache.commons.lang3.math.NumberUtils;

/**
 * Our robots position is represented by a combination of an x and y co-ordinates and a letter
 * representing one of the four cardinal compass points. The test field is divided up into a grid
 * to simplify navigation. An example position might be 1, 1, N, meaning our robot is in the bottom
 * left corner and facing North.
 */
public class Position {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    private int x;
    private int y;
    private Orientation orientation;

    Position(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    Position(String text) throws RobotSquadException {
        String[] coordinates = validateAndSplitCoordinates(text);
        x = Integer.parseInt(coordinates[0]);
        y = Integer.parseInt(coordinates[1]);
        orientation = Orientation.fromInput(coordinates[2]);
    }

    /**
     * Check if the text represents a valid position and return an array with
     * [x,y,orientation], where each entry is still a string.
     *
     * @throws RobotSquadException is x, y are not numbers and the orientation is not in {N,S,E,W}
     */
    private String[] validateAndSplitCoordinates(String text) throws RobotSquadException {
        if (text == null) {
            throw new RobotSquadException("Received null text for Robot position");
        }
        String[] coordinates = text.split(" ");
        if (coordinates.length != 3) {
            throw new RobotSquadException(String.format("Expected 3 tokens for position in %s", text));
        }
        if (!(NumberUtils.isParsable(coordinates[0]) && NumberUtils.isParsable(coordinates[1]))) {
            throw new RobotSquadException(String.format("Can't parse x,y from %s", text));
        }
        try {
            Orientation.fromInput(coordinates[2]);
        } catch (Exception e) {
            throw new RobotSquadException(String.format(
                    "Could not parse Orientation from %s: %s", text, e.getMessage()));
        }
        return coordinates;
    }

    boolean isBetweenBoundaries(int maxX, int maxY) {
        return (x >= 0) && (y >= 0) && (x < maxX) && (y < maxY);
    }

    Position incrementX() {
        return new Position(getX() + 1, getY(), getOrientation());
    }

    Position decrementX() {
        return new Position(getX() - 1, getY(), getOrientation());
    }

    Position incrementY() {
        return new Position(getX(), getY() + 1, getOrientation());
    }

    Position decrementY() {
        return new Position(getX(), getY() - 1, getOrientation());
    }

    Position rotateLeft() {
        return new Position(getX(), getY(), getOrientation().rotateLeft());
    }

    Position rotateRight() {
        return new Position(getX(), getY(), getOrientation().rotateRight());
    }

    /**
     * Rotate or move forward
     *
     * @param input a one of {'L','R', 'M'}
     * @return the new Position after move.
     */
    Position move(char input) throws RobotSquadException {
        switch (Move.fromInput(input)) {
            case LEFT:
                return this.rotateLeft();
            case RIGHT:
                return this.rotateRight();
            case FORWARD:
                switch (this.getOrientation()) {
                    case NORTH:
                        return incrementY();
                    case SOUTH:
                        return decrementY();
                    case EAST:
                        return incrementX();
                    case WEST:
                        return decrementX();
                    default:
                        throw new RobotSquadException(String.format(
                                "Unexpected Orientation: %s", this.getOrientation()));
                }
            default:
                throw new RobotSquadException(String.format("Unexpected Move from input: %s", input));
        }
    }

    @Override
    public String toString() {
        return String.format("%d %d %s", x, y, orientation.getCharacter());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Position)) {
            return false;
        }
        return this.toString().equals(other.toString());
    }

}

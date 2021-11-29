/**
 * Our robots position is represented by a combination of an x and y co-ordinates and a letter
 * representing one of the four cardinal compass points. The test field is divided up into a grid
 * to simplify navigation. An example position might be 1, 1, N, meaning our robot is in the bottom
 * left corner and facing North.
 * <p>
 * In order to control the ruby robot,we can send a simple string of letters. The only valid possible
 * letters are 'L', 'R' and 'M'. 'L' and 'R' makes our robot spin 90 degrees left or right respectively,
 * without moving from its current position.
 * 'M' means move forward one grid point, and maintain the same heading.
 * <p>
 * Assume that the square directly North from (x, y) is (x, y+1).
 * Input:
 * The first line of input is the upper-right coordinates of the test field, the lower-left
 * coordinates are assumed to be 0,0.
 * <p>
 * The rest of the input is information to the robots that have been deployed.
 * Each robot has two lines of input. The first line gives the robot's position, and
 * the second line is a series of instructions telling the robot how to explore the test field.
 * <p>
 * The position is made up of two integers and a letter separated by spaces, corresponding to the
 * x and y co-ordinates and the robot orientation.
 * <p>
 * Each robot run will be finished sequentially, which means that the second robot run can not
 * start to move until the first one has finished.
 */
public class Robot {

    private Position position;
    private int maxX;
    private int maxY;

    Robot(String initialPosition, int maxX, int maxY) throws RobotSquadException {
        this.position = new Position(initialPosition);
        this.maxX = maxX;
        this.maxY = maxY;
    }

    Position runMoves(String moves) throws RobotSquadException {
        for (char character : moves.toCharArray()) {
            Position newPosition = this.position.move(character);
            if (!newPosition.isBetweenBoundaries(maxX, maxY)) {
                throw new RobotSquadException(String.format(
                        "After move %s, the new position (%s) is outside boundaries", character, newPosition));
            }
            this.position = newPosition;
        }
        return this.position;
    }

    public Position getPosition() {
        return position;
    }
}

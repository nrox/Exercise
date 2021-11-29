
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

public class RobotSquad {

    private final int maxX;
    private final int maxY;
    private List<Robot> robots = new ArrayList<>();

    RobotSquad(String text) throws RobotSquadException {
        if (text == null || text.length() == 0) {
            throw new RobotSquadException("Argument to RobotSquad can't be a null or empty string.");
        }
        String[] lines = text.split("\n");
        String[] dimensions = lines[0].split(" ");
        if (dimensions.length != 2 ||
                !NumberUtils.isParsable(dimensions[0]) ||
                !NumberUtils.isParsable(dimensions[1])) {
            throw new RobotSquadException(String.format("Could not parse dimensions from %s", lines[0]));
        }

        maxX = Integer.parseInt(dimensions[0]);
        maxY = Integer.parseInt(dimensions[1]);

        // Loop over the lines and step in intervals of 2 because
        // the current line is the position and the next the moves
        for (int i = 1; i < lines.length; i += 2) {
            Robot robot = new Robot(lines[i], maxX, maxY);
            robot.runMoves(lines[i + 1]);
            robots.add(robot);
        }
    }


    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public List<Robot> getRobots() {
        return robots;
    }
}

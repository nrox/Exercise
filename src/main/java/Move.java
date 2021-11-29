
import java.util.Arrays;

enum Move {
    FORWARD('M'),
    LEFT('L'),
    RIGHT('R');

    private final char character;

    Move(char character) {
        this.character = character;
    }

    /**
     * Return a Move parsed from the input character.
     *
     * @param input should be one of {M,L,R}
     */
    static Move fromInput(char input) throws RobotSquadException {
        return Arrays.stream(values())
                .filter(move -> move.character == input)
                .findFirst()
                .orElseThrow(() -> new RobotSquadException(String.format("Could not parse move from %s", input)));
    }


}

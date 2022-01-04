package sebfisch.aoc21.day02;

public class Position {
    private int depth;
    private int horizontal;

    public Position() {
        this.depth = 0;
        this.horizontal = 0;
    }

    public int product() {
        return depth * horizontal;
    }

    public void moveBy(Command cmd) {
        switch (cmd) {
            case Command.Up up:
                this.depth -= up.steps();
                break;
            case Command.Down down:
                this.depth += down.steps();
                break;
            case Command.Forward forward:
                this.horizontal += forward.steps();
                break;
        }
    }
}

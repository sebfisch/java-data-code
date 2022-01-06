package sebfisch.aoc21.day02;

public class Position {
    protected int depth;
    protected int horizontal;

    public Position() {
        this.depth = 0;
        this.horizontal = 0;
    }

    public int product() {
        return depth * horizontal;
    }

    public void moveBy(Command cmd) {
        if (cmd instanceof Command.Up) {
            depth -= cmd.steps();
        }

        if (cmd instanceof Command.Down) {
            depth += cmd.steps();
        }

        horizontal += cmd.steps();
    }
}

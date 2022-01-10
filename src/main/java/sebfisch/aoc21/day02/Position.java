package sebfisch.aoc21.day02;

public class Position {
    protected int depth;
    protected int horizontal;

    public Position(int depth, int horizontal) {
        this.depth = depth;
        this.horizontal = horizontal;
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

        if (cmd instanceof Command.Forward) {
            horizontal += cmd.steps();
        }
    }

    @Override
    public String toString() {
        return "Position [depth=" + depth + ", horizontal=" + horizontal + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + depth;
        result = prime * result + horizontal;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (depth != other.depth)
            return false;
        if (horizontal != other.horizontal)
            return false;
        return true;
    }
}

package sebfisch.aoc21.day02;

public class AimingPosition extends Position {
    protected int aim;

    public AimingPosition(int depth, int horizontal, int aim) {
        super(depth, horizontal);
        this.aim = aim;
    }

    public void moveBy(Command cmd) {
        switch (cmd) {
            case Command.Up up -> {
                aim -= up.steps();
            }
            case Command.Down down -> {
                aim += down.steps();
            }
            case Command.Forward fwd -> {
                horizontal += fwd.steps();
                depth += aim * fwd.steps();
            }
        }
    }

    @Override
    public String toString() {
        return "AimingPosition [aim=" + aim + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + aim;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AimingPosition other = (AimingPosition) obj;
        if (aim != other.aim)
            return false;
        return true;
    }
}

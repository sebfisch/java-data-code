package sebfisch.aoc21.day02;

public class AimingPosition extends Position {
    protected int aim;

    public AimingPosition() {
        super();
        this.aim = 0;
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
}

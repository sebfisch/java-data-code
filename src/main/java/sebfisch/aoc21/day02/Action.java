package sebfisch.aoc21.day02;

import java.util.function.UnaryOperator;

public record Action(int depth, int horizontal, int aim)
    implements UnaryOperator<AimingPosition> {

  public static Action from(Command cmd) {
    return switch (cmd) {
      case Command.Up up -> new Action(0, 0, -up.steps());
      case Command.Down down -> new Action(0, 0, down.steps());
      case Command.Forward fwd -> new Action(0, fwd.steps(), 0);
    };
  }

  public static Action identity() {
    return new Action(0, 0, 0);
  }

  public Action compose(Action that) {
    // derived from matrix multiplications
    return new Action(
        this.depth + that.depth + this.aim * that.horizontal,
        this.horizontal + that.horizontal,
        this.aim + that.aim);
  }

  public AimingPosition applyToInitialPosition() {
    return new AimingPosition(depth, horizontal, aim);
  }

  @Override
  public AimingPosition apply(AimingPosition pos) {
    return new Action(pos.depth, pos.horizontal, pos.aim)
        .compose(this).applyToInitialPosition();
  }
}
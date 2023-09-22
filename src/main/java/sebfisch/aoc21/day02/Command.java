package sebfisch.aoc21.day02;

import java.util.stream.Stream;

public sealed interface Command {
  int steps();

  record Up(int steps) implements Command {
  }

  record Down(int steps) implements Command {
  }

  record Forward(int steps) implements Command {
  }

  static Command parse(final String line) {
    String[] parts = line.split(" ");

    if (parts.length < 2) {
      throw new IllegalArgumentException("invalid command");
    }

    final int steps = Integer.parseInt(parts[1]);

    // switch (parts[0]) {
    // case "up": return new Up(steps);
    // case "down": return new Down(steps);
    // case "forward": return new Forward(steps);
    // default: throw new IllegalArgumentException("invalid command");
    // }

    return switch (parts[0]) {
      case "up" -> new Up(steps);
      case "down" -> new Down(steps);
      case "forward" -> new Forward(steps);
      default -> throw new IllegalArgumentException("invalid command");
    };
  }

  static Stream<Command> exampleInput() {
    return """
        forward 5
        down 5
        forward 8
        up 3
        down 8
        forward 2
        """.lines().map(Command::parse);
  }
}

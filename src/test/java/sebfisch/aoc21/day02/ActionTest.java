package sebfisch.aoc21.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ActionTest {

    @ParameterizedTest
    @MethodSource("smallActions")
    void testLeftIdentity(Action action) {
        assertEquals(action, Action.identity().compose(action));
    }

    @ParameterizedTest
    @MethodSource("smallActions")
    void testRightIdentity(Action action) {
        assertEquals(action, action.compose(Action.identity()));
    }

    static IntStream smallNats() {
        return IntStream.range(0, 9);
    }

    static IntStream smallInts() {
        return IntStream.concat(smallNats(), IntStream.range(-9, -1));
    }

    static Stream<Command> smallCommands() {
        return Stream.concat(
                smallNats().mapToObj(Command.Up::new),
                Stream.concat(smallNats().mapToObj(Command.Down::new),
                        smallNats().mapToObj(Command.Forward::new)));
    }

    static Stream<AimingPosition> smallAimingPositions() {
        return smallNats().boxed().flatMap(depth -> //
                smallNats().boxed().flatMap(horizontal -> //
                smallInts().boxed().map(aim -> //
                new AimingPosition(depth, horizontal, aim))));
    }

    static Stream<Action> smallActions() {
        return smallInts().boxed().flatMap(depth -> //
                smallNats().boxed().flatMap(horizontal -> //
                smallInts().boxed().map(aim -> //
                new Action(depth, horizontal, aim))));
    }
}

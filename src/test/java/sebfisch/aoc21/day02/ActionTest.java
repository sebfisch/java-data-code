package sebfisch.aoc21.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.SplittableRandom;
import java.util.random.RandomGenerator.SplittableGenerator;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ActionTest {

    @ParameterizedTest
    @MethodSource("randomActions")
    void testLeftIdentity(Action action) {
        assertEquals(action, Action.identity().compose(action));
    }

    @ParameterizedTest
    @MethodSource("randomActions")
    void testRightIdentity(Action action) {
        assertEquals(action, action.compose(Action.identity()));
    }

    @ParameterizedTest
    @MethodSource("randomActionArguments")
    void testAssociativity(Action one, Action two, Action three) {
        assertEquals( //
                one.compose(two).compose(three), //
                one.compose(two.compose(three)));
    }

    @ParameterizedTest
    @MethodSource("randomPosAndCmdArgs")
    void testCommandActionCorrespondence(AimingPosition pos, Command cmd) {
        AimingPosition moved = Action.from(cmd).apply(pos);
        pos.moveBy(cmd);
        assertEquals(pos, moved);
    }

    static final int TEST_COUNT = 100;
    static final int BOUND = 10;

    static Stream<SplittableGenerator> generators() {
        return new SplittableRandom().splits(TEST_COUNT);
    }

    static Stream<Action> randomActions() {
        return generators().map(ActionTest::randomAction);
    }

    static Action randomAction(SplittableGenerator rnd) {
        return new Action( //
                rnd.nextInt(-BOUND, BOUND), // depth change
                rnd.nextInt(0, BOUND), // horizontal change
                rnd.nextInt(-BOUND, BOUND)); // aim change
    }

    static Stream<Arguments> randomActionArguments() {
        return generators().map(ActionTest::threeRandomActions);
    }

    static Arguments threeRandomActions(SplittableGenerator rnd) {
        return Arguments.of(rnd.splits(3).map(ActionTest::randomAction).toArray());
    }

    static Stream<Arguments> randomPosAndCmdArgs() {
        return generators().map(ActionTest::randomPosAndCmd);
    }

    static Arguments randomPosAndCmd(SplittableGenerator rnd) {
        var posGen = rnd.split();
        var cmdGen = posGen.split();
        return Arguments.of(randomPosition(posGen), randomCommand(cmdGen));
    }

    static AimingPosition randomPosition(SplittableGenerator rnd) {
        return new AimingPosition( //
                rnd.nextInt(0, BOUND), // depth
                rnd.nextInt(0, BOUND), // horizontal
                rnd.nextInt(-BOUND, BOUND)); // aim
    }

    static Command randomCommand(SplittableGenerator rnd) {
        return switch (rnd.nextInt(3)) {
            case 0 -> new Command.Up(rnd.nextInt(BOUND));
            case 1 -> new Command.Down(rnd.nextInt(BOUND));
            case 2 -> new Command.Forward(rnd.nextInt(BOUND));
            default -> throw new IllegalStateException("unreachable");
        };
    }
}

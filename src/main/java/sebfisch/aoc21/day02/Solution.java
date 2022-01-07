package sebfisch.aoc21.day02;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class Solution {
    private Solution() {
        // static methods only
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        // part one
        processExampleInput(new Position(0, 0));
        processLargeInput(new Position(0, 0));

        // part two
        processExampleInput(new AimingPosition(0, 0, 0));
        processLargeInput(new AimingPosition(0, 0, 0));

        // part two with large input in parallel
        processLargeInputInParallel();
    }

    private static void processExampleInput(final Position pos) {
        Command.exampleInput().forEachOrdered(pos::moveBy);
        System.out.println(pos.product());
    }

    private static void processLargeInput(final Position pos)
            throws URISyntaxException, IOException {

        try (Stream<String> lines = Files.lines(largeInputPath())) {
            lines.map(Command::parse).forEachOrdered(pos::moveBy);
            System.out.println(pos.product());
        }
    }

    private static void processLargeInputInParallel()
            throws URISyntaxException, IOException {

        try (Stream<String> lines = Files.lines(largeInputPath())) {
            final AimingPosition finalPos = lines
                    .parallel()
                    .map(Command::parse)
                    .map(Action::from)
                    .reduce(Action.identity(), Action::compose)
                    .applyToInitialPosition();

            System.out.println(finalPos.product());
        }
    }

    private static Path largeInputPath() throws URISyntaxException {
        return Paths.get(Solution.class.getResource("/commands.txt").toURI());
    }
}

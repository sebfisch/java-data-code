package sebfisch.aoc21.day02;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class Solution {
    private Solution() {
        // static methods only
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        // part one
        processExampleInput(new Position());
        processLargeInput(new Position());

        // part two
        processExampleInput(new AimingPosition());
        processLargeInput(new AimingPosition());
    }

    private static void processExampleInput(final Position pos) {
        Command.exampleInput().forEach(pos::moveBy);
        System.out.println(pos.product());
    }

    private static void processLargeInput(final Position pos) 
            throws URISyntaxException, IOException {
        URL resource = Solution.class.getResource("/commands.txt");
        try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()))) {
            lines.map(Command::parse).forEach(pos::moveBy);
        }
        System.out.println(pos.product());
    }
}

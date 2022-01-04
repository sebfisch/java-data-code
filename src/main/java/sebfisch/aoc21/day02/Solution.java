package sebfisch.aoc21.day02;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Solution {
    private Solution() {
        // static methods only
    }

    public static void processExampleInput() {
        final Position pos = new Position();
        Command.exampleInput().forEach(pos::moveBy);
        System.out.println(pos.product());
    }

    public static void processLargeInput() throws URISyntaxException, IOException {
        final Position pos = new Position();
        URL resource = Solution.class.getResource("/commands.txt");
        try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()))) {
            lines.map(Command::parse).forEach(pos::moveBy);
        }
        System.out.println(pos.product());
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        processExampleInput();
        processLargeInput();
    }
}

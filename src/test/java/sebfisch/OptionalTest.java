package sebfisch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OptionalTest {
    @Test
    void testPresent() {
        assertEquals("x", new Optional.Present<>("x").value());
    }

    @Test
    void testEquals() {
        assertEquals(new Optional.Empty<>(), new Optional.Empty<>());
        assertEquals(new Optional.Present<>("y"), new Optional.Present<>("y"));
    }

    // TODO: Task 1.1 - add test for null check
}

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

    @Test
    void testMap() {
        final Optional<String> hi = new Optional.Present<>("hello");
        final Optional<Integer> actual = hi.map(String::length);
        final Optional<Integer> expected = new Optional.Present<>(5);
        assertEquals(expected, actual);
    }

    @Test
    void testMatchingFilter() {
        final Optional<String> hi = new Optional.Present<>("hello");
        final Optional<String> actual = hi.filter(s -> s.length() > 2);
        assertEquals(hi, actual);
    }

    @Test
    void testRejectingFilter() {
        final Optional<String> hi = new Optional.Present<>("hello");
        final Optional<String> actual = hi.filter(s -> s.length() < 2);
        assertEquals(new Optional.Empty<>(), actual);
    }
}

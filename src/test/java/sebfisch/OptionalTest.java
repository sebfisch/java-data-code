package sebfisch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OptionalTest {
    @Test
    void testPresent() {
        assertEquals("x", Optional.of("x").value());
    }

    @Test
    void testEquals() {
        assertEquals(Optional.empty(), Optional.empty());
        assertEquals(Optional.of("y"), Optional.of("y"));
    }

    // TODO: Task 1.1 - add test for null check in present constructor

    @Test
    void testMap() {
        final Optional<String> hi = Optional.of("hello");
        final Optional<Integer> actual = hi.map(String::length);
        final Optional<Integer> expected = Optional.of(5);
        assertEquals(expected, actual);
    }

    @Test
    void testMatchingFilter() {
        final Optional<String> hi = Optional.of("hello");
        final Optional<String> actual = hi.filter(s -> s.length() > 2);
        assertEquals(hi, actual);
    }

    @Test
    void testRejectingFilter() {
        final Optional<String> hi = Optional.of("hello");
        final Optional<String> actual = hi.filter(s -> s.length() < 2);
        assertEquals(Optional.empty(), actual);
    }
}

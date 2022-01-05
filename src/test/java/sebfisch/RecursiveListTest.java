package sebfisch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RecursiveListTest {
    @Test
    void testHead() {
        RecursiveList.Populated<Integer> list = //
                (RecursiveList.Populated<Integer>) RecursiveList.of(42, 43);
        assertEquals(42, list.head().intValue());
    }

    @Test
    void testTail() {
        RecursiveList.Populated<Integer> list = //
                (RecursiveList.Populated<Integer>) RecursiveList.of(42, 43);
        assertEquals(RecursiveList.of(43), list.tail());
    }

    @Test
    void testLength() {
        assertEquals(3, RecursiveList.of(1,2,3).length());
    }

    @Test
    void testMap() {
        RecursiveList<String> strings = RecursiveList.of("hi","there");
        RecursiveList<Integer> actual = strings.map(String::length);
        assertEquals(RecursiveList.of(2,5), actual);
    }

    @Test void testFilter() {
        RecursiveList<String> strings = RecursiveList.of("hi","there");
        RecursiveList<String> actual = strings.filter(s -> s.length() < 3);
        assertEquals(RecursiveList.of("hi"), actual);
    }
}

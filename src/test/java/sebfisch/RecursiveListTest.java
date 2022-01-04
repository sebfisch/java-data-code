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
}

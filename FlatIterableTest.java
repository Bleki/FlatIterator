package hu.bleki.util;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlatIterableTest {

    @Test
    public void emptyU() {
        AtomicInteger size = new AtomicInteger();
        new FlatIterable<>(List.of(), ignore -> List.of()).forEach(ignore -> size.incrementAndGet());
        assertEquals(0, size.get());
    }

    @Test
    public void emptyV() {
        AtomicInteger size = new AtomicInteger();
        new FlatIterable<>(List.of(1, 2, 3), ignore -> List.of()).forEach(ignore -> size.incrementAndGet());
        assertEquals(0, size.get());
    }

    @Test
    public void values() {
        AtomicInteger size = new AtomicInteger();
        new FlatIterable<>(List.of(List.of(1, 2), List.of(), List.of(3)), list -> list).forEach(ignore -> size.incrementAndGet());
        assertEquals(3, size.get());
    }
}

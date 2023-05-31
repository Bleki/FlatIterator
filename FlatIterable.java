package hu.bleki.util;

import java.util.Iterator;
import java.util.function.Function;

public class FlatIterable<U, V> implements Iterable<V> {
    private final Iterable<U> iterable;
    private final Function<U, Iterable<V>> flatter;

    public FlatIterable(Iterable<U> iterable, Function<U, Iterable<V>> flatter) {
        this.iterable = iterable;
        this.flatter = flatter;
    }

    @Override
    public Iterator<V> iterator() {
        return new FlatIterator<>(iterable.iterator(), u -> flatter.apply(u).iterator());
    }
}

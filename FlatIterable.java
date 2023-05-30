package hu.bleki.math.util;

import java.util.Iterator;
import java.util.function.Function;

public class FlatIterable<U, V> implements Iterable<V> {
    private final Iterable<U> iterable;
    private final Function<U, Iterable<V>> iterableFunction;

    public FlatIterable(Iterable<U> iterable, Function<U, Iterable<V>> iterableFunction) {
        this.iterable = iterable;
        this.iterableFunction = iterableFunction;
    }

    @Override
    public Iterator<V> iterator() {
        return new FlatIterator<>(iterable.iterator(), u -> iterableFunction.apply(u).iterator());
    }
}

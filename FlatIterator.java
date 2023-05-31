package hu.bleki.math.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.function.Function;

public class FlatIterator<U, V> implements Iterator<V> {
    private final Iterator<U> iterator;
    private final Function<U, Iterator<V>> flatter;
    private Iterator<V> flatIterator = Collections.emptyIterator();

    public FlatIterator(Iterator<U> iterator, Function<U, Iterator<V>> flatter) {
        this.iterator = iterator;
        this.flatter = flatter;

        goNext();
    }

    @Override
    public boolean hasNext() {
        return flatIterator.hasNext();
    }

    @Override
    public V next() {
        V flatNext = flatIterator.next();
        goNext();
        return flatNext;
    }

    private void goNext() {
        if (!flatIterator.hasNext()) {
            while (iterator.hasNext()) {
                U next = iterator.next();
                flatIterator = flatter.apply(next);
                if (flatIterator.hasNext()) {
                    return;
                }
            }
        }
    }
}

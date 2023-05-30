package hu.bleki.math.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.function.Function;

public class FlatIterator<U, V> implements Iterator<V> {
    private final Iterator<U> uIterator;
    private final Function<U, Iterator<V>> iteratorFunction;
    private Iterator<V> vIterator = Collections.emptyIterator();

    public FlatIterator(Iterator<U> iterator, Function<U, Iterator<V>> iteratorFunction) {
        this.uIterator = iterator;
        this.iteratorFunction = iteratorFunction;

        goNext();
    }

    @Override
    public boolean hasNext() {
        return vIterator.hasNext();
    }

    @Override
    public V next() {
        V v = vIterator.next();
        goNext();
        return v;
    }

    private void goNext() {
        if (!vIterator.hasNext()) {
            while (uIterator.hasNext()) {
                U u = uIterator.next();
                vIterator = iteratorFunction.apply(u);
                if (vIterator.hasNext()) {
                    return;
                }
            }
        }
    }
}

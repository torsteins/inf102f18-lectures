package week39;

import week37.ISymTable;

/**
 * @author Torstein Strømme
 */
public class LinearProbingHashTable<Key extends Comparable<Key>, Value> implements ISymTable<Key, Value> {

    @Override
    public void put(Key key, Value value) {

    }

    @Override
    public Value get(Key key) {
        return null;
    }

    @Override
    public boolean containsKey(Key key) {
        return false;
    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }
}

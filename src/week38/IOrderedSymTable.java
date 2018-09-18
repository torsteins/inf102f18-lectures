package week38;

import week37.ISymTable;

public interface IOrderedSymTable<Key extends Comparable<Key>, Value> extends ISymTable<Key, Value> {
    Key minKey();

    Key maxKey();

    int rank(Key key);

    Key select(int rank);

    Key floor(Key key);

    Key ceil(Key key);
}

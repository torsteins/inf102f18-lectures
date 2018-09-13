package week37;

/**
 * @author Torstein Strømme
 */
public interface ISymTable<Key extends Comparable<Key>, Value> {

    void put(Key key, Value value);

    Value get(Key key);

    boolean containsKey(Key key);

    void delete(Key key);

    int size();

    Iterable<Key> keys();

}

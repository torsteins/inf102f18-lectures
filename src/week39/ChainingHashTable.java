package week39;

import week37.ISymTable;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * @author Torstein Strømme
 */
public class ChainingHashTable<Key extends Comparable<Key>, Value> implements ISymTable<Key, Value> {

    private class Node {
        Node next;
        Key key;
        Value value;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    Node[] hashTable = (Node []) Array.newInstance(Node.class, 4);
    int size = 0;

    @Override
    public void put(Key key, Value value) {
        int hash = hashMod(key);
        this.hashTable[hash] = insertKeyAtNode(key, value, this.hashTable[hash]);
        if (this.size > hashTable.length*8) {
            resize(hashTable.length * 2);
        }
    }

    private Node insertKeyAtNode(Key key, Value value, Node node) {
        if (node == null) {
            this.size++;
            return new Node(key, value);
        }

        if (node.key.equals(key)) {
            node.value = value;
            return node;
        }

        node.next = insertKeyAtNode(key, value, node.next);
        return node;
    }

    private int hashMod(Key key) {
        return (key.hashCode() & 0x7fffffff) % this.hashTable.length;
    }

    @Override
    public Value get(Key key) {
        int hash = hashMod(key);
        return getFromNode(key, this.hashTable[hash]);
    }

    private Value getFromNode(Key key, Node node) {
        if (node == null) return null;

        if (node.key.equals(key))
            return node.value;

        return getFromNode(key, node.next);
    }

    @Override
    public boolean containsKey(Key key) {
        return get(key) != null;
    }

    @Override
    public void delete(Key key) {
        int hash = hashMod(key);
        this.hashTable[hash] = deleteFromNode(key, this.hashTable[hash]);
        if (this.size < this.hashTable.length*2 && this.hashTable.length > 0) {
            resize(this.hashTable.length/2);
        }
    }

    private void resize(int newSize) {
        Node[] oldHashTable = this.hashTable;
        this.hashTable = (Node[]) Array.newInstance(Node.class, newSize);
        this.size = 0;

        for (int i = 0; i < oldHashTable.length; i++) {
            while (oldHashTable[i] != null) {
                Node node = oldHashTable[i];
                put(node.key, node.value);
                oldHashTable[i] = node.next;
            }
        }

    }

    private Node deleteFromNode(Key key, Node node) {
        if (node == null) return null;

        if (node.key.equals(key)) {
            this.size--;
            return node.next;
        }

        node.next = deleteFromNode(key, node.next);
        return node;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }
}

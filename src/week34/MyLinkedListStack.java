package week34;

import week40.IBag;

import java.util.Iterator;

/**
 * @author Torstein Strømme
 */
public class MyLinkedListStack<E> extends IStack<E> {
    private Node head;
    private int size;

    private class Node {
        private Node next;
        private E payload;

        private Node(E item) {
            this.payload = item;
            this.next = null;
        }
    }

    @Override
    public E peek() {
        return this.head.payload;
    }

    @Override
    public E pop() {
        E res = this.head.payload;
        this.head = this.head.next;
        this.size--;
        return res;
    }

    @Override
    public void push(E item) {
        Node newNode = new Node(item);
        newNode.next = this.head;
        this.size++;
        this.head = newNode;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node here = MyLinkedListStack.this.head;

            @Override
            public boolean hasNext() {
                return this.here != null;
            }

            @Override
            public E next() {
                E res = this.here.payload;
                this.here = this.here.next;
                return res;
            }
        };
    }
}

package week34;

/**
 * @author Torstein Strømme
 */
public class MyLinkedListStack<E> implements IStack<E> {
    private Node head;

    private class Node {
        private Node next;
        private E payload;

        private Node(E item) {
            this.payload = item;
            this.next = null;
        }
    }

    @Override
    public boolean empty() {
        return this.head == null;
    }

    @Override
    public E peek() {
        return this.head.payload;
    }

    @Override
    public E pop() {
        E res = this.head.payload;
        this.head = this.head.next;
        return res;
    }

    @Override
    public void push(E item) {
        Node newNode = new Node(item);
        newNode.next = this.head;
        this.head = newNode;
    }
}

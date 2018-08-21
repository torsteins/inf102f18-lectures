package week34;

/**
 * @author Torstein Strømme
 */
public class MyLinkedListStack<E> implements IStack<E> {
    private int n;
    private Node<E> head;

    private class Node<E> {
        public Node<E> next;
        public E payload;

        public Node(E item) {
            this.payload = item;
            this.next = null;
        }
    }

    @Override
    public boolean empty() {
        return n == 0;
    }

    @Override
    public E peek() {
        return this.head.payload;
    }

    @Override
    public E pop() {
        E res = this.head.payload;
        this.head = this.head.next;
        this.n--;
        return res;
    }

    @Override
    public void push(E item) {
        Node<E> newNode = new Node<E>(item);
        newNode.next = this.head;
        this.head = newNode;
        this.n++;
    }
}

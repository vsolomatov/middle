package node;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public final class Node<T> {
    private final Node next;
    private final T value;

    public Node(Node next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "node.Node{"
                + "next=" + next
                + ", value=" + value
                + '}';
    }
}

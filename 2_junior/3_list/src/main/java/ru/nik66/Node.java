package ru.nik66;

public class Node<E> {

    private E value;
    private Node<E> next;

    public Node(E value) {
        this.value = value;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    /**
     * Floyd's Tortoise and Hare.
     * Cycle detection algorithm.
     * @param first - Node for start.
     * @return true if nodes has cycle.
     */
    public static boolean hasCycle(Node first) {
        boolean result = false;
        Node slow = first;
        Node fast = first;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                result = true;
                break;
            }
        }
        return result;
    }
}

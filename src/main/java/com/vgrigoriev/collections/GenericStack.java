package com.vgrigoriev.collections;

/**
 * author vgrigore on 13.10.2014.
 */
public class GenericStack<T> implements Iterable<T>{

    private Node first = null;

    @Override
    public Iterator iterator() {
        return new Iterator();
    }

    private class Iterator implements java.util.Iterator<T> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            /*not implemented*/
        }
    }

    private class Node {
        T item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(T input) {
        Node oldHead = first;
        first = new Node();
        first.item = input;
        first.next = oldHead;
    }

    public T pop() {
        T item = first.item;
        first = first.next;
        return item;
    }
}

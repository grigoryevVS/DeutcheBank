package com.vgrigoriev.collections;

import java.util.*;

/**
 * Own implementation of the collection LinkedList
 */
public class MyLinkedList<E> {


    private transient Node<E> header = new Node<E>(null, null, null);
    private transient int size = 0;

    /**
     * Constructor which create empty collection, in which header is references to itself
     */
    public MyLinkedList() {
        header.next = header.previous = header;
    }

    public int getSize() {
        return size;
    }

    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("There no elements");
        }
        return header.next.element;
    }

    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException("There no elements");
        }
        return header.previous.element;
    }

    public E get(int index) {
        return entry(index).element;
    }

    public void set(int index, E element) {
        Node<E> node = entry(index);
        node.element = element;
    }

    public void removeLast() {
        remove(header.previous);
    }

    public E removeFirst() {
        return remove(header.next);
    }

    public void addFirst(E element) {
        addBefore(element, header.next);
    }

    public void addLast(E element) {
        addBefore(element, header.previous);
    }

    private void addBefore(E element, Node<E> node) {
        Node<E> newNode = new Node<E>(element, node, node.previous);
        newNode.previous.next = newNode;
        newNode.next.previous = newNode;
        ++size;
    }


    /**
     * add element to the collection
     * @param element - concrete element to add
     */
    public void add(E element) {
        addBefore(element, header);
    }

    /**
     *
     * add element to the concrete position(index)
     * @param index - position to add
     * @param element - element to add
     */
    public void add(int index, E element) {
        indexCheck(index);
        if (index == size) {
            addBefore(element, header);
        } else {
            addBefore(element, entry(index));
        }
    }

    /**
     * remove target element from collection
     */
    public boolean remove(E element) {
        if (element == null) {
            for (Node<E> node = header.next; node != header; node = node.next) {
                if (node.element == null) {
                    remove(node);
                    return true;
                }
            }
        } else {
            for(Node<E> node = header.next; node != header; node = node.next) {
                if (node.element.equals(element)) {
                    remove(node);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * remove target element from collection by its index in the list
     * @param index - targets index within collection
     */
    public void remove(int index) {
        indexCheck(index);
        remove(entry(index));
    }

    /**
     * help method to remove node
     * @param node - target node to remove
     */
    private E remove(Node<E> node) {
        if (node == header) {
            throw new NoSuchElementException("There is no such element!");
        }
        E result = node.element;
        node.previous.next = node.next;
        node.next.previous = node.previous;
        node.element = null;
        node.previous = node.next = null;
        --size;
        return result;
    }

    private Node<E> entry(int index) {
        indexCheck(index);

        Node<E> result = header;
        if (index < (size >> 1)) {
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        } else {
            for (int i = size; i > index; i++) {
                result = result.previous;
            }
        }
        return result;
    }

    private void indexCheck(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    /**
     * Clear whole linkedList
     */
    public void clear() {
        Node<E> node = header.next;
        while (node != header) {
            Node<E> nextNode = node;
            node.element = null;
            node.previous = node.next = null;
            node = nextNode;
        }
        size = 0;
        header.previous = header.next = header;
    }

    /**
     * find index of target element
     * @param element - target element to search its index in the list
     * @return - current index or -1 if there is no such element
     */
    public int indexOf(E element) {
        int currIndex = 0;

        if (element == null) {
            for (Node<E> node = header.next; node != header; node = node.next) {
                if (node.element == null) {
                    return currIndex;
                }
                ++currIndex;
            }
        } else {
            for (Node<E> node = header.next; node != header; node = node.next) {
                if (node.element.equals(element)) {
                    return currIndex;
                }
                ++currIndex;
            }
        }
        return -1;
    }

    /**
     * list like a stack, pushes in the front of current list element
     * @param element - target element to push
     */
    public void push(E element) {
        addFirst(element);
    }

    /**
     * list like a stack, pop the first element from current list
     * @return - target element that was removed
     */
    public E pop() {
        return removeFirst();
    }

    /**
     * Here a part responsible for the iteration on the linkedList
     */


    public ListIterator<E> listIterator(int index) {
        return new ListIter(index);
    }

    private class ListIter implements ListIterator<E>{
        // TODO

        private int nextIndex;
        private Node<E> last = header;
        private Node<E> next;


        ListIter(int index) {
            indexCheck(index);
            if (index < (size >> 1)) {
                next = header.next;
                for (nextIndex = 0; nextIndex < index; nextIndex++) {
                    next = next.next;
                }
            } else {
                next = header;
                for (nextIndex = size; nextIndex > index; nextIndex--) {
                    next = next.previous;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return nextIndex != size;
        }

        @Override
        public E next() {
            if (nextIndex == size) {
                throw new NoSuchElementException();
            }
            last = next = next.next;
            nextIndex++;
            return last.element;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex != 0;
        }

        @Override
        public E previous() {
            if (nextIndex == 0) {
                throw new NoSuchElementException();
            }
            last = next = next.previous;
            nextIndex--;
            return last.element;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return (nextIndex - 1);
        }

        @Override
        public void remove() {
            Node<E> nextOne = last.next;
            try {
                MyLinkedList.this.remove(last);
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException();
            }
            if (next == last) {
                next = nextOne;
            } else {
                nextIndex--;
                last = header;
            }

        }

        @Override
        public void set(E e) {
            if (last == header) {
                throw new IllegalArgumentException();
            }
            last.element = e;
        }

        @Override
        public void add(E e) {
            last = header;
            addBefore(e, next);
            nextIndex++;
        }
    }


    public Iterator<E> descendingIterator() {
        return new DescendingIterator();
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> e = header.next; e != header; e = e.next) {
            result[i++] = e.element;
        }
        return result;
    }


    /**
     * Inner element of the collection, which contains of data( concrete element and
     * references on the next and previous element of the target collection)
     * @param <E> - generic type of the element
     */
    private static class Node<E>{

        private E element;
        private Node<E> next;
        private Node<E> previous;

        Node(E element, Node<E> next, Node<E> previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }


    private class DescendingIterator implements Iterator<E> {

        final ListIter iter = new ListIter(getSize());

        @Override
        public boolean hasNext() {
            return iter.hasPrevious();
        }

        @Override
        public E next() {
            return iter.previous();
        }

        @Override
        public void remove() {
            iter.remove();
        }
    }
}

package com.vgrigoriev.collections;

import java.util.*;

/**
 * Own implementation of collection ArrayList
 */
public class MyArrayList<E> {

    private Object[] elementData;

    private int size;

    /**
     * Constructor with capacity as a parameter
     *
     * @param initialCapacity - start capacity
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("InitialCapacity can't be lower then 0");
        }
        elementData = new Object[initialCapacity];
    }

    /**
     * Default constructor
     */
    public MyArrayList() {
        this(10);
    }

    /**
     * Get arrayList size
     *
     * @return number of elements in this ArrayLsit
     */
    public int size() {
        return size;
    }

    /**
     * Increase arrays capacity if needed
     */
    public void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    /**
     * overloaded method of adding element to the collection
     *
     * @param element - target element to add
     */
    public boolean add(E element) {
        ensureCapacity(size + 1);
        elementData[size++] = element;
        return true;
    }

    /**
     * overloaded method of adding element to the collection
     *
     * @param index   - position of element, on which position we need to add new element
     * @param element - target object to add to the collection
     */
    public void add(int index, E element) {
        indexCheck(index);

        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * Overloaded method to remove element from collection
     *
     * @param element - target element to remove
     */
    public boolean remove(E element) {
        if (element == null) {
            for (int index = 0; index < size; index++) {
                if (elementData[index] == null) {
                    remover(index);
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (element.equals(elementData[index])) {
                    remover(index);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * * Overloaded method to remove element from collection
     *
     * @param index - concrete index from where need to delete element
     * @return - generic element need to remove
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        indexCheck(index);

        E oldValue = (E) elementData[index];
        remover(index);
        return  oldValue;
    }

    /**
     * checking that index less then size of target collection
     * and more then 0
     *
     * @param index - index from what position we need to remove element
     */
    private void indexCheck(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Wrong index range");
        }
    }

    /**
     * removing concrete element of data from our collection
     *
     * @param index - target index to delete element from
     */
    private void remover(int index) {
        int movements = size - index - 1;
        if (movements > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, movements);
            elementData[--size] = null; // for GC
        }
    }

    /**
     * clear target collection
     */
    public void clear() {
        for (int index = 0; index < size; index++) {
            elementData[index] = null;
        }
        size = 0;
    }

    /**
     * capacity to the current size of arrayList
     */
    public void trimToSize() {
        int oldCapacity = elementData.length;
        if (oldCapacity > size) {
            elementData = Arrays.copyOf(elementData, size);
        }
    }

    /**
     * check for empty arrayList
     * @return - true if the collection empty, else return false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Find concrete index of element from argument list
     * return -1 if element is not found, else return concrete index of element.
     */
    public int indexOf(Object element) {
        if (element == null) {
            for (int index = 0; index < size; index++) {
                if (elementData[index] == null) {
                    return index;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (elementData[index].equals(element)) {
                    return index;
                }
            }
        }
        return -1;
    }

    /**
     * Find last concrete index of element from argument list
     * return -1 if element is not found, else return concrete index of element.
     */
    public int lastIndexOf(Object element) {
        if (element == null) {
            for (int index = size - 1; index >= 0; index--) {
                if (elementData[index] == null) {
                    return index;
                }
            }
        } else {
            for (int index = size - 1; index >= 0; index--) {
                if (elementData[index].equals(element)) {
                    return index;
                }
            }
        }
        return -1;
    }

    /**
     * getting element from the concrete index of collection
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        indexCheck(index);
        return (E) elementData[index];
    }

    /**
     * Setting a new value for the element from target index
     */
    @SuppressWarnings("unchecked")
    public void set(int index, E element) {
        indexCheck(index);
        elementData[index] = element;
    }
}

package com.vgrigoriev.algorithms.stacksQueues;

/**
 * author vgrigore on 16.10.2014.
 */

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Stack with max. Create a data structure that efficiently supports the stack operations
 * (push and pop) and also a return-the-maximum operation. Assume the elements
 * are real numbers so that you can compare them
 * This collection works like LIFO stack, implemented on the arrayList
 *
 */
public class StackWithMax{

    private List<Integer> elementData = new ArrayList<Integer>();

    public StackWithMax() {}

    public List<Integer> getElementData() {
        return elementData;
    }

    public void setElementData(List<Integer> elementData) {
        this.elementData = elementData;
    }

    public int getLastIndex() {
        return elementData.size() - 1;
    }

    public int getSize() {
        return elementData.size();
    }


    public void push(int x) {
        elementData.add(x);
    }

    public int pop() {
        if (null != elementData && !elementData.isEmpty()) {
            int result = elementData.get(getLastIndex());
            elementData.remove(getLastIndex());
            return result;
        }
        throw new EmptyStackException();
    }

    public int getMaxValue() {
        if (null != elementData && !elementData.isEmpty()) {
            int maxValue = elementData.get(0);
            for (int i = 1; i < elementData.size(); i++) {
                int element = elementData.get(i);
                if (element > maxValue) {
                    maxValue = element;
                }
            }
            return maxValue;
        }
        throw new EmptyStackException();
    }

    public int getMinValue() {
        if (null != elementData && !elementData.isEmpty()) {
            int minValue = elementData.get(0);
            for (int i = 1; i < elementData.size(); i++) {
                int element = elementData.get(i);
                if (element < minValue) {
                    minValue = element;
                }
            }
            return minValue;
        }
        throw new EmptyStackException();
    }

}

package com.vgrigoriev.algorithms.sorts;

import java.util.Random;

/**
 * Util class for different static sort methods
 */
public class SortUtil {

    /**
     * order of growth logN
     * good algo to search in the sorted collection
     * @param input
     * @param key
     * @return
     */
    public static int binarySearch(int[] input, int key) {

        int low = 0;
        int high = input.length - 1;

        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (key < input[middle]) {
                high = middle - 1;
            } else if (key > input[middle]) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * order of growth N^2 - compares. N - exchanges
     * just for learning, bad algorithm for the live examples
     */
    public static void selectionSort(Comparable[] input) {

        int size = input.length;
        for (int i = 0; i < size; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (less(input[j], input[min])) {
                    min = j;
                }
            }
            exchange(input, i, min);
        }
    }

    /**
     * order of growth - 1/2 N^2.
     * if the collection already sorted in ascending order - best case. N - 1 and 0 exch, but if it is sorted
     * in the descending order - worst case.
     * not good sort method
     * @param input - input collection of elements, which need to sort
     */
    public static void insertionSort(Comparable[] input) {

        int size = input.length;
        for (int i = 0; i < size; i++) {
            for (int j = i; j > 0; j--) {
                if (less(input[j], input[j - 1])) {
                    exchange(input, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static void insertionSort(Comparable[] input, int low, int high) {
        for (int i = low; i < high; i++) {
            for (int j = i; j > low; j--) {
                if (less(input[j], input[j - 1])) {
                    exchange(input, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * the worst case - O(N^3/2)
     * usually it is better
     * used in embedded systems
     * @param input
     */
    public static void shellSort(Comparable[] input) {

        int size = input.length;
        int h = 1;
        while (h < size / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < size; i++) {
                for (int j = i; j >= h && less(input[j], input[j - 1]); j = h) {
                    exchange(input, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    /**
     * linear time to shuffle deck
     */
    public static void shuffleSort(Object[] input) {
        int size = input.length;
        for (int i = 0; i < size; i++) {
            int rand = new Random().nextInt(i + 1);        // implement StdRandom
            exchange(input, i, rand);
        }
    }

    public static void mergeSort(Comparable[] input) {
        Comparable[] aux = new Comparable[input.length];
        mergeSortHelper(input, aux, 0, input.length - 1);

    }

    /**
     * NlogN - good, but uses extra space - proportional to the N. memory space = 2N.
     * @param input
     */
    public static void bottomUpMergeSort(Comparable[] input) {
        int size = input.length;
        Comparable[] aux = new Comparable[size];
        for (int i = 1; i < size; i = i + i) {
            for (int low = 0; low < size - i; low += i + i) {
                merge(input, aux, low, low + i - 1, Math.min(low + i + i - 1, size - 1));
            }
        }
    }

    private static void mergeSortHelper(Comparable[] input, Comparable[] aux, int low, int high) {
        if (high <= low) {
            return;
        }
        int middle = low + (high - low) / 2;
        mergeSortHelper(input, aux, low, middle);
        mergeSortHelper(input, aux, middle + 1, high);
        if (!less(input[middle + 1], input[middle])) {
            return;
        }
        merge(input, aux, low, middle, high);
    }

    private static void merge(Comparable[] input, Comparable[] aux, int low, int middle, int high) {

        for (int k = low; k <= high; k++) {
            aux[k] = input[k];
        }

        int i = low;
        int j = middle + 1;
        for (int k = low; k <= high; k++) {
            if (i > middle) {
                input[k] = aux[j++];
            } else if (j > high) {
                input[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                input[k] = aux[j++];
            } else {
                input[k] = aux[i++];
            }
        }
    }


    private static void exchange(Comparable[] input, int i, int min) {
        Comparable temp = input[i];
        input[i] = input[min];
        input[min] = temp;
    }

    private static void exchange(Object[] input, int i, int min) {
        Object temp = input[i];
        input[i] = input[min];
        input[min] = temp;
    }

    @SuppressWarnings("unchecked")
    private static boolean less(Comparable comparable, Comparable comparable1) {
        return comparable.compareTo(comparable1) < 0;
    }
}

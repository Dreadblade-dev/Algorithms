package ru.dreadblade.algorithms.sort;

public class SelectionSortAlgorithm implements SortingAlgorithm {
    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minimalElementIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[minimalElementIndex].compareTo(array[j]) > 0) {
                    minimalElementIndex = j;
                }
            }

            T temp = array[minimalElementIndex];
            array[minimalElementIndex] = array[i];
            array[i] = temp;
        }
    }
}
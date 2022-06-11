package ru.dreadblade.algorithms.sort;

public class InsertionSortAlgorithm implements SortingAlgorithm {
    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T temp = array[i];

            int j = i;

            while (j > 0 && array[j - 1].compareTo(temp) > 0) {
                array[j] = array[j - 1];
                j--;
            }

            array[j] = temp;
        }
    }
}

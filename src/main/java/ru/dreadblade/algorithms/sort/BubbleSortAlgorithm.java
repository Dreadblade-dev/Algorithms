package ru.dreadblade.algorithms.sort;

public class BubbleSortAlgorithm implements SortingAlgorithm {
    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i].compareTo(array[j]) < 0) {
                    swap(array, i, j);
                }
            }
        }
    }

    private <T extends Comparable<T>> void swap(T[] array, int firstIndex, int secondIndex) {
        T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}

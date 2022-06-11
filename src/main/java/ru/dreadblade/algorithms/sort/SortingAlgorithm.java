package ru.dreadblade.algorithms.sort;

public interface SortingAlgorithm {
    <T extends Comparable<T>> void sort(T[] array);

    static <T extends Comparable<T>> void bubbleSort(T[] array) {
        new BubbleSortAlgorithm().sort(array);
    }

    static <T extends Comparable<T>> void insertionSort(T[] array) {
        new InsertionSortAlgorithm().sort(array);
    }

    static <T extends Comparable<T>> void selectionSort(T[] array) {
        new SelectionSortAlgorithm().sort(array);
    }
}

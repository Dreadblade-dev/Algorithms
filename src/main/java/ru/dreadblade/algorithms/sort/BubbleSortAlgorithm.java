package ru.dreadblade.algorithms.sort;

public class BubbleSortAlgorithm implements SortingAlgorithm {
    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        int i = 0;
        int n = array.length;

        boolean needIteration = true;

        while (i < n - 1 && needIteration) {
            needIteration = false;

            for (int j = 1; j < n - i; j++) {
                if (array[j].compareTo(array[j - 1]) < 0) {
                    swap(array, j, j - 1);
                    needIteration = true;
                }
            }

            if (!needIteration) {
                break;
            }

            i++;
        }
    }

    private <T extends Comparable<T>> void swap(T[] array, int firstIndex, int secondIndex) {
        T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}


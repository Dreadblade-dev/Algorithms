package ru.dreadblade.algorithms.utils;

/**
 * @author Dreadblade- (https://github.com/Dreadblade-dev)
 * Utilities for testing
 */
public class TestUtils {
    public static Integer[] guaranteedCompletelyUnsortedIntegerArray(int size) {
        Integer[] array = new Integer[size];

        for (int i = size; i > 0; i--) {
            array[size - i] = i - size / 2;
        }

        return array;
    }
}

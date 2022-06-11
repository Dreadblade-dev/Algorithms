package ru.dreadblade.algorithms.utils;

/**
 * @author Dreadblade- (https://github.com/Dreadblade-dev)
 * Random number utilities for testing
 */
public class RandomTestUtils {
    /**
     * Generates an {@code Integer} array with {@code int size} where every value
     * is random {@code Integer} value in range between {@code int minValue} and {@code int maxValue}
     * @param size The <code>int</code> size of array
     * @param minValue The minimum {@code int} value in the range
     * @param maxValue The maximum {@code int} value in the range
     * @return An {@code Integer} array with {@code int} size full of random {@code Integer} values in range between min and max
     */
    public static Integer[] randomIntegerValuesArray(int size, int minValue, int maxValue) {
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = randomValue(minValue, maxValue);
        }

        return array;
    }

    /**
     * Generates a random {@code int} value between [min, max)
     * @param min The minimum {@code int} value in the range
     * @param max The maximum {@code int} value in the range
     * @return random {@code int} value between [min, max)
     */
    public static int randomValue(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }
}

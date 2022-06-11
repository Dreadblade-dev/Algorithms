package ru.dreadblade.algorithms.sort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dreadblade.algorithms.utils.RandomTestUtils;
import ru.dreadblade.algorithms.utils.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Insertion sort algorithm testing")
public class InsertionSortAlgorithmTest {
    @Test
    public void insertionSort_positiveValuesOnly_isCorrect() {
        Integer[] array = RandomTestUtils.randomIntegerValuesArray(1024, 0, 101);

        SortingAlgorithm.insertionSort(array);

        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i]).isLessThanOrEqualTo(array[i + 1]);
        }
    }

    @Test
    public void insertionSort_negativeValuesOnly_isCorrect() {
        Integer[] array = RandomTestUtils.randomIntegerValuesArray(1024, -100, 1);

        SortingAlgorithm.insertionSort(array);

        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i]).isLessThanOrEqualTo(array[i + 1]);
        }
    }

    @Test
    public void insertionSort_bothPositiveAndNegativeValues_isCorrect() {
        Integer[] array = RandomTestUtils.randomIntegerValuesArray(1024, -100, 101);

        SortingAlgorithm.insertionSort(array);

        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i]).isLessThanOrEqualTo(array[i + 1]);
        }
    }

    @Test
    public void insertionSort_bothPositiveAndNegativeValues_guaranteedCompletelyUnsortedArray_isCorrect() {
        Integer[] array = TestUtils.guaranteedCompletelyUnsortedIntegerArray(16);

        SortingAlgorithm.insertionSort(array);

        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i]).isLessThanOrEqualTo(array[i + 1]);
        }
    }

    @Test
    public void insertionSort_bothPositiveAndNegativeValues_sortedArray_isCorrect() {
        Integer[] array = TestUtils.sortedArray(1024);

        SortingAlgorithm.insertionSort(array);

        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i]).isLessThanOrEqualTo(array[i + 1]);
        }
    }
}
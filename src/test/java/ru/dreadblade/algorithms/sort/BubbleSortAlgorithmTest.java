package ru.dreadblade.algorithms.sort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dreadblade.algorithms.utils.RandomTestUtils;
import ru.dreadblade.algorithms.utils.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Bubble sort algorithm testing")
public class BubbleSortAlgorithmTest {
    @Test
    public void bubbleSort_positiveValuesOnly_isCorrect() {
        Integer[] array = RandomTestUtils.randomIntegerValuesArray(1000, 0, 101);

        SortingAlgorithm.bubbleSort(array);

        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i]).isLessThanOrEqualTo(array[i + 1]);
        }
    }

    @Test
    public void bubbleSort_negativeValuesOnly_isCorrect() {
        Integer[] array = RandomTestUtils.randomIntegerValuesArray(1000, -100, 1);

        SortingAlgorithm.bubbleSort(array);

        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i]).isLessThanOrEqualTo(array[i + 1]);
        }
    }

    @Test
    public void bubbleSort_bothPositiveAndNegativeValues_isCorrect() {
        Integer[] array = RandomTestUtils.randomIntegerValuesArray(1000, -100, 101);

        SortingAlgorithm.bubbleSort(array);

        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i]).isLessThanOrEqualTo(array[i + 1]);
        }
    }

    @Test
    public void bubbleSort_bothPositiveAndNegativeValues_guaranteedCompletelyUnsortedArray_isCorrect() {
        Integer[] array = TestUtils.guaranteedCompletelyUnsortedIntegerArray(1000);

        SortingAlgorithm.bubbleSort(array);

        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i]).isLessThanOrEqualTo(array[i + 1]);
        }
    }
}

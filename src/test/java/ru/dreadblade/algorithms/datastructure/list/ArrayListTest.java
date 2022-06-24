package ru.dreadblade.algorithms.datastructure.list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.dreadblade.algorithms.datastructure.util.IndexOutOfBoundsException;
import ru.dreadblade.algorithms.datastructure.util.Iterator;
import ru.dreadblade.algorithms.datastructure.util.MessageUtils;
import ru.dreadblade.algorithms.datastructure.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Array based list testing")
public class ArrayListTest {
    @Test
    void arrayList_createsEmpty_isCorrect() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        int expectedSize = 0;
        int expectedCapacity = 16;

        assertThat(arrayList.getSize()).isEqualTo(expectedSize);
        assertThat(arrayList.isEmpty()).isTrue();
        assertThat(arrayList.getCapacity()).isEqualTo(expectedCapacity);
    }

    @Test
    void arrayList_createsEmpty_withSpecifiedCapacity_isCorrect() {
        int expectedCapacity = 24;

        ArrayList<Integer> arrayList = new ArrayList<>(expectedCapacity);

        int expectedSize = 0;

        assertThat(arrayList.getSize()).isEqualTo(expectedSize);
        assertThat(arrayList.isEmpty()).isTrue();
        assertThat(arrayList.getCapacity()).isEqualTo(expectedCapacity);
    }

    @Test
    void arrayList_clear_isCorrect() {
        List<Integer> arrayList = new ArrayList<>();

        int expectedSize = 0;

        for (int i = 0; i < 32; i++) {
            arrayList.add(i * i);
        }

        arrayList.clear();

        assertThat(arrayList.getSize()).isEqualTo(expectedSize);
        assertThat(arrayList.isEmpty()).isTrue();
    }

    @Test
    void arrayList_generalAddTest_isCorrect() {
        List<Integer> arrayList = new ArrayList<>();

        int expectedSize = 32;

        for (int i = 0; i < expectedSize; i++) {
            arrayList.add(i * i);
        }

        assertThat(arrayList.getSize()).isEqualTo(expectedSize);
        assertThat(arrayList.isEmpty()).isFalse();

        for (int i = 0; i < expectedSize; i++) {
            assertThat(arrayList.get(i)).isEqualTo(i * i);
        }
    }

    @Test
    void arrayList_generalUsage_isCorrect() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        int expectedSize = 32;

        for (int i = 0; i < expectedSize; i++) {
            arrayList.add(i * i);
        }

        assertThat(arrayList.getSize()).isEqualTo(expectedSize);

        for (int i = 0; i < expectedSize; i++) {
            int expectedElement = i * i;

            assertThat(arrayList.contains(expectedElement)).isTrue();
            assertThat(arrayList.indexOf(expectedElement)).isGreaterThanOrEqualTo(0);
            assertThat(arrayList.get(i)).isEqualTo(expectedElement);
        }

        int expectedCapacity = 32;

        assertThat(arrayList.getCapacity()).isEqualTo(expectedCapacity);

        arrayList.add(32 * 32);

        expectedSize = 33;
        expectedCapacity = 48;

        assertThat(arrayList.getCapacity()).isEqualTo(expectedCapacity);

        int count = 0;

        while (!arrayList.isEmpty()) {
            int expectedElement = count * count;
            assertThat(arrayList.remove(0)).isEqualTo(expectedElement);

            count++;
        }

        expectedCapacity /= 8;

        assertThat(count).isEqualTo(expectedSize);
        assertThat(arrayList.getCapacity()).isEqualTo(expectedCapacity);

        expectedSize = 0;

        assertThat(arrayList.isEmpty()).isTrue();
        assertThat(arrayList.getSize()).isEqualTo(expectedSize);

        expectedSize = 32;

        for (int i = 0; i < expectedSize; i++) {
            arrayList.add(i * i);
        }

        expectedCapacity += 32;

        assertThat(arrayList.getCapacity()).isEqualTo(expectedCapacity);

        count = 0;

        while (!arrayList.isEmpty()) {
            Integer expectedElement = count * count;
            Integer nextExpectedElement = (count + 1) * (count + 1);

            assertThat(arrayList.remove(expectedElement)).isTrue();

            assertThat(arrayList.contains(expectedElement)).isFalse();
            assertThat(arrayList.indexOf(expectedElement)).isEqualTo(-1);

            if (!arrayList.isEmpty()) {
                assertThat(arrayList.get(0)).isEqualTo(nextExpectedElement);
            } else {
                assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(0), MessageUtils.getIndexOutOfBoundsExceptionMessage(0, 0));
            }

            count++;
        }

        expectedCapacity /= 8;

        assertThat(arrayList.getCapacity()).isEqualTo(expectedCapacity);
        assertThat(count).isEqualTo(expectedSize);

        expectedSize = 0;

        assertThat(arrayList.isEmpty()).isTrue();
        assertThat(arrayList.getSize()).isEqualTo(expectedSize);

        expectedSize = 32;

        for (int i = 0; i < expectedSize; i++) {
            arrayList.add(i * i);
        }

        expectedCapacity += 32;
        assertThat(arrayList.getCapacity()).isEqualTo(expectedCapacity);

        Iterator<Integer> iterator = arrayList.listIterator();

        for (int i = 0; i < expectedSize; i++) {
            int expectedElement = i * i;

            assertThat(iterator.hasNext()).isTrue();
            assertThat(iterator.next()).isEqualTo(expectedElement);

            assertThat(arrayList.contains(expectedElement)).isTrue();
            assertThat(arrayList.indexOf(expectedElement)).isGreaterThanOrEqualTo(0);
            assertThat(arrayList.get(i)).isEqualTo(expectedElement);
        }

        iterator = arrayList.listIterator();

        for (int i = 0; i < expectedSize; i++) {
            int expectedElement = i * i;

            assertThat(iterator.hasNext()).isTrue();
            assertThat(iterator.next()).isEqualTo(expectedElement);
            iterator.remove();

            assertThat(arrayList.contains(expectedElement)).isFalse();
            assertThat(arrayList.indexOf(expectedElement)).isEqualTo(-1);
        }

        expectedCapacity /= 8;
        expectedSize = 0;

        assertThat(arrayList.getCapacity()).isEqualTo(expectedCapacity);
        assertThat(arrayList.isEmpty()).isTrue();
        assertThat(arrayList.getSize()).isEqualTo(expectedSize);
    }

    @DisplayName("add(int index, T element) tests")
    @Nested
    class addAtIndexTests {
        @Test
        void arrayList_addAtIndexAtHead_emptyList_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 1;
            int expectedIndex = 0;
            int expectedElement = 123;

            arrayList.add(expectedIndex, expectedElement);

            assertThat(arrayList.getSize()).isEqualTo(expectedSize);
            assertThat(arrayList.isEmpty()).isFalse();
            assertThat(arrayList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void arrayList_addAtIndexAtHead_filledList_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = 123;

            int nextExpectedIndex = expectedIndex + 1;
            int nextExpectedElement = arrayList.get(expectedIndex);

            expectedSize++;

            arrayList.add(expectedIndex, expectedElement);

            assertThat(arrayList.getSize()).isEqualTo(expectedSize);
            assertThat(arrayList.isEmpty()).isFalse();
            assertThat(arrayList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(arrayList.get(nextExpectedIndex)).isEqualTo(nextExpectedElement);
        }

        @Test
        void arrayList_addAtIndexAtMiddle_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = 123;

            int previousExpectedIndex = expectedIndex - 1;
            int previousExpectedElement = arrayList.get(previousExpectedIndex);

            int nextExpectedIndex = expectedIndex + 1;
            int nextExpectedElement = arrayList.get(expectedIndex);

            expectedSize++;

            arrayList.add(expectedIndex, expectedElement);

            assertThat(arrayList.getSize()).isEqualTo(expectedSize);
            assertThat(arrayList.isEmpty()).isFalse();
            assertThat(arrayList.get(previousExpectedIndex)).isEqualTo(previousExpectedElement);
            assertThat(arrayList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(arrayList.get(nextExpectedIndex)).isEqualTo(nextExpectedElement);
        }

        @Test
        void arrayList_addAtIndexAtTail_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = expectedSize;
            int expectedElement = 123;

            int previousExpectedIndex = expectedIndex - 1;
            int previousExpectedElement = arrayList.get(previousExpectedIndex);

            expectedSize++;

            arrayList.add(expectedIndex, expectedElement);

            assertThat(arrayList.getSize()).isEqualTo(expectedSize);
            assertThat(arrayList.isEmpty()).isFalse();
            assertThat(arrayList.get(previousExpectedIndex)).isEqualTo(previousExpectedElement);
            assertThat(arrayList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void arrayList_addAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int firstExpectedIndex = -1;
            int secondExpectedIndex = 33;

            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(firstExpectedIndex, 1), "Index: " + firstExpectedIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(secondExpectedIndex, 1), "Index: " + secondExpectedIndex + "; Size: " + expectedSize);
        }
    }

    @DisplayName("set(int index, T element) tests")
    @Nested
    class setAtIndexTests {
        @Test
        void arrayList_setAtIndexAtHead_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedReplacedElement = arrayList.get(expectedIndex);
            int expectedElement = 123;

            Integer result = arrayList.set(expectedIndex, expectedElement);

            assertThat(result).isEqualTo(expectedReplacedElement);
            assertThat(arrayList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(arrayList.getSize()).isEqualTo(expectedSize);
        }

        @Test
        void arrayList_setAtIndexAtMiddle_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedReplacedElement = arrayList.get(expectedIndex);
            int expectedElement = 123;

            Integer result = arrayList.set(expectedIndex, expectedElement);

            assertThat(result).isEqualTo(expectedReplacedElement);
            assertThat(arrayList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(arrayList.getSize()).isEqualTo(expectedSize);
        }

        @Test
        void arrayList_setAtIndexAtTail_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedReplacedElement = arrayList.get(expectedIndex);
            int expectedElement = 123;

            Integer result = arrayList.set(expectedIndex, expectedElement);

            assertThat(result).isEqualTo(expectedReplacedElement);
            assertThat(arrayList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(arrayList.getSize()).isEqualTo(expectedSize);
        }

        @Test
        void arrayList_setAtIndex_throwsIndexOutOfBounds_emptyList_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedIndex = 0;

            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(expectedIndex, 1), "Index: " + expectedIndex + "; Size: " + arrayList.getSize());
        }

        @Test
        void arrayList_setAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int firstExpectedIndex = -1;
            int secondExpectedIndex = 32;

            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(firstExpectedIndex, 1), "Index: " + firstExpectedIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(secondExpectedIndex, 1), "Index: " + secondExpectedIndex + "; Size: " + expectedSize);
        }
    }

    @DisplayName("get(int index) tests")
    @Nested
    class getAtIndexTests {
        @Test
        void arrayList_getAtIndexAtHead_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = 0;

            assertThat(arrayList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void arrayList_getAtIndexAtMiddle_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = expectedIndex * expectedIndex;

            assertThat(arrayList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void arrayList_getAtIndexAtTail_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = expectedIndex * expectedIndex;

            assertThat(arrayList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void arrayList_getAtIndex_throwsIndexOutOfBounds_emptyList_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedIndex = 0;

            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(expectedIndex), "Index: " + expectedIndex + "; Size: " + arrayList.getSize());
        }

        @Test
        void arrayList_getAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int firstExpectedIndex = -1;
            int secondExpectedIndex = 32;

            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(firstExpectedIndex), "Index: " + firstExpectedIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(secondExpectedIndex), "Index: " + secondExpectedIndex + "; Size: " + expectedSize);
        }
    }

    @DisplayName("contains(T element) tests")
    @Nested
    class containsElementTests {
        @Test
        void arrayList_containsElementAtHead_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = arrayList.get(expectedIndex);

            assertThat(arrayList.contains(expectedElement)).isTrue();
        }

        @Test
        void arrayList_containsElementAtMiddle_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = arrayList.get(expectedIndex);

            assertThat(arrayList.contains(expectedElement)).isTrue();
        }

        @Test
        void arrayList_containsElementAtTail_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = arrayList.get(expectedIndex);

            assertThat(arrayList.contains(expectedElement)).isTrue();
        }

        @Test
        void arrayList_containsElement_thatDoesNotExist_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedElement = 123;

            assertThat(arrayList.contains(expectedElement)).isFalse();
        }
    }

    @DisplayName("indexOf(T element) tests")
    @Nested
    class indexOfElementTests {
        @Test
        void arrayList_indexOfElementAtHead_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = arrayList.get(expectedIndex);

            assertThat(arrayList.indexOf(expectedElement)).isEqualTo(expectedIndex);
        }

        @Test
        void arrayList_indexOfElementAtMiddle_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = arrayList.get(expectedIndex);

            assertThat(arrayList.indexOf(expectedElement)).isEqualTo(expectedIndex);
        }

        @Test
        void arrayList_indexOfElementAtTail_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = arrayList.get(expectedIndex);

            assertThat(arrayList.indexOf(expectedElement)).isEqualTo(expectedIndex);
        }

        @Test
        void arrayList_indexOfElement_thatDoesNotExist_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = -1;
            int expectedElement = 123;

            assertThat(arrayList.indexOf(expectedElement)).isEqualTo(expectedIndex);
        }
    }

    @DisplayName("remove(int index) tests")
    @Nested
    class removeAtIndexTests {
        @Test
        void arrayList_removeAtIndexAtHead_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = arrayList.get(expectedIndex);

            Integer result = arrayList.remove(expectedIndex);

            assertThat(result).isEqualTo(expectedElement);
            assertThat(arrayList.contains(expectedElement)).isFalse();
        }

        @Test
        void arrayList_removeAtIndexAtMiddle_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = arrayList.get(expectedIndex);

            Integer result = arrayList.remove(expectedIndex);

            assertThat(result).isEqualTo(expectedElement);
            assertThat(arrayList.contains(expectedElement)).isFalse();
        }

        @Test
        void arrayList_removeAtIndexAtTail_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = arrayList.get(expectedIndex);

            Integer result = arrayList.remove(expectedIndex);

            assertThat(result).isEqualTo(expectedElement);
            assertThat(arrayList.contains(expectedElement)).isFalse();
        }

        @Test
        void arrayList_removeAtIndex_throwsIndexOutOfBounds_emptyList_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedIndex = 0;

            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(expectedIndex), "Index: " + expectedIndex + "; Size: " + arrayList.getSize());
        }

        @Test
        void arrayList_removeAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int firstExpectedIndex = -1;
            int secondExpectedIndex = 32;

            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(firstExpectedIndex), "Index: " + firstExpectedIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(secondExpectedIndex), "Index: " + secondExpectedIndex + "; Size: " + expectedSize);
        }
    }

    @DisplayName("remove(T element) tests")
    @Nested
    class removeElementTests {
        @Test
        void arrayList_removeElementAtHead_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = arrayList.get(expectedIndex);

            boolean result = arrayList.remove(Integer.valueOf(expectedElement));

            assertThat(result).isTrue();
            assertThat(arrayList.contains(expectedElement)).isFalse();
        }

        @Test
        void arrayList_removeElementAtMiddle_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = arrayList.get(expectedIndex);

            boolean result = arrayList.remove(Integer.valueOf(expectedElement));

            assertThat(result).isTrue();
            assertThat(arrayList.contains(expectedElement)).isFalse();
        }

        @Test
        void arrayList_removeElementAtTail_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = arrayList.get(expectedIndex);

            boolean result = arrayList.remove(Integer.valueOf(expectedElement));

            assertThat(result).isTrue();
            assertThat(arrayList.contains(expectedElement)).isFalse();
        }

        @Test
        void arrayList_removeElement_thatDoesNotExist_throwsNoSuchElementException_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedElement = 123;

            boolean result = arrayList.remove(Integer.valueOf(expectedElement));

            assertThat(result).isFalse();
            assertThat(arrayList.contains(expectedElement)).isFalse();
        }
    }

    @DisplayName("listIterator() tests")
    @Nested
    class listIteratorTest {
        @Test
        void arrayList_listIterator_emptyList_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            Iterator<Integer> iterator = arrayList.listIterator();

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void arrayList_listIterator_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            Iterator<Integer> iterator = arrayList.listIterator();

            for (int i = 0; i < expectedSize; i++) {
                assertThat(iterator.hasNext()).isTrue();
                assertThat(iterator.next()).isEqualTo(i * i);
            }

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }
    }

    @DisplayName("listIterator(int beginIndex) tests")
    @Nested
    class listIteratorAtIndexTest {
        @Test
        void arrayList_listIteratorAtIndexAtHead_emptyList_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int beginIndex = 0;

            Iterator<Integer> iterator = arrayList.listIterator(beginIndex);

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void arrayList_listIteratorAtIndexAtHead_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int beginIndex = 0;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            Iterator<Integer> iterator = arrayList.listIterator(beginIndex);

            for (int i = 0; i < expectedSize; i++) {
                assertThat(iterator.hasNext()).isTrue();
                assertThat(iterator.next()).isEqualTo(i * i);
            }

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void arrayList_listIteratorAtIndexAtMiddle_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int beginIndex = 16;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            Iterator<Integer> iterator = arrayList.listIterator(beginIndex);

            for (int i = 16; i < expectedSize; i++) {
                assertThat(iterator.hasNext()).isTrue();
                assertThat(iterator.next()).isEqualTo(i * i);
            }

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void arrayList_listIteratorAtIndexAtTail_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int beginIndex = 31;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            Iterator<Integer> iterator = arrayList.listIterator(beginIndex);

            assertThat(iterator.hasNext()).isTrue();
            assertThat(iterator.next()).isEqualTo(arrayList.get(beginIndex));

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void arrayList_listIteratorRemoveAtIndexAtHead_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int beginIndex = 0;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedElement = arrayList.get(beginIndex);
            int nextExpectedElement = arrayList.get(beginIndex + 1);

            Iterator<Integer> iterator = arrayList.listIterator(beginIndex);

            assertThat(iterator.next()).isEqualTo(expectedElement);

            iterator.remove();
            expectedSize--;

            assertThat(arrayList.getSize()).isEqualTo(expectedSize);
            assertThat(arrayList.contains(expectedElement)).isFalse();
            assertThat(arrayList.get(beginIndex)).isEqualTo(nextExpectedElement);
        }

        @Test
        void arrayList_listIteratorRemoveAtIndexAtMiddle_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int beginIndex = 16;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedElement = arrayList.get(beginIndex);
            int nextExpectedElement = arrayList.get(beginIndex + 1);

            Iterator<Integer> iterator = arrayList.listIterator(beginIndex);

            assertThat(iterator.next()).isEqualTo(expectedElement);

            iterator.remove();
            expectedSize--;

            assertThat(arrayList.getSize()).isEqualTo(expectedSize);
            assertThat(arrayList.contains(expectedElement)).isFalse();
            assertThat(arrayList.get(beginIndex)).isEqualTo(nextExpectedElement);
        }

        @Test
        void arrayList_listIteratorRemoveAtIndexAtTail_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int beginIndex = 31;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int expectedElement = arrayList.get(beginIndex);

            Iterator<Integer> iterator = arrayList.listIterator(beginIndex);

            assertThat(iterator.next()).isEqualTo(expectedElement);

            iterator.remove();
            expectedSize--;

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);

            assertThat(arrayList.getSize()).isEqualTo(expectedSize);
            assertThat(arrayList.contains(expectedElement)).isFalse();
            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(beginIndex));
        }

        @Test
        void arrayList_listIteratorRemoveAtIndex_withoutCallingNext_throwsNoSuchElementException() {
            List<Integer> arrayList = new ArrayList<>();

            int beginIndex = 0;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            Iterator<Integer> iterator = arrayList.listIterator(beginIndex);

            assertThrows(NoSuchElementException.class, iterator::remove);
        }

        @Test
        void arrayList_listIteratorAtIndex_throwsIndexOutOfBounds_emptyList_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int beginIndex = -1;

            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.listIterator(beginIndex), "Index: " + beginIndex + "; Size: " + arrayList.getSize());
        }

        @Test
        void arrayList_listIteratorAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> arrayList = new ArrayList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                arrayList.add(i * i);
            }

            int firstBeginIndex = -1;
            int secondBeginIndex = 33;

            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.listIterator(firstBeginIndex), "Index: " + firstBeginIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> arrayList.listIterator(secondBeginIndex), "Index: " + secondBeginIndex + "; Size: " + expectedSize);
        }
    }
}

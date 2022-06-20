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

@DisplayName("Doubly linked list testing")
public class DoublyLinkedListTest {
    @Test
    void doublyLinkedList_createsEmpty_isCorrect() {
        List<Integer> doublyLinkedList = new DoublyLinkedList<>();

        int expectedSize = 0;

        assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);
        assertThat(doublyLinkedList.isEmpty()).isTrue();
    }

    @Test
    void doublyLinkedList_clear_isCorrect() {
        List<Integer> doublyLinkedList = new DoublyLinkedList<>();

        int expectedSize = 0;

        for (int i = 0; i < 32; i++) {
            doublyLinkedList.add(i * i);
        }

        doublyLinkedList.clear();

        assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);
        assertThat(doublyLinkedList.isEmpty()).isTrue();
    }

    @Test
    void doublyLinkedList_generalAddTest_isCorrect() {
        List<Integer> doublyLinkedList = new DoublyLinkedList<>();

        int expectedSize = 32;

        for (int i = 0; i < expectedSize; i++) {
            doublyLinkedList.add(i * i);
        }

        assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);
        assertThat(doublyLinkedList.isEmpty()).isFalse();

        for (int i = 0; i < expectedSize; i++) {
            assertThat(doublyLinkedList.get(i)).isEqualTo(i * i);
        }
    }

    @Test
    void doublyLinkedList_generalUsage_isCorrect() {
        List<Integer> doublyLinkedList = new DoublyLinkedList<>();

        int expectedSize = 32;

        for (int i = 0; i < expectedSize; i++) {
            doublyLinkedList.add(i * i);
        }

        assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);

        for (int i = 0; i < expectedSize; i++) {
            int expectedElement = i * i;

            assertThat(doublyLinkedList.contains(expectedElement)).isTrue();
            assertThat(doublyLinkedList.indexOf(expectedElement)).isGreaterThanOrEqualTo(0);
            assertThat(doublyLinkedList.get(i)).isEqualTo(expectedElement);
        }

        int count = 0;

        while (!doublyLinkedList.isEmpty()) {
            int expectedElement = count * count;
            assertThat(doublyLinkedList.remove(0)).isEqualTo(expectedElement);

            count++;
        }

        assertThat(count).isEqualTo(expectedSize);

        expectedSize = 0;

        assertThat(doublyLinkedList.isEmpty()).isTrue();
        assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);

        expectedSize = 32;

        for (int i = 0; i < expectedSize; i++) {
            doublyLinkedList.add(i * i);
        }

        count = 0;

        while (!doublyLinkedList.isEmpty()) {
            Integer expectedElement = count * count;
            Integer nextExpectedElement = (count + 1) * (count + 1);

            assertThat(doublyLinkedList.remove(expectedElement)).isTrue();

            assertThat(doublyLinkedList.contains(expectedElement)).isFalse();
            assertThat(doublyLinkedList.indexOf(expectedElement)).isEqualTo(-1);

            if (!doublyLinkedList.isEmpty()) {
                assertThat(doublyLinkedList.get(0)).isEqualTo(nextExpectedElement);
            } else {
                assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.get(0), MessageUtils.getIndexOutOfBoundsExceptionMessage(0, 0));
            }

            count++;
        }

        assertThat(count).isEqualTo(expectedSize);

        expectedSize = 0;

        assertThat(doublyLinkedList.isEmpty()).isTrue();
        assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);

        expectedSize = 32;

        for (int i = 0; i < expectedSize; i++) {
            doublyLinkedList.add(i * i);
        }
        Iterator<Integer> iterator = doublyLinkedList.listIterator();


        for (int i = 0; i < expectedSize; i++) {
            int expectedElement = i * i;

            assertThat(iterator.hasNext()).isTrue();
            assertThat(iterator.next()).isEqualTo(expectedElement);

            assertThat(doublyLinkedList.contains(expectedElement)).isTrue();
            assertThat(doublyLinkedList.indexOf(expectedElement)).isGreaterThanOrEqualTo(0);
            assertThat(doublyLinkedList.get(i)).isEqualTo(expectedElement);
        }

        iterator = doublyLinkedList.listIterator();

        for (int i = 0; i < expectedSize; i++) {
            int expectedElement = i * i;

            assertThat(iterator.hasNext()).isTrue();
            assertThat(iterator.next()).isEqualTo(expectedElement);
            iterator.remove();

            System.out.println(doublyLinkedList);

            assertThat(doublyLinkedList.contains(expectedElement)).isFalse();
            assertThat(doublyLinkedList.indexOf(expectedElement)).isEqualTo(-1);
        }

        expectedSize = 0;

        assertThat(doublyLinkedList.isEmpty()).isTrue();
        assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);
    }

    @DisplayName("add(int index, T element) tests")
    @Nested
    class addAtIndexTests {
        @Test
        void doublyLinkedList_addAtIndexAtHead_emptyList_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 1;
            int expectedIndex = 0;
            int expectedElement = 123;

            doublyLinkedList.add(expectedIndex, expectedElement);

            assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);
            assertThat(doublyLinkedList.isEmpty()).isFalse();
            assertThat(doublyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void doublyLinkedList_addAtIndexAtHead_filledList_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = 123;

            int nextExpectedIndex = expectedIndex + 1;
            int nextExpectedElement = doublyLinkedList.get(expectedIndex);

            expectedSize++;

            doublyLinkedList.add(expectedIndex, expectedElement);

            assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);
            assertThat(doublyLinkedList.isEmpty()).isFalse();
            assertThat(doublyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(doublyLinkedList.get(nextExpectedIndex)).isEqualTo(nextExpectedElement);
        }

        @Test
        void doublyLinkedList_addAtIndexAtMiddle_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = 123;

            int previousExpectedIndex = expectedIndex - 1;
            int previousExpectedElement = doublyLinkedList.get(previousExpectedIndex);

            int nextExpectedIndex = expectedIndex + 1;
            int nextExpectedElement = doublyLinkedList.get(expectedIndex);

            expectedSize++;

            doublyLinkedList.add(expectedIndex, expectedElement);

            assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);
            assertThat(doublyLinkedList.isEmpty()).isFalse();
            assertThat(doublyLinkedList.get(previousExpectedIndex)).isEqualTo(previousExpectedElement);
            assertThat(doublyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(doublyLinkedList.get(nextExpectedIndex)).isEqualTo(nextExpectedElement);
        }

        @Test
        void doublyLinkedList_addAtIndexAtTail_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = expectedSize;
            int expectedElement = 123;

            int previousExpectedIndex = expectedIndex - 1;
            int previousExpectedElement = doublyLinkedList.get(previousExpectedIndex);

            expectedSize++;

            doublyLinkedList.add(expectedIndex, expectedElement);

            assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);
            assertThat(doublyLinkedList.isEmpty()).isFalse();
            assertThat(doublyLinkedList.get(previousExpectedIndex)).isEqualTo(previousExpectedElement);
            assertThat(doublyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void doublyLinkedList_addAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int firstExpectedIndex = -1;
            int secondExpectedIndex = 33;

            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.add(firstExpectedIndex, 1), "Index: " + firstExpectedIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.add(secondExpectedIndex, 1), "Index: " + secondExpectedIndex + "; Size: " + expectedSize);
        }
    }

    @DisplayName("set(int index, T element) tests")
    @Nested
    class setAtIndexTests {
        @Test
        void doublyLinkedList_setAtIndexAtHead_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedReplacedElement = doublyLinkedList.get(expectedIndex);
            int expectedElement = 123;

            Integer result = doublyLinkedList.set(expectedIndex, expectedElement);

            assertThat(result).isEqualTo(expectedReplacedElement);
            assertThat(doublyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);
        }

        @Test
        void doublyLinkedList_setAtIndexAtMiddle_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedReplacedElement = doublyLinkedList.get(expectedIndex);
            int expectedElement = 123;

            Integer result = doublyLinkedList.set(expectedIndex, expectedElement);

            assertThat(result).isEqualTo(expectedReplacedElement);
            assertThat(doublyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);
        }

        @Test
        void doublyLinkedList_setAtIndexAtTail_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedReplacedElement = doublyLinkedList.get(expectedIndex);
            int expectedElement = 123;

            Integer result = doublyLinkedList.set(expectedIndex, expectedElement);

            assertThat(result).isEqualTo(expectedReplacedElement);
            assertThat(doublyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);
        }

        @Test
        void doublyLinkedList_setAtIndex_throwsIndexOutOfBounds_emptyList_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedIndex = 0;

            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.set(expectedIndex, 1), "Index: " + expectedIndex + "; Size: " + doublyLinkedList.getSize());
        }

        @Test
        void doublyLinkedList_setAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int firstExpectedIndex = -1;
            int secondExpectedIndex = 32;

            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.set(firstExpectedIndex, 1), "Index: " + firstExpectedIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.set(secondExpectedIndex, 1), "Index: " + secondExpectedIndex + "; Size: " + expectedSize);
        }
    }

    @DisplayName("get(int index) tests")
    @Nested
    class getAtIndexTests {
        @Test
        void doublyLinkedList_getAtIndexAtHead_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = 0;

            assertThat(doublyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void doublyLinkedList_getAtIndexAtMiddle_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = expectedIndex * expectedIndex;

            assertThat(doublyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void doublyLinkedList_getAtIndexAtTail_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = expectedIndex * expectedIndex;

            assertThat(doublyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void doublyLinkedList_getAtIndex_throwsIndexOutOfBounds_emptyList_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedIndex = 0;

            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.get(expectedIndex), "Index: " + expectedIndex + "; Size: " + doublyLinkedList.getSize());
        }

        @Test
        void doublyLinkedList_getAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int firstExpectedIndex = -1;
            int secondExpectedIndex = 32;

            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.get(firstExpectedIndex), "Index: " + firstExpectedIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.get(secondExpectedIndex), "Index: " + secondExpectedIndex + "; Size: " + expectedSize);
        }
    }

    @DisplayName("contains(T element) tests")
    @Nested
    class containsElementTests {
        @Test
        void doublyLinkedList_containsElementAtHead_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = doublyLinkedList.get(expectedIndex);

            assertThat(doublyLinkedList.contains(expectedElement)).isTrue();
        }

        @Test
        void doublyLinkedList_containsElementAtMiddle_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = doublyLinkedList.get(expectedIndex);

            assertThat(doublyLinkedList.contains(expectedElement)).isTrue();
        }

        @Test
        void doublyLinkedList_containsElementAtTail_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = doublyLinkedList.get(expectedIndex);

            assertThat(doublyLinkedList.contains(expectedElement)).isTrue();
        }

        @Test
        void doublyLinkedList_containsElement_thatDoesNotExist_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedElement = 123;

            assertThat(doublyLinkedList.contains(expectedElement)).isFalse();
        }
    }

    @DisplayName("indexOf(T element) tests")
    @Nested
    class indexOfElementTests {
        @Test
        void doublyLinkedList_indexOfElementAtHead_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = doublyLinkedList.get(expectedIndex);

            assertThat(doublyLinkedList.indexOf(expectedElement)).isEqualTo(expectedIndex);
        }

        @Test
        void doublyLinkedList_indexOfElementAtMiddle_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = doublyLinkedList.get(expectedIndex);

            assertThat(doublyLinkedList.indexOf(expectedElement)).isEqualTo(expectedIndex);
        }

        @Test
        void doublyLinkedList_indexOfElementAtTail_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = doublyLinkedList.get(expectedIndex);

            assertThat(doublyLinkedList.indexOf(expectedElement)).isEqualTo(expectedIndex);
        }

        @Test
        void doublyLinkedList_indexOfElement_thatDoesNotExist_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = -1;
            int expectedElement = 123;

            assertThat(doublyLinkedList.indexOf(expectedElement)).isEqualTo(expectedIndex);
        }
    }

    @DisplayName("remove(int index) tests")
    @Nested
    class removeAtIndexTests {
        @Test
        void doublyLinkedList_removeAtIndexAtHead_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = doublyLinkedList.get(expectedIndex);

            Integer result = doublyLinkedList.remove(expectedIndex);

            assertThat(result).isEqualTo(expectedElement);
            assertThat(doublyLinkedList.contains(expectedElement)).isFalse();
        }

        @Test
        void doublyLinkedList_removeAtIndexAtMiddle_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = doublyLinkedList.get(expectedIndex);

            Integer result = doublyLinkedList.remove(expectedIndex);

            assertThat(result).isEqualTo(expectedElement);
            assertThat(doublyLinkedList.contains(expectedElement)).isFalse();
        }

        @Test
        void doublyLinkedList_removeAtIndexAtTail_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = doublyLinkedList.get(expectedIndex);

            Integer result = doublyLinkedList.remove(expectedIndex);

            assertThat(result).isEqualTo(expectedElement);
            assertThat(doublyLinkedList.contains(expectedElement)).isFalse();
        }

        @Test
        void doublyLinkedList_removeAtIndex_throwsIndexOutOfBounds_emptyList_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedIndex = 0;

            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.remove(expectedIndex), "Index: " + expectedIndex + "; Size: " + doublyLinkedList.getSize());
        }

        @Test
        void doublyLinkedList_removeAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int firstExpectedIndex = -1;
            int secondExpectedIndex = 32;

            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.remove(firstExpectedIndex), "Index: " + firstExpectedIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.remove(secondExpectedIndex), "Index: " + secondExpectedIndex + "; Size: " + expectedSize);
        }
    }

    @DisplayName("remove(T element) tests")
    @Nested
    class removeElementTests {
        @Test
        void doublyLinkedList_removeElementAtHead_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = doublyLinkedList.get(expectedIndex);

            boolean result = doublyLinkedList.remove(Integer.valueOf(expectedElement));

            assertThat(result).isTrue();
            assertThat(doublyLinkedList.contains(expectedElement)).isFalse();
        }

        @Test
        void doublyLinkedList_removeElementAtMiddle_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = doublyLinkedList.get(expectedIndex);

            boolean result = doublyLinkedList.remove(Integer.valueOf(expectedElement));

            assertThat(result).isTrue();
            assertThat(doublyLinkedList.contains(expectedElement)).isFalse();
        }

        @Test
        void doublyLinkedList_removeElementAtTail_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = doublyLinkedList.get(expectedIndex);

            boolean result = doublyLinkedList.remove(Integer.valueOf(expectedElement));

            assertThat(result).isTrue();
            assertThat(doublyLinkedList.contains(expectedElement)).isFalse();
        }

        @Test
        void doublyLinkedList_removeElement_thatDoesNotExist_throwsNoSuchElementException_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedElement = 123;

            boolean result = doublyLinkedList.remove(Integer.valueOf(expectedElement));

            assertThat(result).isFalse();
            assertThat(doublyLinkedList.contains(expectedElement)).isFalse();
        }
    }

    @DisplayName("listIterator() tests")
    @Nested
    class listIteratorTest {
        @Test
        void doublyLinkedList_listIterator_emptyList_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            Iterator<Integer> iterator = doublyLinkedList.listIterator();

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void doublyLinkedList_listIterator_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            Iterator<Integer> iterator = doublyLinkedList.listIterator();

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
        void doublyLinkedList_listIteratorAtIndexAtHead_emptyList_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int beginIndex = 0;

            Iterator<Integer> iterator = doublyLinkedList.listIterator(beginIndex);

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void doublyLinkedList_listIteratorAtIndexAtHead_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int beginIndex = 0;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            Iterator<Integer> iterator = doublyLinkedList.listIterator(beginIndex);

            for (int i = 0; i < expectedSize; i++) {
                assertThat(iterator.hasNext()).isTrue();
                assertThat(iterator.next()).isEqualTo(i * i);
            }

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void doublyLinkedList_listIteratorAtIndexAtMiddle_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int beginIndex = 16;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            Iterator<Integer> iterator = doublyLinkedList.listIterator(beginIndex);

            for (int i = 16; i < expectedSize; i++) {
                assertThat(iterator.hasNext()).isTrue();
                assertThat(iterator.next()).isEqualTo(i * i);
            }

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void doublyLinkedList_listIteratorAtIndexAtTail_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int beginIndex = 31;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            Iterator<Integer> iterator = doublyLinkedList.listIterator(beginIndex);

            assertThat(iterator.hasNext()).isTrue();
            assertThat(iterator.next()).isEqualTo(doublyLinkedList.get(beginIndex));

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void doublyLinkedList_listIteratorRemoveAtIndexAtHead_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int beginIndex = 0;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedElement = doublyLinkedList.get(beginIndex);
            int nextExpectedElement = doublyLinkedList.get(beginIndex + 1);

            Iterator<Integer> iterator = doublyLinkedList.listIterator(beginIndex);

            assertThat(iterator.next()).isEqualTo(expectedElement);

            iterator.remove();
            expectedSize--;

            assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);
            assertThat(doublyLinkedList.contains(expectedElement)).isFalse();
            assertThat(doublyLinkedList.get(beginIndex)).isEqualTo(nextExpectedElement);
        }

        @Test
        void doublyLinkedList_listIteratorRemoveAtIndexAtMiddle_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int beginIndex = 16;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedElement = doublyLinkedList.get(beginIndex);
            int nextExpectedElement = doublyLinkedList.get(beginIndex + 1);

            Iterator<Integer> iterator = doublyLinkedList.listIterator(beginIndex);

            assertThat(iterator.next()).isEqualTo(expectedElement);

            iterator.remove();
            expectedSize--;

            assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);
            assertThat(doublyLinkedList.contains(expectedElement)).isFalse();
            assertThat(doublyLinkedList.get(beginIndex)).isEqualTo(nextExpectedElement);
        }

        @Test
        void doublyLinkedList_listIteratorRemoveAtIndexAtTail_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int beginIndex = 31;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int expectedElement = doublyLinkedList.get(beginIndex);

            Iterator<Integer> iterator = doublyLinkedList.listIterator(beginIndex);

            assertThat(iterator.next()).isEqualTo(expectedElement);

            iterator.remove();
            expectedSize--;

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);

            assertThat(doublyLinkedList.getSize()).isEqualTo(expectedSize);
            assertThat(doublyLinkedList.contains(expectedElement)).isFalse();
            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.get(beginIndex));
        }

        @Test
        void doublyLinkedList_listIteratorAtIndex_throwsIndexOutOfBounds_emptyList_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int beginIndex = -1;

            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.listIterator(beginIndex), "Index: " + beginIndex + "; Size: " + doublyLinkedList.getSize());
        }

        @Test
        void doublyLinkedList_listIteratorAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> doublyLinkedList = new DoublyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                doublyLinkedList.add(i * i);
            }

            int firstBeginIndex = -1;
            int secondBeginIndex = 33;

            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.listIterator(firstBeginIndex), "Index: " + firstBeginIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> doublyLinkedList.listIterator(secondBeginIndex), "Index: " + secondBeginIndex + "; Size: " + expectedSize);
        }
    }
}

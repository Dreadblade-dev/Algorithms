package ru.dreadblade.algorithms.datastructure.list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.dreadblade.algorithms.datastructure.util.IndexOutOfBoundsException;
import ru.dreadblade.algorithms.datastructure.util.Iterator;
import ru.dreadblade.algorithms.datastructure.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Singly linked list testing")
public class SinglyLinkedListTest {
    @Test
    void singlyLinkedList_createsEmpty_isCorrect() {
        List<Integer> singlyLinkedList = new SinglyLinkedList<>();

        int expectedSize = 0;

        assertThat(singlyLinkedList.getSize()).isEqualTo(expectedSize);
        assertThat(singlyLinkedList.isEmpty()).isTrue();
    }

    @Test
    void singlyLinkedList_clear_isCorrect() {
        List<Integer> singlyLinkedList = new SinglyLinkedList<>();

        int expectedSize = 0;

        for (int i = 0; i < 32; i++) {
            singlyLinkedList.add(i * i);
        }

        singlyLinkedList.clear();

        assertThat(singlyLinkedList.getSize()).isEqualTo(expectedSize);
        assertThat(singlyLinkedList.isEmpty()).isTrue();
    }

    @Test
    void singlyLinkedList_generalAddTest_isCorrect() {
        List<Integer> singlyLinkedList = new SinglyLinkedList<>();

        int expectedSize = 32;

        for (int i = 0; i < expectedSize; i++) {
            singlyLinkedList.add(i * i);
        }

        assertThat(singlyLinkedList.getSize()).isEqualTo(expectedSize);
        assertThat(singlyLinkedList.isEmpty()).isFalse();

        for (int i = 0; i < expectedSize; i++) {
            assertThat(singlyLinkedList.get(i)).isEqualTo(i * i);
        }
    }

    @DisplayName("add(int index, T element) tests")
    @Nested
    class addAtIndexTests {
        @Test
        void singlyLinkedList_addAtIndexAtHead_emptyList_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 1;
            int expectedIndex = 0;
            int expectedElement = 123;

            singlyLinkedList.add(expectedIndex, expectedElement);

            assertThat(singlyLinkedList.getSize()).isEqualTo(expectedSize);
            assertThat(singlyLinkedList.isEmpty()).isFalse();
            assertThat(singlyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void singlyLinkedList_addAtIndexAtHead_filledList_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = 123;

            int nextExpectedIndex = expectedIndex + 1;
            int nextExpectedElement = singlyLinkedList.get(expectedIndex);

            expectedSize++;

            singlyLinkedList.add(expectedIndex, expectedElement);

            assertThat(singlyLinkedList.getSize()).isEqualTo(expectedSize);
            assertThat(singlyLinkedList.isEmpty()).isFalse();
            assertThat(singlyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(singlyLinkedList.get(nextExpectedIndex)).isEqualTo(nextExpectedElement);
        }

        @Test
        void singlyLinkedList_addAtIndexAtMiddle_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = 123;

            int previousExpectedIndex = expectedIndex - 1;
            int previousExpectedElement = singlyLinkedList.get(previousExpectedIndex);

            int nextExpectedIndex = expectedIndex + 1;
            int nextExpectedElement = singlyLinkedList.get(expectedIndex);

            expectedSize++;

            singlyLinkedList.add(expectedIndex, expectedElement);

            assertThat(singlyLinkedList.getSize()).isEqualTo(expectedSize);
            assertThat(singlyLinkedList.isEmpty()).isFalse();
            assertThat(singlyLinkedList.get(previousExpectedIndex)).isEqualTo(previousExpectedElement);
            assertThat(singlyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(singlyLinkedList.get(nextExpectedIndex)).isEqualTo(nextExpectedElement);
        }

        @Test
        void singlyLinkedList_addAtIndexAtTail_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = expectedSize;
            int expectedElement = 123;

            int previousExpectedIndex = expectedIndex - 1;
            int previousExpectedElement = singlyLinkedList.get(previousExpectedIndex);

            expectedSize++;

            singlyLinkedList.add(expectedIndex, expectedElement);

            assertThat(singlyLinkedList.getSize()).isEqualTo(expectedSize);
            assertThat(singlyLinkedList.isEmpty()).isFalse();
            assertThat(singlyLinkedList.get(previousExpectedIndex)).isEqualTo(previousExpectedElement);
            assertThat(singlyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void singlyLinkedList_addAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int firstExpectedIndex = -1;
            int secondExpectedIndex = 33;

            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.add(firstExpectedIndex, 1), "Index: " + firstExpectedIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.add(secondExpectedIndex, 1), "Index: " + secondExpectedIndex + "; Size: " + expectedSize);
        }
    }

    @DisplayName("set(int index, T element) tests")
    @Nested
    class setAtIndexTests {
        @Test
        void singlyLinkedList_setAtIndexAtHead_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedReplacedElement = singlyLinkedList.get(expectedIndex);
            int expectedElement = 123;

            Integer result = singlyLinkedList.set(expectedIndex, expectedElement);

            assertThat(result).isEqualTo(expectedReplacedElement);
            assertThat(singlyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(singlyLinkedList.getSize()).isEqualTo(expectedSize);
        }

        @Test
        void singlyLinkedList_setAtIndexAtMiddle_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedReplacedElement = singlyLinkedList.get(expectedIndex);
            int expectedElement = 123;

            Integer result = singlyLinkedList.set(expectedIndex, expectedElement);

            assertThat(result).isEqualTo(expectedReplacedElement);
            assertThat(singlyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(singlyLinkedList.getSize()).isEqualTo(expectedSize);
        }

        @Test
        void singlyLinkedList_setAtIndexAtTail_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedReplacedElement = singlyLinkedList.get(expectedIndex);
            int expectedElement = 123;

            Integer result = singlyLinkedList.set(expectedIndex, expectedElement);

            assertThat(result).isEqualTo(expectedReplacedElement);
            assertThat(singlyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
            assertThat(singlyLinkedList.getSize()).isEqualTo(expectedSize);
        }

        @Test
        void singlyLinkedList_setAtIndex_throwsIndexOutOfBounds_emptyList_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedIndex = 0;

            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.set(expectedIndex, 1), "Index: " + expectedIndex + "; Size: " + singlyLinkedList.getSize());
        }

        @Test
        void singlyLinkedList_setAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int firstExpectedIndex = -1;
            int secondExpectedIndex = 32;

            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.set(firstExpectedIndex, 1), "Index: " + firstExpectedIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.set(secondExpectedIndex, 1), "Index: " + secondExpectedIndex + "; Size: " + expectedSize);
        }
    }
    
    @DisplayName("get(int index) tests")
    @Nested
    class getAtIndexTests {
        @Test
        void singlyLinkedList_getAtIndexAtHead_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = 0;

            assertThat(singlyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void singlyLinkedList_getAtIndexAtMiddle_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = expectedIndex * expectedIndex;

            assertThat(singlyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void singlyLinkedList_getAtIndexAtTail_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = expectedIndex * expectedIndex;

            assertThat(singlyLinkedList.get(expectedIndex)).isEqualTo(expectedElement);
        }

        @Test
        void singlyLinkedList_getAtIndex_throwsIndexOutOfBounds_emptyList_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedIndex = 0;

            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.get(expectedIndex), "Index: " + expectedIndex + "; Size: " + singlyLinkedList.getSize());
        }

        @Test
        void singlyLinkedList_getAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int firstExpectedIndex = -1;
            int secondExpectedIndex = 32;

            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.get(firstExpectedIndex), "Index: " + firstExpectedIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.get(secondExpectedIndex), "Index: " + secondExpectedIndex + "; Size: " + expectedSize);
        }
    }

    @DisplayName("contains(T element) tests")
    @Nested
    class containsElementTests {
        @Test
        void singlyLinkedList_containsElementAtHead_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = singlyLinkedList.get(expectedIndex);

            assertThat(singlyLinkedList.contains(expectedElement)).isTrue();
        }

        @Test
        void singlyLinkedList_containsElementAtMiddle_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = singlyLinkedList.get(expectedIndex);

            assertThat(singlyLinkedList.contains(expectedElement)).isTrue();
        }

        @Test
        void singlyLinkedList_containsElementAtTail_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = singlyLinkedList.get(expectedIndex);

            assertThat(singlyLinkedList.contains(expectedElement)).isTrue();
        }

        @Test
        void singlyLinkedList_containsElement_thatDoesNotExist_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedElement = 123;

            assertThat(singlyLinkedList.contains(expectedElement)).isFalse();
        }
    }

    @DisplayName("indexOf(T element) tests")
    @Nested
    class indexOfElementTests {
        @Test
        void singlyLinkedList_indexOfElementAtHead_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = singlyLinkedList.get(expectedIndex);

            assertThat(singlyLinkedList.indexOf(expectedElement)).isEqualTo(expectedIndex);
        }

        @Test
        void singlyLinkedList_indexOfElementAtMiddle_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = singlyLinkedList.get(expectedIndex);

            assertThat(singlyLinkedList.indexOf(expectedElement)).isEqualTo(expectedIndex);
        }

        @Test
        void singlyLinkedList_indexOfElementAtTail_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = singlyLinkedList.get(expectedIndex);

            assertThat(singlyLinkedList.indexOf(expectedElement)).isEqualTo(expectedIndex);
        }

        @Test
        void singlyLinkedList_indexOfElement_thatDoesNotExist_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = -1;
            int expectedElement = 123;

            assertThat(singlyLinkedList.indexOf(expectedElement)).isEqualTo(expectedIndex);
        }
    }

    @DisplayName("remove(int index) tests")
    @Nested
    class removeAtIndexTests {
        @Test
        void singlyLinkedList_removeAtIndexAtHead_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = singlyLinkedList.get(expectedIndex);

            Integer result = singlyLinkedList.remove(expectedIndex);

            assertThat(result).isEqualTo(expectedElement);
            assertThat(singlyLinkedList.contains(expectedElement)).isFalse();
        }

        @Test
        void singlyLinkedList_removeAtIndexAtMiddle_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = singlyLinkedList.get(expectedIndex);

            Integer result = singlyLinkedList.remove(expectedIndex);

            assertThat(result).isEqualTo(expectedElement);
            assertThat(singlyLinkedList.contains(expectedElement)).isFalse();
        }

        @Test
        void singlyLinkedList_removeAtIndexAtTail_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = singlyLinkedList.get(expectedIndex);

            Integer result = singlyLinkedList.remove(expectedIndex);

            assertThat(result).isEqualTo(expectedElement);
            assertThat(singlyLinkedList.contains(expectedElement)).isFalse();
        }

        @Test
        void singlyLinkedList_removeAtIndex_throwsIndexOutOfBounds_emptyList_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedIndex = 0;

            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.remove(expectedIndex), "Index: " + expectedIndex + "; Size: " + singlyLinkedList.getSize());
        }

        @Test
        void singlyLinkedList_removeAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int firstExpectedIndex = -1;
            int secondExpectedIndex = 32;

            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.remove(firstExpectedIndex), "Index: " + firstExpectedIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.remove(secondExpectedIndex), "Index: " + secondExpectedIndex + "; Size: " + expectedSize);
        }
    }

    @DisplayName("remove(T element) tests")
    @Nested
    class removeElementTests {
        @Test
        void singlyLinkedList_removeElementAtHead_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = 0;
            int expectedElement = singlyLinkedList.get(expectedIndex);

            boolean result = singlyLinkedList.remove(Integer.valueOf(expectedElement));

            assertThat(result).isTrue();
            assertThat(singlyLinkedList.contains(expectedElement)).isFalse();
        }

        @Test
        void singlyLinkedList_removeElementAtMiddle_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = 16;
            int expectedElement = singlyLinkedList.get(expectedIndex);

            boolean result = singlyLinkedList.remove(Integer.valueOf(expectedElement));

            assertThat(result).isTrue();
            assertThat(singlyLinkedList.contains(expectedElement)).isFalse();
        }

        @Test
        void singlyLinkedList_removeElementAtTail_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedIndex = expectedSize - 1;
            int expectedElement = singlyLinkedList.get(expectedIndex);

            boolean result = singlyLinkedList.remove(Integer.valueOf(expectedElement));

            assertThat(result).isTrue();
            assertThat(singlyLinkedList.contains(expectedElement)).isFalse();
        }

        @Test
        void singlyLinkedList_removeElement_thatDoesNotExist_throwsNoSuchElementException_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedElement = 123;

            boolean result = singlyLinkedList.remove(Integer.valueOf(expectedElement));

            assertThat(result).isFalse();
            assertThat(singlyLinkedList.contains(expectedElement)).isFalse();
        }
    }

    @DisplayName("listIterator() tests")
    @Nested
    class listIteratorTest {

        @DisplayName("ListIterator create testing")
        @Nested
        class listIteratorCreateTest {
            @Test
            void singlyLinkedList_listIterator_emptyList_isCorrect() {
                List<Integer> singlyLinkedList = new SinglyLinkedList<>();

                Iterator<Integer> iterator = singlyLinkedList.listIterator();

                assertThat(iterator.hasNext()).isFalse();
                assertThrows(NoSuchElementException.class, iterator::next);
            }
        }


        @Test
        void singlyLinkedList_listIterator_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            Iterator<Integer> iterator = singlyLinkedList.listIterator();

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
        void singlyLinkedList_listIteratorAtIndexAtHead_emptyList_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int beginIndex = 0;

            Iterator<Integer> iterator = singlyLinkedList.listIterator(beginIndex);

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void singlyLinkedList_listIteratorAtIndexAtHead_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int beginIndex = 0;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            Iterator<Integer> iterator = singlyLinkedList.listIterator(beginIndex);

            for (int i = 0; i < expectedSize; i++) {
                assertThat(iterator.hasNext()).isTrue();
                assertThat(iterator.next()).isEqualTo(i * i);
            }

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void singlyLinkedList_listIteratorAtIndexAtMiddle_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int beginIndex = 16;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            Iterator<Integer> iterator = singlyLinkedList.listIterator(beginIndex);

            for (int i = 16; i < expectedSize; i++) {
                assertThat(iterator.hasNext()).isTrue();
                assertThat(iterator.next()).isEqualTo(i * i);
            }

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void singlyLinkedList_listIteratorAtIndexAtTail_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int beginIndex = 31;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            Iterator<Integer> iterator = singlyLinkedList.listIterator(beginIndex);

            assertThat(iterator.hasNext()).isTrue();
            assertThat(iterator.next()).isEqualTo(singlyLinkedList.get(beginIndex));

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
        }

        @Test
        void singlyLinkedList_listIteratorRemoveAtIndexAtHead_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int beginIndex = 0;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedElement = singlyLinkedList.get(beginIndex);
            int nextExpectedElement = singlyLinkedList.get(beginIndex + 1);

            Iterator<Integer> iterator = singlyLinkedList.listIterator(beginIndex);

            assertThat(iterator.next()).isEqualTo(expectedElement);

            iterator.remove();
            expectedSize--;

            assertThat(singlyLinkedList.getSize()).isEqualTo(expectedSize);
            assertThat(singlyLinkedList.contains(expectedElement)).isFalse();
            assertThat(singlyLinkedList.get(beginIndex)).isEqualTo(nextExpectedElement);
        }

        @Test
        void singlyLinkedList_listIteratorRemoveAtIndexAtMiddle_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int beginIndex = 16;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedElement = singlyLinkedList.get(beginIndex);
            int nextExpectedElement = singlyLinkedList.get(beginIndex + 1);

            Iterator<Integer> iterator = singlyLinkedList.listIterator(beginIndex);

            assertThat(iterator.next()).isEqualTo(expectedElement);

            iterator.remove();
            expectedSize--;

            assertThat(singlyLinkedList.getSize()).isEqualTo(expectedSize);
            assertThat(singlyLinkedList.contains(expectedElement)).isFalse();
            assertThat(singlyLinkedList.get(beginIndex)).isEqualTo(nextExpectedElement);
        }

        @Test
        void singlyLinkedList_listIteratorRemoveAtIndexAtTail_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int beginIndex = 31;
            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int expectedElement = singlyLinkedList.get(beginIndex);

            Iterator<Integer> iterator = singlyLinkedList.listIterator(beginIndex);

            assertThat(iterator.next()).isEqualTo(expectedElement);

            iterator.remove();
            expectedSize--;

            assertThat(iterator.hasNext()).isFalse();
            assertThrows(NoSuchElementException.class, iterator::next);
            
            assertThat(singlyLinkedList.getSize()).isEqualTo(expectedSize);
            assertThat(singlyLinkedList.contains(expectedElement)).isFalse();
            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.get(beginIndex));
        }

        @Test
        void singlyLinkedList_listIteratorAtIndex_throwsIndexOutOfBounds_emptyList_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int beginIndex = -1;

            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.listIterator(beginIndex), "Index: " + beginIndex + "; Size: " + singlyLinkedList.getSize());
        }

        @Test
        void singlyLinkedList_listIteratorAtIndex_throwsIndexOutOfBounds_isCorrect() {
            List<Integer> singlyLinkedList = new SinglyLinkedList<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                singlyLinkedList.add(i * i);
            }

            int firstBeginIndex = -1;
            int secondBeginIndex = 33;

            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.listIterator(firstBeginIndex), "Index: " + firstBeginIndex + "; Size: " + expectedSize);
            assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.listIterator(secondBeginIndex), "Index: " + secondBeginIndex + "; Size: " + expectedSize);
        }
    }
}
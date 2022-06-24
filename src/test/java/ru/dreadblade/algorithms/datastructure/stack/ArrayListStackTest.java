package ru.dreadblade.algorithms.datastructure.stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.dreadblade.algorithms.datastructure.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("ArrayList based stack testing")
public class ArrayListStackTest {
    @Test
    void arrayListStack_createsEmpty_isCorrect() {
        Stack<Integer> stack = new ArrayListStack<>();

        int expectedSize = 0;

        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.getSize()).isEqualTo(expectedSize);
    }

    @Test
    void arrayListStack_createsEmpty_withSpecifiedCapacity_isCorrect() {
        int expectedCapacity = 1024;

        ArrayListStack<Integer> stack = new ArrayListStack<>(expectedCapacity);

        int expectedSize = 0;

        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.getSize()).isEqualTo(expectedSize);
        assertThat(stack.getCapacity()).isEqualTo(expectedCapacity);
    }

    @Test
    void arrayListStack_clear_isCorrect() {
        Stack<Integer> stack = new ArrayListStack<>();

        int expectedSize = 32;

        for (int i = 0; i < expectedSize; i++) {
            stack.push(i * i);
        }

        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.getSize()).isEqualTo(expectedSize);

        stack.clear();

        expectedSize = 0;

        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.getSize()).isEqualTo(expectedSize);
    }

    @Test
    void arrayListStack_push_isCorrect() {
        Stack<Integer> stack = new ArrayListStack<>();

        int expectedSize = 32;

        for (int i = 0; i < expectedSize; i++) {
            stack.push(i * i);

            int currentSize = i + 1;

            assertThat(stack.getSize()).isEqualTo(currentSize);
        }

        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.getSize()).isEqualTo(expectedSize);
    }

    @Test
    void arrayListStack_capacityChange_isCorrect() {
        final int CAPACITY_VALUE = 16;
        int expectedCapacity = 0;

        ArrayListStack<Integer> stack = new ArrayListStack<>(expectedCapacity);

        assertThat(stack.getCapacity()).isEqualTo(expectedCapacity);

        stack.push(1);

        expectedCapacity = expectedCapacity + CAPACITY_VALUE;
        assertThat(stack.getCapacity()).isEqualTo(expectedCapacity);

        for (int i = 0; i < 16; i++) {
            stack.push(i * i);
        }

        expectedCapacity = expectedCapacity + CAPACITY_VALUE;
        assertThat(stack.getCapacity()).isEqualTo(expectedCapacity);

        for (int i = 0; i < 10; i++) {
            stack.pop();
        }

        assertThat(stack.getCapacity()).isEqualTo(expectedCapacity);

        stack.pop();

        expectedCapacity = expectedCapacity / 2;
        assertThat(stack.getCapacity()).isEqualTo(expectedCapacity);
    }

    @DisplayName("pop() tests")
    @Nested
    class popTests {
        @Test
        void arrayListStack_pop_isCorrect() {
            Stack<Integer> stack = new ArrayListStack<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                stack.push(i * i);
            }

            for (int i = expectedSize - 1; i >= 0; i--) {
                assertThat(stack.pop()).isEqualTo(i * i);
            }

            expectedSize = 0;

            assertThat(stack.isEmpty()).isTrue();
            assertThat(stack.getSize()).isEqualTo(expectedSize);
        }

        @Test
        void arrayListStack_popOnEmptyStack_throwsNoSuchElementException() {
            Stack<Integer> stack = new ArrayListStack<>();

            assertThrows(NoSuchElementException.class, stack::pop);
        }
    }

    @DisplayName("peek() tests")
    @Nested
    class peekTests {
        @Test
        void arrayListStack_peek_isCorrect() {
            Stack<Integer> stack = new ArrayListStack<>();

            int expectedSize = 32;

            for (int i = 0; i < expectedSize; i++) {
                stack.push(i * i);
            }

            for (int i = expectedSize - 1; i >= 0; i--) {
                assertThat(stack.peek()).isEqualTo(i * i);

                stack.pop();
            }
        }

        @Test
        void arrayListStack_popOnEmptyStack_throwsNoSuchElementException() {
            Stack<Integer> stack = new ArrayListStack<>();

            assertThrows(NoSuchElementException.class, stack::peek);
        }
    }
}

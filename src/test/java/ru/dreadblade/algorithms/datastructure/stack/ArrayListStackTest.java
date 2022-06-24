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

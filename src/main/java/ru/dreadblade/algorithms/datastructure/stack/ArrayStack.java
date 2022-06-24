package ru.dreadblade.algorithms.datastructure.stack;

import ru.dreadblade.algorithms.datastructure.util.NoSuchElementException;

import java.util.Arrays;

public class ArrayStack<T> implements Stack<T> {
    private static final int CAPACITY = 16;
    private Object[] data;
    private int size;

    public ArrayStack() {
        data = new Object[CAPACITY];
        size = 0;
    }

    public ArrayStack(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public void push(T element) {
        if (size == data.length) {
            changeCapacity(data.length + CAPACITY);
        }

        data[size++] = element;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (size < data.length / 4) {
            changeCapacity(data.length / 2);
        }

        return (T) data[--size];
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return (T) data[size - 1];
    }

    private void changeCapacity(int capacity) {
        data = Arrays.copyOf(data, capacity);
    }
}

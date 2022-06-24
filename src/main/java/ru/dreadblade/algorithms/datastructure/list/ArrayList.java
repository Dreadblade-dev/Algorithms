package ru.dreadblade.algorithms.datastructure.list;

import ru.dreadblade.algorithms.datastructure.util.IndexOutOfBoundsException;
import ru.dreadblade.algorithms.datastructure.util.Iterator;
import ru.dreadblade.algorithms.datastructure.util.MessageUtils;
import ru.dreadblade.algorithms.datastructure.util.NoSuchElementException;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 16;

    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[CAPACITY];
        size = 0;
    }

    public ArrayList(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
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
    public void add(T element) {
        if (size == data.length) {
            changeCapacity(data.length + CAPACITY);
        }

        data[size] = element;
        size++;
    }

    @Override
    public void add(int index, T element) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(MessageUtils.getIndexOutOfBoundsExceptionMessage(index, size));
        }

        if (size == data.length) {
            changeCapacity(data.length + CAPACITY);
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = element;
        size++;
    }

    @Override
    public T set(int index, T element) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(MessageUtils.getIndexOutOfBoundsExceptionMessage(index, size));
        }

        T replacedData = (T) data[index];

        data[index] = element;

        return replacedData;
    }

    @Override
    public T get(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(MessageUtils.getIndexOutOfBoundsExceptionMessage(index, size));
        }

        return (T) data[index];
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(data[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public T remove(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(MessageUtils.getIndexOutOfBoundsExceptionMessage(index, size));
        }

        if (size < data.length / 4) {
            changeCapacity(data.length / 2);
        }

        T removedData = (T) data[index];

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }

        size--;
        return removedData;
    }

    @Override
    public boolean remove(T element) {
        int index = indexOf(element);

        if (index >= 0) {
            remove(index);

            return true;
        }

        return false;
    }

    @Override
    public Iterator<T> listIterator() {
        return new ArrayListIterator(0);
    }

    @Override
    public Iterator<T> listIterator(int beginIndex) {
        if (!isPositionIndex(beginIndex)) {
            throw new IndexOutOfBoundsException(MessageUtils.getIndexOutOfBoundsExceptionMessage(beginIndex, size));
        }

        return new ArrayListIterator(beginIndex);
    }

    public int getCapacity() {
        return data.length;
    }

    private void changeCapacity(int capacity) {
        data = Arrays.copyOf(data, capacity);
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private class ArrayListIterator implements Iterator<T> {
        T lastReturnedValue;
        int currentIndex;

        public ArrayListIterator(int beginIndex) {
            currentIndex = beginIndex;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturnedValue = (T) data[currentIndex];
            currentIndex++;

            return lastReturnedValue;
        }

        @Override
        public void remove() {
            if (lastReturnedValue == null) {
                throw new NoSuchElementException();
            }

            if (size < data.length / 4) {
                changeCapacity(data.length / 2);
            }

            currentIndex--;
            for (int i = currentIndex + 1; i < size; i++) {
                data[i - 1] = data[i];
            }
            size--;
        }
    }
}

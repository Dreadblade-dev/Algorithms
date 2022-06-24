package ru.dreadblade.algorithms.datastructure.stack;

import ru.dreadblade.algorithms.datastructure.list.ArrayList;
import ru.dreadblade.algorithms.datastructure.util.NoSuchElementException;

public class ArrayListStack<T> implements Stack<T> {
    private ArrayList<T> list;

    public ArrayListStack() {
        list = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void push(T element) {
        list.add(element);
    }

    @Override
    public T pop() {
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }

        return list.remove(list.getSize() - 1);
    }

    @Override
    public T peek() {
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }

        return list.get(list.getSize() - 1);
    }
}

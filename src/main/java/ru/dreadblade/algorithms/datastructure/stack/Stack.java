package ru.dreadblade.algorithms.datastructure.stack;

public interface Stack<T> {
    int getSize();
    boolean isEmpty();
    void clear();
    void push(T element);
    T pop();
    T peek();
}

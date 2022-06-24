package ru.dreadblade.algorithms.datastructure.stack;

import ru.dreadblade.algorithms.datastructure.util.NoSuchElementException;

public class LinkedStack<T> implements Stack<T> {
    private Node<T> top;
    private int size;

    public LinkedStack() {
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
        top = null;
    }

    @Override
    public void push(T element) {
        Node<T> nodeToAdd = new Node<>();
        nodeToAdd.data = element;
        nodeToAdd.next = top;
        top = nodeToAdd;

        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T data = top.data;
        top = top.next;
        size--;

        return data;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return top.data;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;
    }
}

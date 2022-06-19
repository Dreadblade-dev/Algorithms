package ru.dreadblade.algorithms.datastructure.list;

import ru.dreadblade.algorithms.datastructure.util.IndexOutOfBoundsException;
import ru.dreadblade.algorithms.datastructure.util.*;

public class SinglyLinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

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
        head = null;
        tail = null;
    }

    @Override
    public void add(T element) {
        Node<T> nodeToAdd = new Node<>();
        nodeToAdd.data = element;

        if (head != null) {
            if (tail == head) {
                head.next = nodeToAdd;
            } else {
                tail.next = nodeToAdd;
            }
        } else {
            head = nodeToAdd;
        }

        tail = nodeToAdd;

        size++;
    }

    @Override
    public void add(int index, T element) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(MessageUtils.getIndexOutOfBoundsExceptionMessage(index, size));
        }

        if (index == size) {
            add(element);

            return;
        }

        Node<T> nodeToAdd = new Node<>();
        nodeToAdd.data = element;

        if (index == 0 && size > 0) {
            nodeToAdd.next = head;
            head = nodeToAdd;
            size++;

            return;
        }

        Node<T> previousNode = getNode(index - 1);

        nodeToAdd.next = previousNode.next;
        previousNode.next = nodeToAdd;

        size++;
    }

    @Override
    public T set(int index, T element) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(MessageUtils.getIndexOutOfBoundsExceptionMessage(index, size));
        }

        Node<T> node = getNode(index);

        T replacedData = node.data;
        node.data = element;

        return replacedData;
    }

    @Override
    public T get(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(MessageUtils.getIndexOutOfBoundsExceptionMessage(index, size));
        }

        return getNode(index).data;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (get(i).equals(element)) {
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

        if (index == 0) {
            T data = head.data;
            head = head.next;
            size--;

            return data;
        }

        Node<T> previousNode = getNode(index - 1);
        Node<T> currentNode = previousNode.next;

        T data = currentNode.data;
        previousNode.next = currentNode.next;

        if (index == size - 1) {
            tail = previousNode;
        }

        size--;
        return data;
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
        return new SinglyLinkedListIterator(0);
    }

    @Override
    public Iterator<T> listIterator(int beginIndex) {
        if (!isPositionIndex(beginIndex)) {
            throw new IndexOutOfBoundsException(MessageUtils.getIndexOutOfBoundsExceptionMessage(beginIndex, size));
        }

        return new SinglyLinkedListIterator(beginIndex);
    }

    /**
     * @param index an {@code int index} position of {@code T item}
     * @return {@code true} if {@code int index} is correct position (for an add() operation or for an iterator())
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * @param index an {@code int index} position of {@code T item}
     * @return {@code true} if {@code int index} is the index of an existing element
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private Node<T> getNode(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(MessageUtils.getIndexOutOfBoundsExceptionMessage(index, size));
        }

        if (index == 0) {
            return head;
        }

        if (index == size - 1) {
            return tail;
        }

        Node<T> currentNode = head;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode;
    }

    private class SinglyLinkedListIterator implements Iterator<T> {
        private Node<T> currentNode;
        private int currentIndex;

        public SinglyLinkedListIterator(int index) {
            currentNode = (index != size) ? getNode(index) : null;
            currentIndex = index;
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

            T data = currentNode.data;
            currentNode = currentNode.next;
            currentIndex++;

            return data;
        }

        @Override
        public void remove() {
            SinglyLinkedList.this.remove(currentIndex - 1);
        }
    }

    private static class Node<T> {
        private Node<T> next;
        private T data;
    }
}
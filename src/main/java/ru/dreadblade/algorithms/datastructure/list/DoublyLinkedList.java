package ru.dreadblade.algorithms.datastructure.list;

import ru.dreadblade.algorithms.datastructure.util.IndexOutOfBoundsException;
import ru.dreadblade.algorithms.datastructure.util.Iterator;
import ru.dreadblade.algorithms.datastructure.util.MessageUtils;
import ru.dreadblade.algorithms.datastructure.util.NoSuchElementException;

public class DoublyLinkedList<T> implements List<T> {
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
        tail = null;
        head = null;
        size = 0;
    }

    @Override
    public void add(T element) {
        Node<T> nodeToAdd = new Node<>();
        nodeToAdd.data = element;

        if (head != null) {
            if (tail == head) {
                head.next = nodeToAdd;
                nodeToAdd.previous = head;
            } else {
                tail.next = nodeToAdd;
                nodeToAdd.previous = tail;
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
        } else {
            Node<T> nodeToAdd = new Node<>();
            nodeToAdd.data = element;

            if (index > 0) {
                Node<T> nextNode = getNode(index);
                Node<T> previousNode = nextNode.previous;

                nodeToAdd.previous = previousNode;
                nodeToAdd.next = nextNode;

                previousNode.next = nodeToAdd;
                nextNode.previous = nodeToAdd;
            } else {
                nodeToAdd.next = head;
                head.previous = nodeToAdd;

                head = nodeToAdd;
            }
            size++;
        }
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

        if (index == 0) {
            return head.data;
        }

        if (index == size - 1) {
            return tail.data;
        }

        return getNode(index).data;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public int indexOf(T element) {
        Node<T> currentNode = head;

        for (int i = 0; i < size; i++) {
            if (element.equals(currentNode.data)) {
                return i;
            }

            currentNode = currentNode.next;
        }

        return -1;
    }

    @Override
    public T remove(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(MessageUtils.getIndexOutOfBoundsExceptionMessage(index, size));
        }

        Node<T> nodeToRemove = getNode(index);

        if (index > 0 && index < size - 1) {
            nodeToRemove.previous.next = nodeToRemove.next;
            nodeToRemove.next.previous = nodeToRemove.previous;
        } else if (index == 0) {
            if (index == size - 1) {
                head = null;
                tail = null;
            } else {
                head = nodeToRemove.next;

                if (tail != head) {
                    head.next.previous = head;
                }
            }
        } else {
            tail = nodeToRemove.previous;
            tail.next = null;
        }

        size--;

        return nodeToRemove.data;
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
        return new DoublyLinkedListIterator(0);
    }

    @Override
    public Iterator<T> listIterator(int beginIndex) {
        if (!isPositionIndex(beginIndex)) {
            throw new IndexOutOfBoundsException(MessageUtils.getIndexOutOfBoundsExceptionMessage(beginIndex, size));
        }

        return new DoublyLinkedListIterator(beginIndex);
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private Node<T> getNode(int index) {
        Node<T> currentNode = head;

        int count = 0;

        while (count < index) {
            currentNode = currentNode.next;

            count++;
        }

        return currentNode;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        Node<T> currentNode = head;

        while (currentNode != null) {
            stringBuilder.append(currentNode.data);

            if (currentNode != tail) {
                stringBuilder.append(", ");
            }

            currentNode = currentNode.next;
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private class DoublyLinkedListIterator implements Iterator<T> {
        private Node<T> currentNode;
        private int currentIndex;
        private Node<T> lastReturnedNode;

        public DoublyLinkedListIterator(int beginIndex) {
            currentNode = (beginIndex != size) ? getNode(beginIndex) : null;
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

            T data = currentNode.data;

            lastReturnedNode = currentNode;
            currentNode = currentNode.next;
            currentIndex++;

            return data;
        }

        @Override
        public void remove() {
            if (lastReturnedNode == null) {
                throw new NoSuchElementException();
            }

            if (lastReturnedNode != head && lastReturnedNode != tail) {
                currentNode.previous = lastReturnedNode.previous;
                lastReturnedNode.previous.next = currentNode;
            } else if (lastReturnedNode == head) {
                if (head != tail) {
                    head = head.next;
                    head.previous = null;
                } else {
                    head = null;
                }
            } else {
                tail = tail.previous;
                tail.next = null;
            }

            currentIndex--;
            size--;
        }
    }

    private static class Node<T> {
        private Node<T> previous;
        private Node<T> next;
        private T data;
    }
}

package ru.dreadblade.algorithms.datastructure.list;

import ru.dreadblade.algorithms.datastructure.util.Iterator;

public interface List<T> {
    int getSize();
    boolean isEmpty();
    void clear();
    void add(T element);
    void add(int index, T element);
    T set(int index, T element);
    T get(int index);
    boolean contains(T element);
    int indexOf(T element);
    T remove(int index);
    boolean remove(T element);
    Iterator<T> listIterator();
    Iterator<T> listIterator(int beginIndex);
}

package ru.dreadblade.algorithms.datastructure.util;

public class MessageUtils {
    public static String getIndexOutOfBoundsExceptionMessage(int index, int size) {
        return String.format("Index: %d; Size: %d", index, size);
    }
}

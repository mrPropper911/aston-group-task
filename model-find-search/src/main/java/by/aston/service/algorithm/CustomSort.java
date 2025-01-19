package by.aston.service.algorithm;

import by.aston.model.ObjectBuilder;

import java.util.Comparator;
import java.util.List;

public interface CustomSort {

    <T extends ObjectBuilder & Comparable<T>> void sort(List<T> list, Comparator<? super T> comparator);

    void sortWildcard(List<?> list);
}
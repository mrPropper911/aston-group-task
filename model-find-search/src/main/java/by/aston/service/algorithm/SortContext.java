package by.aston.service.algorithm;

import by.aston.model.ObjectBuilder;

import java.util.Comparator;
import java.util.List;

public class SortContext {
    private CustomSort customSort;

    public SortContext(CustomSort customSort) {
        this.customSort = customSort;
    }

    public void setSortStrategy(CustomSort customSort) {
        this.customSort = customSort;
    }

    /**
     * Сортировка с использованием компаратора
     *
     * @param list       список для сортировки
     * @param comparator компаратор требуемый для сравнения элементов
     * @throws IllegalArgumentException если список или компаратор равен null
     */
    public <T extends ObjectBuilder & Comparable<T>> void sort(List<T> list, Comparator<? super T> comparator){
        if (list == null || comparator == null) {
            throw new IllegalArgumentException("Список и компаратор не может быть пустым.");
        }
        customSort.sort(list, comparator);
    }

    /**
     * Сортировка с использованием wildcard (Comparable)
     *
     * @param list список для сортировки
     * @throws IllegalArgumentException если список равен null
     */
    public void sortWildcard(List<?> list){
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть пустым.");
        }
        customSort.sortWildcard(list);
    }
}
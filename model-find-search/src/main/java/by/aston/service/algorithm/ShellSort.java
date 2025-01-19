package by.aston.service.algorithm;

import by.aston.model.ObjectBuilder;

import java.util.Comparator;
import java.util.List;

public final class ShellSort implements CustomSort {

    /**
     * Сортировка с использованием естественного порядка (Comparable)
     *
     * @param list список для сортировки
     * @throws IllegalArgumentException если список равен null
     */
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть пустым.");
        }
        shellSort(list, Comparator.naturalOrder());
    }

    /**
     * Сортировка с использованием wildcard и естественного порядка (Comparable)
     *
     * @param list список для сортировки
     * @throws IllegalArgumentException если список равен null
     */
    public void sortWildcard(List<?> list){
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть пустым.");
        }

        @SuppressWarnings("unchecked")
        List<Comparable<Object>> comparableList = (List<Comparable<Object>>) list;
        sort(comparableList);
    }

    /**
     * Сортировка с использованием компаратора
     *
     * @param list       список для сортировки
     * @param comparator компаратор требуемый для сравнения элементов
     * @throws IllegalArgumentException если список или компаратор равен null
     */
    @Override
    public <T extends ObjectBuilder & Comparable<T>> void sort(List<T> list, Comparator<? super T> comparator) {
        if (list == null || comparator == null) {
            throw new IllegalArgumentException("Список и компаратор не может быть пустым.");
        }
        shellSort(list, comparator);
    }

    /**
     * Сортировка Шелла
     *
     * @param list       список для сортировки
     * @param comparator компаратор требуемый для сравнения элементов
     */
    private static <T> void shellSort(List<T> list, Comparator<? super T> comparator) {
        int length = list.size();

        if (length <= 1) {
            return;
        }

        for (int gap = length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < length; i++) {
                T temp = list.get(i);
                int j;

                for (j = i; j >= gap && comparator.compare(list.get(j - gap), temp) > 0; j -= gap) {
                    list.set(j, list.get(j - gap));
                }
                list.set(j, temp);
            }
        }
    }
}

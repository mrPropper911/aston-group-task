package by.aston.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomCollections {
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
        sort(list, Comparator.naturalOrder());
    }

    /**
     * Сортировка с использованием wildcard и естественного порядка (Comparable)
     *
     * @param list список для сортировки
     * @throws IllegalArgumentException если список равен null
     */
    public static void sortWildcard(List<?> list){
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
    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
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

    /**
     * Бинарный поиск
     *
     * @param list       список для поиска
     * @param key        элемент поиска
     * @param comparator компаратор требуемый для сравнения элементов
     * @return индекс элемента в списке, в случае если не найден -1
     * @throws IllegalArgumentException если список, элемент поиска или компаратор равен null
     */
    public static <T> int searchBinary(List<T> list,
                                       T key, Comparator<? super T> comparator) {
        if (list == null || key == null || comparator == null) {
            throw new IllegalArgumentException("List, key and comparator can not be null");
        }

        List<T> sortedList = new ArrayList<>(list);
        sort(sortedList, comparator);

        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            T midElement = list.get(mid);
            int cmp = comparator.compare(midElement, key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
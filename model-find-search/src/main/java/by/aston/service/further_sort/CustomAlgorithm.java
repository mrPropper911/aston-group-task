package by.aston.service.further_sort;

import by.aston.service.CustomCollections;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class CustomAlgorithm {
    /**
     * Сортировка с не естественным порядком, а именно:
     * классы с четными значениями сортируются в натуральном порядке, а с нечетными остаются на своих местах
     *
     * @param list список для сортировки
     * @throws IllegalArgumentException если список равен null
     */
    public static <T extends CompareValue & Comparable<T>> void naturalOrderForEvenSort(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("List can not be null");
        }

        List<T> listEven = list.stream()
                .filter(n -> n.getValue() % 2 == 0)
                .collect(Collectors.toList());

        Comparator<T> comparator = Comparator.comparingInt(T::getValue);

        CustomCollections.sort(listEven, comparator);

        int evenIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue() % 2 == 0) {
                list.set(i, listEven.get(evenIndex++));
            }
        }

    }
}


package by.aston.service;

import by.aston.model.ObjectBuilder;

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
    public static <T extends ObjectBuilder & Comparable<T>> void naturalOrderForEvenSort(List<T> list) {
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

    public static void naturalOrderForEvenSortWildcard(List<?> list) {
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть null.");
        }

        if (!list.isEmpty() && !(list.get(0) instanceof ObjectBuilder)) {
            throw new IllegalArgumentException("List elements must implement ObjectBuilder");
        }

        @SuppressWarnings("unchecked")
        List<ObjectBuilder> typedList = (List<ObjectBuilder>) list;

        List<ObjectBuilder> listEven = typedList.stream()
                .filter(n -> n.getValue() % 2 == 0)
                .collect(Collectors.toList());

        Comparator<ObjectBuilder> comparator = Comparator.comparingInt(ObjectBuilder::getValue);

        CustomCollections.sort(listEven, comparator);

        int evenIndex = 0;
        for (int i = 0; i < typedList.size(); i++) {
            if (typedList.get(i).getValue() % 2 == 0) {
                typedList.set(i, listEven.get(evenIndex++));
            }
        }
    }
}


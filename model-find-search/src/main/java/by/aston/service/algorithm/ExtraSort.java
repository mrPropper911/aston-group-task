package by.aston.service.algorithm;

import by.aston.model.ObjectBuilder;
import by.aston.service.CustomCollections;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ExtraSort implements CustomSort {
    /**
     * Сортировка с не естественным порядком, а именно:
     * классы с четными значениями сортируются в натуральном порядке, а с нечетными остаются на своих местах
     *
     * @param list список для сортировки
     * @throws IllegalArgumentException если список равен null
     */
    @Override
    public <T extends ObjectBuilder & Comparable<T>> void sort(List<T> list, Comparator<? super T> comparator) {
        if (list == null) {
            throw new IllegalArgumentException("List can not be null");
        }

        List<T> listEven = list.stream()
                .filter(n -> n.getValue() % 2 == 0)
                .collect(Collectors.toList());

        Comparator<T> comparatorExtra = Comparator.comparingInt(T::getValue);

        CustomCollections.sort(listEven, comparatorExtra);

        int evenIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue() % 2 == 0) {
                list.set(i, listEven.get(evenIndex++));
            }
        }
    }

    @Override
    public void sortWildcard(List<?> list) {
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

package by.aston.service.further_sort;

import by.aston.service.CustomCollections;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class NaturalOrderForEvenSort<T extends CompareValue & Comparable<T>> {

    public static <T extends CompareValue & Comparable<T>> void sort(List<T> list) {
        List<T> listEven = list.stream()
                .filter(n -> n
                        .getValue()
                        % 2 == 0)
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


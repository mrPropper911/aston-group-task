package by.aston.service.sorting;

import java.util.Comparator;
import java.util.List;

public final class ShellSorting<T> implements CustomSort<T> {
    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        int length = list.size();

        // Начинаем с большого интервала и уменьшаем его
        for (int gap = length / 2; gap > 0; gap /= 2) {
            // Выполняем сортировку вставками для текущего интервала
            for (int i = gap; i < length; i++) {
                T temp = list.get(i);
                list.remove(i);
                int j;

                // Сдвигаем элементы, которые находятся на расстоянии gap
                for (j = i; j >= gap && comparator.compare(list.get(j - gap), temp) > 0; j -= gap) {
                    list.add(j, list.get(j - gap));
                    list.remove(j - gap);
                }
                list.add(j, temp);
            }
        }
        return list;
    }

}

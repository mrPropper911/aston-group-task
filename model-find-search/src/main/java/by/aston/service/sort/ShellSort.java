package by.aston.service.sort;

import java.util.Comparator;
import java.util.List;

public final class ShellSort<T> implements CustomSort<T> {
    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        int length = list.size();

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
        return list;
    }

}

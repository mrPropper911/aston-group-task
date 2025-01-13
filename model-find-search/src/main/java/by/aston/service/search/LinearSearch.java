package by.aston.service.search;

import java.util.Comparator;
import java.util.List;

public class LinearSearch<T> implements CustomSearch<T> {
    private final Comparator<T> comparator;

    public LinearSearch(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int search(List<T> list, T key) {
        for (int i = 0; i < list.size(); i++) {
            T currentElement = list.get(i);
            if (comparator.compare(currentElement, key) == 0) {
                return i;
            }
        }
        return -1;
    }
}

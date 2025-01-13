package by.aston.service.search;

import java.util.Comparator;
import java.util.List;

public class BinarySearch<T> implements CustomSearch<T>{
    private final Comparator<T> comparator;

    public BinarySearch(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int search(List<T> list, T key) {

        //TODO заменить своей сортировкой
        list.sort(comparator);

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

package by.aston.service.sort;

import java.util.Comparator;
import java.util.List;

public class SortContext<T>{
    private CustomSort<T> customSort;

    public SortContext(CustomSort<T> customSort) {
        this.customSort = customSort;
    }

    public void setSortStrategy(CustomSort<T> customSort) {
        this.customSort = customSort;
    }

    public List<T> sort(List<T> list, Comparator<T> comparator){
        if (list == null || list.isEmpty()){
            throw new IllegalArgumentException("List cannot be null or empty");
        }

        return customSort.sort(list, comparator);
    }}

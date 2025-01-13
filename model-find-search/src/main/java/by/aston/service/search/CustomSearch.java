package by.aston.service.search;

import java.util.List;

public interface CustomSearch<T> {
    int search(List<T> list, T key);
}

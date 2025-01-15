package by.aston.service.sort;

import java.util.Comparator;
import java.util.List;

public interface CustomSort <T> {
    List<T> sort(List<T> list, Comparator<T> comparator);
}

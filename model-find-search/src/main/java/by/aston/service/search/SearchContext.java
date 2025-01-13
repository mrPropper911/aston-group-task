package by.aston.service.search;

import java.util.List;

public class SearchContext<T> {
    private CustomSearch<T> customSearch;

    public SearchContext(CustomSearch<T> customSearch) {
        this.customSearch = customSearch;
    }

    public void setSearchStrategy(CustomSearch<T> customSearch) {
        this.customSearch = customSearch;
    }

    public int search(List<T> list, T key){
        if (list == null || list.isEmpty()){
            throw new IllegalArgumentException("List cannot be null or empty");
        }

        return customSearch.search(list, key);
    }
}

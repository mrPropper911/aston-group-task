package by.aston.service.search;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchContextTest {

    @Test
    void testSearchWithMockStrategy(){
        CustomSearch<Integer> customSearch = Mockito.mock(CustomSearch.class);
        SearchContext<Integer> searchContext = new SearchContext<>(customSearch);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int key = 3;
        int expectedIndex = 2;

        Mockito.when(customSearch.search(list,key)).thenReturn(expectedIndex);
        int result = searchContext.search(list, key);

        assertEquals(expectedIndex, result);
        Mockito.verify(customSearch, Mockito.times(1)).search(list, key);
    }

    @Test
    void testChangeSearchStrategy(){
        CustomSearch<Integer> firstCustomSearch = Mockito.mock(CustomSearch.class);
        CustomSearch<Integer> secondCustomSearch = Mockito.mock(CustomSearch.class);
        SearchContext<Integer> searchContext = new SearchContext<>(firstCustomSearch);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int key = 3;
        int firstExpectedIndex = 2;
        int secondExpectedIndex = 3;

        Mockito.when(firstCustomSearch.search(list, key)).thenReturn(firstExpectedIndex);
        int firstResult = searchContext.search(list, key);
        assertEquals(firstExpectedIndex, firstResult);

        searchContext.setSearchStrategy(secondCustomSearch);

        Mockito.when(secondCustomSearch.search(list, key)).thenReturn(secondExpectedIndex);
        int secondResult = searchContext.search(list, key);
        assertEquals(secondExpectedIndex, secondResult);

        Mockito.verify(firstCustomSearch, Mockito.times(1)).search(list, key);
        Mockito.verify(secondCustomSearch, Mockito.times(1)).search(list, key);
    }

}
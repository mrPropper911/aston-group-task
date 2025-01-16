package by.aston.service.further_sort;

import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.service.CustomCollections;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class NaturalOrderForEvenSortTest {
    private static List<Book> list = new ArrayList<>();

    @BeforeEach
    void generateTestValue() {
        list = Arrays.asList(
                new Book.Builder().numberPages(501).author("A").title("A").build(),
                new Book.Builder().numberPages(200).author("B").title("B").build(),
                new Book.Builder().numberPages(800).author("C").title("C").build(),
                new Book.Builder().numberPages(101).author("D").title("D").build(),
                new Book.Builder().numberPages(400).author("E").title("E").build(),
                new Book.Builder().numberPages(701).author("F").title("F").build(),
                new Book.Builder().numberPages(600).author("G").title("G").build(),
                new Book.Builder().numberPages(301).author("H").title("H").build(),
                new Book.Builder().numberPages(1000).author("I").title("I").build(),
                new Book.Builder().numberPages(901).author("J").title("J").build()
        );
    }

    @Test
    void testSortingEmptyList() {
        List<Book> emptyList = new ArrayList<>();
        NaturalOrderForEvenSort.sort(emptyList);
        assertThat(emptyList).isEmpty();
    }

    @Test
    void testSortingSingleElementList() {
        List<Book> singleElementList = Collections.singletonList(
                new Book.Builder().numberPages(200).author("B").title("B").build()
        );
        CustomCollections.sort(singleElementList);
        assertThat(singleElementList).hasSize(1);
    }



    @Test
    void testOfExpectedElements() {
        List<Book> listBeforeSorting = new ArrayList<>(list);
        NaturalOrderForEvenSort.sort(list);
        assertThat(list)
                .hasSize(10)
                .containsExactly(listBeforeSorting.get(0), listBeforeSorting.get(1), listBeforeSorting.get(4)
                        , listBeforeSorting.get(3), listBeforeSorting.get(6), listBeforeSorting.get(5)
                        , listBeforeSorting.get(2), listBeforeSorting.get(7), listBeforeSorting.get(8)
                        , listBeforeSorting.get(9))
                .doesNotHaveDuplicates();
    }
}

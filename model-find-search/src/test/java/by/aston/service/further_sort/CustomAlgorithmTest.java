package by.aston.service.further_sort;

import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.service.CustomCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomAlgorithmTest {
    private static List<Book> list = new ArrayList<>();

    @BeforeEach
    void generateTestValue() {
        list = Arrays.asList(
                new Book.Builder().numberPages(501).author("A").title("A").build(),
                new Book.Builder().numberPages(800).author("B").title("B").build(),
                new Book.Builder().numberPages(200).author("C").title("C").build(),
                new Book.Builder().numberPages(101).author("D").title("D").build(),
                new Book.Builder().numberPages(400).author("E").title("E").build(),
                new Book.Builder().numberPages(701).author("F").title("F").build()
        );
    }

    @Test
    void testSortingEmptyList() {
        List<Book> emptyList = new ArrayList<>();
        CustomAlgorithm.naturalOrderForEvenSort(emptyList);
        assertThat(emptyList).isEmpty();
    }

    @Test
    void testSortingSingleElementList() {
        List<Book> singleElementList = Collections.singletonList(
                new Book.Builder().numberPages(200).author("С").title("С").build()
        );
        CustomAlgorithm.naturalOrderForEvenSort(singleElementList);
        assertThat(singleElementList).hasSize(1);
    }

    @Test
    void testSortNullList() {
        List<Book> nullList = null;
        try {
            CustomCollections.sort(nullList);
        } catch (IllegalArgumentException ignored) {
        }
        assertThat(nullList).isNull();
    }

    @Test
    void testOfExpectedElements() {
        CustomAlgorithm.naturalOrderForEvenSort(list);

        List<Book> expectedBookList = Arrays.asList(
                new Book.Builder().numberPages(501).author("A").title("A").build(),
                new Book.Builder().numberPages(200).author("C").title("C").build(),
                new Book.Builder().numberPages(400).author("E").title("E").build(),
                new Book.Builder().numberPages(101).author("D").title("D").build(),
                new Book.Builder().numberPages(800).author("B").title("B").build(),
                new Book.Builder().numberPages(701).author("F").title("F").build()
        );

        assertThat(list).containsExactlyElementsOf(expectedBookList);

    }
}

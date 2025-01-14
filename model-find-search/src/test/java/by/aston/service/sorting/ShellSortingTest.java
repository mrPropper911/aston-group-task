package by.aston.service.sorting;

import by.aston.model.Book;
import by.aston.model.MainCollection;
import by.aston.view.Sorting;
import org.junit.jupiter.api.*;

import java.util.*;

public class ShellSortingTest {
    private static List<Book> list = new ArrayList<>();

    @BeforeAll
    static void generateTestValue() {
        list.add(new Book("Герой нашего времени", "М. Ю. Лермонтов", 133));
        list.add(new Book("Война и мир", "Л. Н. Толстой", 867));
        list.add(new Book("Преступление и наказание", "Ф. М. Достоевский", 266));
        list.add(new Book("Евгений Онегин", "А. С. Пушкин", 146));
        list.add(new Book("Идиот", "Ф. М. Достоевский", 498));
    }

    @Disabled
    @Test
    @DisplayName("запуск окна сортировки для проверки работы всего функционала")
    void enterManualProperties() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Сортировать коллекцию");
            int scannerChoice = scanner.nextInt();
            switch (scannerChoice) {
                case 1:
                    Sorting.sortingProcess();
                    break;
            }
        }
    }

    @Test
    @DisplayName("проверка сортировки")
    void sorting() {
        CustomSort<Book> customSort = new ShellSorting<>();
        Comparator<Book> comparator = Comparator.comparingInt(book -> ((Book) book).getNumberOfPages());
        customSort.sort(list, comparator);
        for (Book b : list) {
            System.out.println(b);
        }
    }

    @BeforeEach
    @DisplayName("очистка рабочей коллекции")
    void clearMainCollection() {
        MainCollection.clearMainList();
    }

}

package by.aston.service.sort;

import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.service.search.SearchContext;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

public class ShellSortingTest {
    private static List<Car> list = new ArrayList<>();
    private static Comparator<Car> carComparator = Comparator.comparingInt(car -> ((Car) car).getYearRelease());

    @BeforeAll
    static void generateTestValue() {
        Car car1 = new Car(100, "Audi", 2006); list.add(car1);
        Car car2 = new Car(200, "BMW", 2007); list.add(car2);
        Car car3 = new Car(200, "BMW", 2007); list.add(car3);
        Car car4 = new Car(400, "Toyota", 2010); list.add(car4);
        Car car5 = new Car(500, "Mercedes", 2011); list.add(car5);
    }


    @Test
    @DisplayName("сравнение результатов двух сортировок")
    void testSortingForCorrectness() {
        SortContext<Car> sortContext = new SortContext<>(new ShellSort<>());
        List<Car> listShellSort = new ArrayList<>(list);
        List<Car> listSort = new ArrayList<>(list);

        sortContext.sort(listShellSort, carComparator);
        listSort.sort(carComparator);


        for (int i = 0; i < list.size(); i++) {
            listShellSort.get(i).equals(listSort.get(i));
        }

    }

    @Test
    @DisplayName("проверка на наличие ожидаемых значений после сортировки")
    void assertThatListContainsExpectedValues(){
        SortContext<Car> sortContext = new SortContext<>(new ShellSort<>());
        List<Car> listShellSort = new ArrayList<>(list);

        sortContext.sort(listShellSort, carComparator);

        assertThat(listShellSort)
                .hasSize(5)
                .containsExactly(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4))
                .doesNotHaveDuplicates();
    }

}

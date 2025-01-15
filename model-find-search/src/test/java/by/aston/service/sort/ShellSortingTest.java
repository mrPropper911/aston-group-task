package by.aston.service.sort;

import by.aston.model.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShellSortingTest {
    private static List<Car> list = new ArrayList<>();

    @BeforeAll
    static void generateTestValue() {
        list = Arrays.asList(
                new Car.Builder().power(700).model("Audi").yearRelease(2009).build(),
                new Car.Builder().power(200).model("BMW").yearRelease(2007).build(),
                new Car.Builder().power(300).model("Mercedes").yearRelease(2008).build()
        );
    }

    @Test
    @DisplayName("сравнение результатов двух сортировок")
    void testSortingForCorrectness() {
        SortContext<Car> sortContext = new SortContext<>(new ShellSort<>());
        Comparator<Car> carComparator = Comparator.comparingInt(Car::getYearRelease)
                .thenComparing(Car::getModel);

        List<Car> expectedCatList = Arrays.asList(
                new Car.Builder().power(200).model("BMW").yearRelease(2007).build(),
                new Car.Builder().power(300).model("Mercedes").yearRelease(2008).build(),
                new Car.Builder().power(700).model("Audi").yearRelease(2009).build()
        );
        List<Car> actualCatList = sortContext.sort(list, carComparator);

        assertThat(actualCatList).containsExactlyElementsOf(expectedCatList);
    }

    //TODO исправить тест
//    @Test
//    @DisplayName("проверка на наличие ожидаемых значений после сортировки")
//    void assertThatListContainsExpectedValues() {
//        SortContext<Car> sortContext = new SortContext<>(new ShellSort<>());
//        List<Car> listShellSort = new ArrayList<>(list);
//
//        sortContext.sort(listShellSort, carComparator);
//
//        assertThat(listShellSort)
//                .hasSize(5)
//                .containsExactly(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4))
//                .doesNotHaveDuplicates();
//    }

}

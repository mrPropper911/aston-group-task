package by.aston.service.search;

import by.aston.model.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LinearSearchTest {
    private static List<Car> list = new ArrayList<>();

    @BeforeAll
    static void generateTestValue() {
        list = Arrays.asList(
                new Car(100, "Audi", 2006),
                new Car(200, "BMW", 2007),
                new Car(300, "Mercedes", 2009),
                new Car(400, "Toyota", 2010),
                new Car(500, "Mercedes", 2011)
        );
    }

    @Test
    void testSearchExistingElement() {
        Comparator<Car> powerComparator = Comparator.comparingInt(Car::getPower);
        LinearSearch<Car> linearSearch = new LinearSearch<>(powerComparator);

        Car existingCar = new Car(200, "", 1);
        int index = linearSearch.search(list, existingCar);

        assertThat(index).isEqualTo(1);
    }

    @Test
    void testSearchNonExistingElement() {
        Comparator<Car> yearComparator = Comparator.comparingInt(Car::getYearRelease);
        BinarySearch<Car> binarySearch = new BinarySearch<>(yearComparator);

        Car notExistingCar = new Car(1, "", 1);
        int index = binarySearch.search(list, notExistingCar);

        assertThat(index).isEqualTo(-1);
    }

    //TODO добавить ещё тесты
}
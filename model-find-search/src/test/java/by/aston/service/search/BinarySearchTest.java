package by.aston.service.search;

import by.aston.model.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BinarySearchTest {
    private static List<Car> list = new ArrayList<>();

    @BeforeAll
    static void generateTestValue() {
        list = Arrays.asList(
                new Car.Builder().power(100).model("Audi").yearRelease(2006).build();
                new Car.Builder().power(200).model("BMW").yearRelease(2007).build();
                new Car.Builder().power(300).model("Mercedes").yearRelease(2009).build();
                new Car.Builder().power(400).model("Toyota").yearRelease(2010).build();
                new Car.Builder().power(500).model("Mercedes".yearRelease(2011).build();
        );
    }

    @Test
    void testSearchExistingElement() {
        Comparator<Car> powerComparator = Comparator.comparingInt(Car::getPower);
        BinarySearch<Car> binarySearch = new BinarySearch<>(powerComparator);

        Car existingCar = new Car(200, "", 1);
        int index = binarySearch.search(list, existingCar);

        assertThat(index).isEqualTo(1);
    }

    @Test
    void testSearchExistingElementWithAllFields() {
        Comparator<Car> powerComparator = Comparator.comparingInt(Car::getPower)
                .thenComparing(Car::getModel)
                .thenComparing(Car::getYearRelease);
        BinarySearch<Car> binarySearch = new BinarySearch<>(powerComparator);

        Car existingCar = new Car(300, "Mercedes", 2009);
        int index = binarySearch.search(list, existingCar);

        assertThat(index).isEqualTo(2);

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
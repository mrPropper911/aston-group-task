package by.aston.service;

import by.aston.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class CustomCollectionsTest {
    private static List<Car> list = new ArrayList<>();

    @BeforeEach
    void generateTestValue() {
        list = Arrays.asList(
                new Car.Builder().power(700).model("Audi").yearRelease(2009).build(),
                new Car.Builder().power(200).model("BMW").yearRelease(2007).build(),
                new Car.Builder().power(300).model("Mercedes").yearRelease(2008).build()
        );
    }

    @Test
    void testSortingWithNonComparator() {
        CustomCollections.sort(list);

        List<Car> expectedCarList = Arrays.asList(
                new Car.Builder().power(700).model("Audi").yearRelease(2009).build(),
                new Car.Builder().power(200).model("BMW").yearRelease(2007).build(),
                new Car.Builder().power(300).model("Mercedes").yearRelease(2008).build()
        );

        assertThat(list).containsExactlyElementsOf(expectedCarList);
    }

    @Test
    void testSortingWithComparator() {
        Comparator<Car> carComparator = Comparator
                .comparingInt(Car::getYearRelease)
                .thenComparing(Car::getModel);

        CustomCollections.sort(list, carComparator);

        List<Car> expectedCarList = Arrays.asList(
                new Car.Builder().power(200).model("BMW").yearRelease(2007).build(),
                new Car.Builder().power(300).model("Mercedes").yearRelease(2008).build(),
                new Car.Builder().power(700).model("Audi").yearRelease(2009).build()
        );

        assertThat(list).containsExactlyElementsOf(expectedCarList);
    }

    @Test
    void testSortingEmptyList() {
        List<Car> emptyList = new ArrayList<>();
        CustomCollections.sort(emptyList);
        assertThat(emptyList).isEmpty();
    }

    @Test
    void testSortingSingleElementList() {
        List<Car> singleElementList = Collections.singletonList(
                new Car.Builder().power(200).model("BMW").yearRelease(2007).build()
        );
        CustomCollections.sort(singleElementList);
        assertThat(singleElementList).hasSize(1);
    }

    @Test
    void testSortingWithManyElementList() {
        int size = 10_000;
        List<Integer> muchElementsForShell = new ArrayList<>(size);
        List<Integer> muchElementsForDefSort = new ArrayList<>(size);

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            muchElementsForShell.add(random.nextInt(100));
        }
        muchElementsForDefSort.addAll(muchElementsForShell);

        CustomCollections.sort(muchElementsForShell);
        Collections.sort(muchElementsForDefSort);

        assertThat(muchElementsForShell).hasSameElementsAs(muchElementsForDefSort);
    }

    @Test
    void testSearchExistingElement() {
        Comparator<Car> powerComparator = Comparator.comparingInt(Car::getPower);
        Car existingCar = new Car.Builder().power(200).model("").yearRelease(1).build();

        int index = CustomCollections.searchBinary(list, existingCar, powerComparator);

        assertThat(index).isEqualTo(1);
    }

    @Test
    void testSearchExistingElementWithAllFields() {
        Comparator<Car> powerComparator = Comparator.comparingInt(Car::getPower)
                .thenComparing(Car::getModel)
                .thenComparing(Car::getYearRelease);
        Car existingCar = new Car.Builder().power(300).model("Mercedes").yearRelease(2008).build();

        int index = CustomCollections.searchBinary(list, existingCar, powerComparator);

        assertThat(index).isEqualTo(2);
    }

    @Test
    void testSearchNonExistingElement() {
        Comparator<Car> yearComparator = Comparator.comparingInt(Car::getYearRelease);

        Car notExistingCar = new Car.Builder().power(1).model("").yearRelease(1).build();
        int index = CustomCollections.searchBinary(list, notExistingCar, yearComparator);

        assertThat(index).isEqualTo(-1);
    }
}
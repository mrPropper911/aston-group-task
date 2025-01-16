package by.aston.view;

import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.model.Vegetable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomObjectGenerator {

    private static final Random RANDOM = new Random();

    public static <T> List<T> generateCollection(Class<T> clazz, int count){
        List<T> collection = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            try{
                if (clazz == Car.class) {
                    Car car = new Car.Builder()
                            .model("Модель автомобиля" + (i + 1))
                            .power(RANDOM.nextInt(300) + 100) // Мощность от 100 до 400
                            .yearRelease(RANDOM.nextInt(30) + 1990) // Год от 1990 до 2020
                            .build();
                    collection.add(clazz.cast(car));
                } else if (clazz == Book.class) {
                    Book book = new Book.Builder()
                            .author("Автор" + (i + 1))
                            .title("Название" + (i + 1))
                            .numberPages(RANDOM.nextInt(500) + 100) // Количество страниц от 100 до 600
                            .build();
                    collection.add(clazz.cast(book));
                } else if (clazz == Vegetable.class) {
                    Vegetable vegetable = new Vegetable.Builder()
                            .type("Тип корнеплода" + (i + 1))
                            .weight(RANDOM.nextDouble() * 10) // Вес от 0 до 10
                            .color("Цвет" + (i + 1))
                            .build();
                    collection.add(clazz.cast(vegetable));
                } else {
                    throw new IllegalArgumentException("Неверный тип объекта: " + clazz.getName());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка при создании объекта: " + e.getMessage());
                i--; // Уменьшаем счетчик, чтобы повторить ввод для этого объекта
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }
        return collection;
    }

    public static <T> List<T> selectRandomObjects(List<T> collection, int count) {
        List<T> selectedObjects = new ArrayList<>();
        List<Integer> randomIndices = new ArrayList<>();
        int size = collection.size();

        // Генерация уникальных случайных индексов
        while (randomIndices.size() < count) {
            int randomIndex = RANDOM.nextInt(size);
            if (!randomIndices.contains(randomIndex)) {
                randomIndices.add(randomIndex);
                selectedObjects.add(collection.get(randomIndex));
            }
        }
        return selectedObjects;
    }
}
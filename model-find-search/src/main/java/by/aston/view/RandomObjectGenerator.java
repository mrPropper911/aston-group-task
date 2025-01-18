package by.aston.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.model.Vegetable;

public class RandomObjectGenerator {

    private static final Random random = new Random();

    public static <T> List<T> getObjectList(Class<T> clazz, int count){
        List<T> collection = new ArrayList<>();

        List<String> PATH_BOOK_AUTHORS = loadDataFromFile("src/resources/names.txt");
        List<String> PATH_BOOK_TITLES = loadDataFromFile("src/resources/titles.txt");
        List<String> PATH_CAR_MODELS = loadDataFromFile("src/resources/models.txt");
        List<String> PATH_CAR_COLORS = loadDataFromFile("src/resources/colors.txt");
        List<String> vegetables = loadDataFromFile("src/resources/vegetables.txt");

            try{
                if (clazz == Car.class) {
                    String randomModel = PATH_CAR_MODELS.get(random.nextInt(PATH_CAR_MODELS.size()));
                    Car car = new Car.Builder()
                            .model(randomModel)
                            .power(random.nextInt(300) + 100) // Мощность от 100 до 400
                            .yearRelease(random.nextInt(30) + 1990) // Год от 1990 до 2020
                            .build();
                    collection.add(clazz.cast(car));
                } else if (clazz == Book.class) {
                    String randomAuthor = PATH_BOOK_AUTHORS.get(random.nextInt(PATH_BOOK_AUTHORS.size())); // Случайный автор из файла
                    String randomTitle = PATH_BOOK_TITLES.get(random.nextInt(PATH_BOOK_TITLES.size()));
                    Book book = new Book.Builder()
                            .author(randomAuthor)
                            .title(randomTitle)
                            .numberPages(random.nextInt(500) + 100) // Количество страниц от 100 до 600
                            .build();
                    collection.add(clazz.cast(book));
                } else if (clazz == Vegetable.class) {
                    String randomColor = PATH_CAR_COLORS.get(random.nextInt(PATH_CAR_COLORS.size()));
                    String randomVegetables = vegetables.get(random.nextInt(vegetables.size()));
                    Vegetable vegetable = new Vegetable.Builder()
                            .type(randomVegetables)
                            .weight(random.nextDouble() * 10) // Вес от 0 до 10
                            .color(randomColor)
                            .build();
                    collection.add(clazz.cast(vegetable));
                } else {
                    throw new IllegalArgumentException("Неверный тип объекта: " + clazz.getName());
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        return collection;
    }

    private static List<String> loadDataFromFile(String filePath) {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return data;
    }
}

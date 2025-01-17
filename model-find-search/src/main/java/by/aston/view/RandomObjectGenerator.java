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

    private static final Random RANDOM = new Random();

    public static <T> List<T> generateCollection(Class<T> clazz, int count){
        List<T> collection = new ArrayList<>();
        List<String> names = loadDataFromFile("src/resources/names.txt"); // Путь к файлу с именами
        List<String> titles = loadDataFromFile("src/resources/titles.txt");
        List<String> models = loadDataFromFile("src/resources/models.txt");
        List<String> colors = loadDataFromFile("src/resources/colors.txt");
        List<String> vegetables = loadDataFromFile("src/resources/vegetables.txt");

            try{
                if (clazz == Car.class) {
                    String randomModel = models.get(RANDOM.nextInt(models.size()));
                    Car car = new Car.Builder()
                            .model(randomModel)
                            .power(RANDOM.nextInt(300) + 100) // Мощность от 100 до 400
                            .yearRelease(RANDOM.nextInt(30) + 1990) // Год от 1990 до 2020
                            .build();
                    collection.add(clazz.cast(car));
                } else if (clazz == Book.class) {
                    String randomAuthor = names.get(RANDOM.nextInt(names.size())); // Случайный автор из файла
                    String randomTitle = titles.get(RANDOM.nextInt(titles.size()));
                    Book book = new Book.Builder()
                            .author(randomAuthor)
                            .title(randomTitle)
                            .numberPages(RANDOM.nextInt(500) + 100) // Количество страниц от 100 до 600
                            .build();
                    collection.add(clazz.cast(book));
                } else if (clazz == Vegetable.class) {
                    String randomColor = colors.get(RANDOM.nextInt(colors.size()));
                    String randomVegetables = vegetables.get(RANDOM.nextInt(vegetables.size()));
                    Vegetable vegetable = new Vegetable.Builder()
                            .type(randomVegetables)
                            .weight(RANDOM.nextDouble() * 10) // Вес от 0 до 10
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

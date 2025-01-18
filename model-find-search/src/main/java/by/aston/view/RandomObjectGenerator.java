package by.aston.view;

import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.model.Vegetable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomObjectGenerator {

    private static final Random random = new Random();
    private final static String PATH_BOOK_AUTHORS = "randomazers/names.txt";
    private final static String PATH_BOOK_TITLES = "randomazers/titles.txt";
    private final static String PATH_CAR_MODELS = "randomazers/models.txt";
    private final static String PATH_CAR_COLORS = "randomazers/colors.txt";
    private final static String PATH_VEGETABLES_NAME = "randomazers/vegetables.txt";

    public static <T> List<T> getObjectList(Class<T> clazz, int count) {
        List<T> collection = new ArrayList<>();

        if (clazz == Car.class) {
            List<String> stringModelList = getRandomObjectsByCount(PATH_CAR_MODELS, count);
            for (var i = 0; i < count; i++) {
                Car car = new Car.Builder()
                        .model(stringModelList.get(i))
                        .power(random.nextInt(300) + 100)
                        .yearRelease(random.nextInt(100) + 1886)
                        .build();
                collection.add(clazz.cast(car));
            }
        } else if (clazz == Book.class) {
            List<String> randomAuthorList = getRandomObjectsByCount(PATH_BOOK_AUTHORS, count);
            List<String> randomTitleList = getRandomObjectsByCount(PATH_BOOK_TITLES, count);
            for (var i = 0; i < count; i++) {
                Book book = new Book.Builder()
                        .author(randomAuthorList.get(i))
                        .title(randomTitleList.get(i))
                        .numberPages(random.nextInt(500) + 100)
                        .build();
                collection.add(clazz.cast(book));
            }
        } else if (clazz == Vegetable.class) {
            List<String> randomNamesList = getRandomObjectsByCount(PATH_VEGETABLES_NAME, count);
            List<String> randomColorsList = getRandomObjectsByCount(PATH_CAR_COLORS, count);
            for (var i = 0; i < count; i++) {
                Vegetable vegetable = new Vegetable.Builder()
                        .type(randomNamesList.get(i))
                        .weight(random.nextDouble() * 10)
                        .color(randomColorsList.get(i))
                        .build();
                collection.add(clazz.cast(vegetable));
            }
        }
        return collection;
    }

    private static List<String> getRandomObjectsByCount(String filePath, int count) {
        List<String> stringList = loadDataForRandom(filePath);
        return random.ints(0, stringList.size())
                .limit(count)
                .mapToObj(stringList::get)
                .collect(Collectors.toList());
    }

    private static List<String> loadDataForRandom(String filePath) {
        InputStream resourceAsStream = RandomObjectGenerator.class.getClassLoader().getResourceAsStream(filePath);

        if (resourceAsStream == null) {
            return Collections.emptyList();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            return bufferedReader.lines()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}

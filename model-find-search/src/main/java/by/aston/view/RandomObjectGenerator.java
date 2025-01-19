package by.aston.view;

import by.aston.model.ObjectBuilder;
import by.aston.model.ObjectBuilderFactory;

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
    public static <T> List<T> getObjectList(Class<T> clazz, int count) {
        List<T> collection = new ArrayList<>();
        ObjectBuilderFactory factory = new ObjectBuilderFactory();
        ObjectBuilder<T> objectBuilder = factory.getFactory(clazz);

        for (var i = 0; i < count; i++) {
            collection.add(objectBuilder.createRandomObject());
        }
        return collection;
    }

    public static List<String> getRandomObjectsByCount(String filePath, int count) {
        var random = new Random();
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

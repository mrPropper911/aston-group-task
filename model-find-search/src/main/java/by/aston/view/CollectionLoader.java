package by.aston.view;

import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.model.Vegetable;
import by.aston.utils.NumberUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CollectionLoader {
    private final static int USER_INPUT_NUMBER_ERROR = -1;
    private final DataInput input;

    public CollectionLoader(DataInput input) {
        this.input = input;
    }

    public List<?> getObjectList() {
        while (true) {
            input.showMessage("""
                    \nВыберите действие:
                    1. Создать коллекцию объектов вручную.
                    2. Сгенерировать коллекцию случайных объектов.
                    3. Считать коллекцию из файла.
                    4. Выход.
                    """);
            try {
                var choice = NumberUtils.parseInt(input.readLine());

                switch (choice) {
                    case 1 -> {
                        var keyboardInput = new KeyboardInput(input);
                        return keyboardInput.getObjectList();
                    }
                    case 2 -> {
                        return generateRandomCollection();
                    }
                    case 3 -> {
                        return readCollectionFromFile();
                    }
                    case 4 -> {
                        return Collections.emptyList();
                    }
                    default -> input.showMessage("Неверный выбор. Пожалуйста, попробуйте снова.");
                }
            } catch (NumberFormatException exception) {
                input.showErrorMessage("Ошибка формата числа при выборе действия:" +
                        exception.getMessage());
            }
        }
    }

    private List<?> generateRandomCollection() {
        try {
            input.showMessage("Введите количество элементов для генерации: ");
            var userCountChoice = NumberUtils.parseInt(input.readLine());
            var userObjectChoice = userCollectionChoice();

            switch (userObjectChoice) {
                case 1 -> {
                    return generateAndDisplayCollection(Car.class, userCountChoice);
                }
                case 2 -> {
                    return generateAndDisplayCollection(Book.class, userCountChoice);
                }
                case 3 -> {
                    return generateAndDisplayCollection(Vegetable.class, userCountChoice);
                }
                default -> {
                    input.showErrorMessage("Неверный выбор типа коллекции.");
                    return Collections.emptyList();
                }
            }
        } catch (NumberFormatException exception) {
            input.showErrorMessage("Ошибка формата числа при выборе количество элементов:" +
                    exception.getMessage());
        }
        return Collections.emptyList();
    }

    private List<?> readCollectionFromFile() {
        input.showMessage("Введите путь к файлу для чтения: ");
        var readFilePath = input.readLine();
        var userObjectChoice = userCollectionChoice();

        switch (userObjectChoice) {
            case 1 -> {
                return readAndDisplayCollection(readFilePath, Car.class);
            }
            case 2 -> {
                return readAndDisplayCollection(readFilePath, Book.class);
            }
            case 3 -> {
                return readAndDisplayCollection(readFilePath, Vegetable.class);
            }
            default -> {
                input.showErrorMessage("Неверный выбор типа коллекции.");
                return Collections.emptyList();
            }
        }
    }

    private <T> List<T> generateAndDisplayCollection(Class<T> clazz, int objectCount) {
        List<T> collection = RandomObjectGenerator.getObjectList(clazz, objectCount);
        input.showMessage("Случайно сгенерированная коллекция: ");
        collection.forEach(data -> input.showMessage(data.toString()));
        return collection;
    }

    private <T> List<T> readAndDisplayCollection(String filePath, Class<T> clazz) {
        try {
            var collection = FileHandler.readCollectionFromFile(filePath, clazz);
            input.showMessage("Считанная из файла коллекция: ");
            collection.forEach(data -> input.showMessage(data.toString()));
            return collection;
        } catch (IOException | ClassNotFoundException exception) {
            input.showErrorMessage("При загрузки коллекции возникла ошибка: " +
                    exception.getMessage());
            return Collections.emptyList();
        }
    }

    private int userCollectionChoice() {
        try {
            input.showMessage("""
                    \nВыберите тип коллекции для чтения:
                    1. Автомобили
                    2. Книги
                    3. Корнеплоды
                    """);
            return NumberUtils.parseInt(input.readLine());
        } catch (NumberFormatException exception) {
            input.showErrorMessage("Ошибка формата числа при выборе коллекции: " +
                    exception.getMessage());
            return USER_INPUT_NUMBER_ERROR;
        }
    }
}

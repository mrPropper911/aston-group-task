package by.aston.view;

import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.model.Vegetable;
import by.aston.utils.NumberUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CollectionLoader {

    private final DataInput input;

    public CollectionLoader(DataInput input) {
        this.input = input;
    }

    public List<?> getObjectList() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();

        while (true) {
            //todo записать это метод inputOfData?
            input.showMessage("""
                    Выберите действие:
                    1. Создать коллекцию объектов вручную.
                    2. Сгенерировать коллекцию случайных объектов.
                    3. Считать коллекцию из файла.
                    4. Выход.
                    """);

            int choice = NumberUtils.parseInt(input.readLine());//todo try везде

            switch (choice) {
                case 1 -> {
                    var keyboardInput = new KeyboardInput(input);
                    return keyboardInput.getObjectList();
                }
                case 2 -> {
                    return generateRandomCollection(randomObjectGenerator);//todo
                }
                case 3 -> {
                    return readCollectionFromFile();
                }
                case 5 -> {
                    return Collections.emptyList();
                }
                default -> input.showMessage("Неверный выбор. Пожалуйста, попробуйте снова.");
            }


//            try {
//                switch (choice) {
//                    case 1:
//                        keyboardInput.userDialogInit();
//                        break;
//                    case 2:
//                        generateRandomCollection(randomObjectGenerator);
//                        break;
//
//                    case 3:
//                        readCollectionFromFile(fileHandler, keyboardInput);
//                        break;
//
//                    case 4:
//                        writeCollectionToFile(fileHandler, keyboardInput, carCollection, bookCollection, vegetableCollection);
//                        break;
//
//                    case 5:
//                        input.showMessage("Выход из программы.");
//                        return;
//
//                    default:
//                        input.showMessage("Неверный выбор. Пожалуйста, попробуйте снова.");
//                }
//            } catch (Exception e) {
//                input.showMessage("Произошла ошибка: " + e.getMessage());
//            }
        }
    }

    private <T> List<T> generateRandomCollection(RandomObjectGenerator randomObjectGenerator) {
        try {
            input.showMessage("""
                    Выберите тип коллекции для генерации:
                    1. Автомобили
                    2. Книги
                    3. Корнеплоды
                    """);
            int userObjectChoice = NumberUtils.parseInt(input.readLine());

            input.showMessage("Введите количество элементов для генерации: ");
            int userCountOfObjectChoice = NumberUtils.parseInt(input.readLine());

            List<?> randomCollection = null;

            switch (userObjectChoice) {
                case 1:
                    randomCollection = randomObjectGenerator.getObjectList(Car.class, userCountOfObjectChoice);
                    input.showMessage("Сгенерированные автомобили: " + randomCollection);
                    break;
                case 2:
                    randomCollection = randomObjectGenerator.getObjectList(Book.class, userCountOfObjectChoice);
                    input.showMessage("Сгенерированные книги: " + randomCollection);
                    break;
                case 3:
                    randomCollection = randomObjectGenerator.getObjectList(Vegetable.class, userCountOfObjectChoice);
                    input.showMessage("Сгенерированные корнеплоды: " + randomCollection);
                    break;
                default:
                    input.showMessage("Неверный тип объекта.");
                    break;
            }
            //todo
//            return randomCollection;
        } catch (NumberFormatException exception) {
            input.showErrorMessage(exception.getMessage());
        }
        return Collections.emptyList();
    }

    private List<?> readCollectionFromFile() {
        input.showMessage("""
                Выберите тип коллекции для чтения:
                1. Автомобили
                2. Книги
                3. Корнеплоды
                """);
        var userObjectChoice = NumberUtils.parseInt(input.readLine());

        input.showMessage("Введите путь к файлу для чтения: ");
        var readFilePath = input.readLine();

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

    private <T> List<T> readAndDisplayCollection(String filePath, Class<T> clazz) {
        try {
            List<T> collection = FileHandler.readCollectionFromFile(filePath, clazz);
            input.showMessage("Считанная из файла коллекция: " + collection);
            return collection;
        } catch (IOException | ClassNotFoundException exception) {
            input.showErrorMessage("При загрузки коллекции возникла ошибка: " + exception.getMessage());
            return Collections.emptyList();
        }
    }


}

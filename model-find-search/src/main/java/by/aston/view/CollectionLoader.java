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

    public <T> List<T> getObjectList() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        FileHandler fileHandler = new FileHandler();

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
                    return readCollectionFromFile(fileHandler);
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

    private <T> List<T> readCollectionFromFile(FileHandler fileHandler) {
        input.showMessage("Введите путь к файлу для чтения: ");
        var readFilePath = input.readLine();

        input.showMessage("Введите тип объекта (1 - Машина, 2 - Книга, 3 - Корнеплоды): ");
        var readType = NumberUtils.parseInt(input.readLine());
        List<?> collection = null;

        try {
            switch (readType) {
                case 1:
                    collection = fileHandler.readCollectionFromFile(readFilePath, Car.class);
                    input.showMessage("Считанная коллекция автомобилей: " + collection);
                    break;
                case 2:
                    collection = fileHandler.readCollectionFromFile(readFilePath, Book.class);
                    input.showMessage("Считанная коллекция книг: " + collection);
                    break;
                case 3:
                    collection = fileHandler.readCollectionFromFile(readFilePath, Vegetable.class);
                    input.showMessage("Считанная коллекция корнеплодов: " + collection);
                    break;
                default:
                    input.showMessage("Неверный тип объекта.");
                    break;
            }
        } catch (IOException | ClassNotFoundException e) {
            input.showMessage("Ошибка при чтении из файла: " + e.getMessage());
        }
//        return collection;//todo
        return Collections.emptyList();
    }

//    private void writeCollectionToFile(FileHandler fileHandler, KeyboardInput keyboardInput,
//                                       List<Car> carCollection, List<Book> bookCollection,
//                                       List<Vegetable> vegetableCollection) {
//        input.showMessage("Введите путь к файлу для записи: ");
//        String writeFilePath = input.readLine();
//
//        try {
//            if (carCollection != null && !carCollection.isEmpty()) {
//                fileHandler.writeCollectionToFile(writeFilePath, carCollection);
//                input.showMessage("Коллекция автомобилей записана в файл.");
//            } else if (bookCollection != null && !bookCollection.isEmpty()) {
//                fileHandler.writeCollectionToFile(writeFilePath, bookCollection);
//                input.showMessage("Коллекция книг записана в файл.");
//            } else if (vegetableCollection != null && !vegetableCollection.isEmpty()) {
//                fileHandler.writeCollectionToFile(writeFilePath, vegetableCollection);
//                input.showMessage("Коллекция корнеплодов записана в файл.");
//            } else {
//                input.showMessage("Нет коллекции для записи.");
//            }
//        } catch (IOException e) {
//            input.showMessage("Ошибка при записи в файл: " + e.getMessage());
//        }
//    }
}

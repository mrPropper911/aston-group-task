package by.aston.view;

import java.util.List;
import java.io.*;

import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.model.Vegetable;

public class ConsoleInput {
    public static void inputOfData(String[] args) {
        KeyboardInput keyboardInput = new KeyboardInput();
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        FileHandler fileHandler = new FileHandler();

        List<Car> carCollection = null; // Для хранения коллекции автомобилей
        List<Book> bookCollection = null; // Для хранения коллекции книг
        List<Vegetable> vegetableCollection = null; // Для хранения коллекции корнеплодов

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Создать коллекцию объектов вручную");
            System.out.println("2. Сгенерировать коллекцию случайных объектов");
            System.out.println("3. Считать коллекцию из файла");
            System.out.println("4. Записать коллекцию в файл");
            System.out.println("5. Выход");

            int choice = keyboardInput.scanner.nextInt();
            keyboardInput.scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        keyboardInput.userDialogInit();
                        break;
                    case 2:
                        generateRandomCollection(randomObjectGenerator);
                        break;

                    case 3:
                        readCollectionFromFile(fileHandler, keyboardInput);
                        break;

                    case 4:
                        writeCollectionToFile(fileHandler, keyboardInput, carCollection, bookCollection, vegetableCollection);
                        break;

                    case 5:
                        System.out.println("Выход из программы.");
                        return;

                    default:
                        System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }
    }

    private static List<?> generateRandomCollection(RandomObjectGenerator randomObjectGenerator) {
        KeyboardInput keyboardInput = new KeyboardInput();
        System.out.print("Введите тип объекта (1 - Машина, 2 - Книга, 3 - Корнеплоды): ");
        int type = keyboardInput.scanner.nextInt();
        System.out.print("Введите количество объектов: ");
        int count = keyboardInput.scanner.nextInt();
        List<?> randomCollection = null;

        switch (type) {
            case 1:
                randomCollection = randomObjectGenerator.generateCollection(Car.class, count);
                System.out.println("Сгенерированные автомобили: " + randomCollection);
                break;
            case 2:
                randomCollection = randomObjectGenerator.generateCollection(Book.class, count);
                System.out.println("Сгенерированные книги: " + randomCollection);
                break;
            case 3:
                randomCollection = randomObjectGenerator.generateCollection(Vegetable.class, count);
                System.out.println("Сгенерированные корнеплоды: " + randomCollection);
                break;
            default:
                System.out.println("Неверный тип объекта.");
                break;
        }
        return randomCollection;
    }

    private static List<?> readCollectionFromFile(FileHandler fileHandler, KeyboardInput keyboardInput) {
        System.out.print("Введите путь к файлу для чтения: ");
        String readFilePath = keyboardInput.scanner.nextLine();
        System.out.print("Введите тип объекта (1 - Машина, 2 - Книга, 3 - Корнеплоды): ");
        int readType = keyboardInput.scanner.nextInt();
        List<?> collection = null;

        try {
            switch (readType) {
                case 1:
                    collection = fileHandler.readCollectionFromFile(readFilePath, Car.class);
                    System.out.println("Считанная коллекция автомобилей: " + collection);
                    break;
                case 2:
                    collection = fileHandler.readCollectionFromFile(readFilePath, Book.class);
                    System.out.println("Считанная коллекция книг: " + collection);
                    break;
                case 3:
                    collection = fileHandler.readCollectionFromFile(readFilePath, Vegetable.class);
                    System.out.println("Считанная коллекция корнеплодов: " + collection);
                    break;
                default:
                    System.out.println("Неверный тип объекта.");
                    break;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при чтении из файла: " + e.getMessage());
        }
        return collection;
    }

    private static void writeCollectionToFile(FileHandler fileHandler, KeyboardInput keyboardInput,
                                              List<Car> carCollection, List<Book> bookCollection,
                                              List<Vegetable> vegetableCollection) {
        System.out.print("Введите путь к файлу для записи: ");
        String writeFilePath = keyboardInput.scanner.nextLine();

        try {
            if (carCollection != null && !carCollection.isEmpty()) {
                fileHandler.writeCollectionToFile(writeFilePath, carCollection);
                System.out.println("Коллекция автомобилей записана в файл.");
            } else if (bookCollection != null && !bookCollection.isEmpty()) {
                fileHandler.writeCollectionToFile(writeFilePath, bookCollection);
                System.out.println("Коллекция книг записана в файл.");
            } else if (vegetableCollection != null && !vegetableCollection.isEmpty()) {
                fileHandler.writeCollectionToFile(writeFilePath, vegetableCollection);
                System.out.println("Коллекция корнеплодов записана в файл.");
            } else {
                System.out.println("Нет коллекции для записи.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}

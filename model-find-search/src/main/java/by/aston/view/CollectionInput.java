package by.aston.view;

import java.util.List;
import java.io.*;

import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.model.Vegetable;

public class CollectionInput {
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
                        System.out.print("Введите тип объекта (1 - Машина, 2 - Книга, 3 - Корнеплоды): ");
                        int type = keyboardInput.scanner.nextInt();
                        System.out.print("Введите количество объектов: ");
                        int count = keyboardInput.scanner.nextInt();
                        List<?> randomCollection = null;

                        switch (type) {
                            case 1:
                                randomCollection = randomObjectGenerator.generateCollection(Car.class, count);
                                carCollection = (List<Car>) randomCollection;
                                break;
                            case 2:
                                randomCollection = randomObjectGenerator.generateCollection(Book.class, count);
                                bookCollection = (List<Book>) randomCollection; // Сохраняем в переменную
                                break;
                            case 3:
                                randomCollection = randomObjectGenerator.generateCollection(Vegetable.class, count);
                                vegetableCollection = (List<Vegetable>) randomCollection; // Сохраняем в переменную
                                break;
                            default:
                                System.out.println("Неверный тип объекта.");
                                continue;
                        }

                        System.out.println("Сгенерированные объекты:");
                        for (Object obj : randomCollection) {
                            System.out.println(obj);
                        }
                        break;

                    case 3:
                        System.out.print("Введите путь к файлу для чтения: ");
                        String readFilePath = keyboardInput.scanner.nextLine();
                        System.out.print("Введите тип объекта (1 - Машина, 2 - Книга, 3 - Корнеплоды): ");
                        int readType = keyboardInput.scanner.nextInt();

                        try {
                            switch (readType) {
                                case 1:
                                    carCollection = fileHandler.readCollectionFromFile(readFilePath, Car.class);
                                    System.out.println("Считанная коллекция автомобилей:");
                                    for (Car car : carCollection) {
                                        System.out.println(car);
                                    }
                                    break;
                                case 2:
                                    bookCollection = fileHandler.readCollectionFromFile(readFilePath, Book.class);
                                    System.out.println("Считанная коллекция книг:");
                                    for (Book book : bookCollection) {
                                        System.out.println(book);
                                    }
                                    break;
                                case 3:
                                    vegetableCollection = fileHandler.readCollectionFromFile(readFilePath, Vegetable.class);
                                    System.out.println("Считанная коллекция корнеплодов:");
                                    for (Vegetable vegetable : vegetableCollection) {
                                        System.out.println(vegetable);
                                    }
                                    break;
                                default:
                                    System.out.println("Неверный тип объекта.");
                                    break;
                            }
                        } catch (IOException | ClassNotFoundException e) {
                            System.out.println("Ошибка при чтении из файла: " + e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.print("Введите путь к файлу для записи: ");
                        String writeFilePath = keyboardInput.scanner.nextLine();
                        // Записываем в файл последнюю сгенерированную коллекцию
                        if (carCollection != null) {
                            fileHandler.writeCollectionToFile(writeFilePath, carCollection);
                            System.out.println("Коллекция автомобилей записана в файл.");
                        } else if (bookCollection != null) {
                            fileHandler.writeCollectionToFile(writeFilePath, bookCollection);
                            System.out.println("Коллекция книг записана в файл.");
                        } else if (vegetableCollection != null) {
                            fileHandler.writeCollectionToFile(writeFilePath, vegetableCollection);
                            System.out.println("Коллекция корнеплодов записана в файл.");
                        } else {
                            System.out.println("Нет коллекции для записи.");
                        }
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
}

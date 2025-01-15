package by.aston.controller;


import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.model.Vegetable;
import by.aston.service.search.BinarySearch;
import by.aston.service.search.CustomSearch;
import by.aston.service.search.LinearSearch;
import by.aston.view.FileInput;
import by.aston.view.KeyboardInput;
import by.aston.view.RandomInput;

import java.util.List;
import java.util.Scanner;

public class UserInterfaceApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final FileInput fileInput = new FileInput();
    private final KeyboardInput keyboardInput = new KeyboardInput(scanner);
    private final RandomInput randomInput = new RandomInput();
//    private final CustomSort<Object> customSort = new CustomSort();
    private CustomSearch<Object> customSearch;
    private List<Object> currentData = null;

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Ввести данные");
            System.out.println("2. Сортировать данные");
            System.out.println("3. Найти элемент");
            System.out.println("4. Вывести текущие данные");
            System.out.println("5. Выйти");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> handleInput();
                case 2 -> handleSort();
                case 3 -> handleSearch();
                case 4 -> displayInfo();
                case 5 -> {
                    running = false;
                    System.out.println("Выход из программы.");
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void displayInfo() {
        if (currentData == null || currentData.isEmpty()) {
            System.out.println("Нет данных для отображения.");
        } else {
            System.out.println("Текущие данные:");
            currentData.forEach(System.out::println);
        }
    }

    private List<Object> chooseInputMethod(String type) {
        System.out.println("Выберите способ ввода данных:");
        System.out.println("1. Из файла");
        System.out.println("2. С клавиатуры");
        System.out.println("3. Случайная генерация");

        switch (Integer.parseInt(scanner.nextLine())) {
            case 1 -> {
                return fileInput.readData(type);
            }
            case 2 -> {
                System.out.print("Введите количество элементов: ");
                int count = Integer.parseInt(scanner.nextLine());
                return keyboardInput.readData(type, count);
            }
            case 3 -> {
                System.out.print("Введите количество элементов: ");
                int count = Integer.parseInt(scanner.nextLine());
                return randomInput.generateData(type, count);
            }
            default -> {
                System.out.println("Неверный выбор.");
                return null;
            }
        }
    }

    private void handleInput() {
        System.out.println("Выберите тип данных:");
        System.out.println("1. Car");
        System.out.println("2. Book");
        System.out.println("3. RootVegetable");

        String type = switch (Integer.parseInt(scanner.nextLine())) {
            case 1 -> "Car";
            case 2 -> "Book";
            case 3 -> "RootVegetable";
            default -> {
                System.out.println("Неверный выбор.");
                yield null;
            }
        };

        if (type == null) return;

        currentData = chooseInputMethod(type);
        if (currentData != null) {
            System.out.println("Данные успешно загружены.");
        }
    }

    private void handleSort() {
        if (currentData == null || currentData.isEmpty()) {
            System.out.println("Нет данных для сортировки. Сначала введите данные.");
            return;
        }

        System.out.println("Выберите режим сортировки:");
        System.out.println("1. Обычная сортировка");
        System.out.println("2. Сортировка с учётом чётных/нечётных значений");

        boolean specialSort = switch (Integer.parseInt(scanner.nextLine())) {
            case 1 -> false;
            case 2 -> true;
            default -> {
                System.out.println("Неверный выбор. Будет выполнена обычная сортировка.");
                yield false;
            }
        };

//        customSort.sort(currentData, specialSort);
        System.out.println("Сортировка завершена. Отсортированные данные:");
        displayInfo();
    }

    private void handleSearch() {
        if (currentData == null || currentData.isEmpty()) {
            System.out.println("Нет данных для поиска. Сначала введите данные.");
            return;
        }

        System.out.println("Выберите метод поиска:");
        System.out.println("1. BinarySearch");
        System.out.println("2. LinearSearch");

        customSearch = switch (Integer.parseInt(scanner.nextLine())) {
            case 1 -> new BinarySearch<>(null); // TODO
            case 2 -> new LinearSearch<>(null); // TODO
            default -> {
                System.out.println("Неверный выбор.");
                yield null;
            }
        };

        if (customSearch != null) {
            System.out.print("Введите элемент для поиска: ");
            String target = scanner.nextLine();

            Object key = parseKey(target, currentData.get(0).getClass());
            if (key == null) {
                System.out.println("Неверный формат ключа для поиска.");
                return;
            }

            int index = customSearch.search(currentData, key);
            if (index != -1) {
                System.out.println("Элемент найден: " + currentData.get(index));
            } else {
                System.out.println("Элемент не найден.");
            }
        }
    }


    private Object parseKey(String input, Class<?> clazz) {
        try {
            if (clazz == Car.class) {
//                return new Car.Builder().setModel(input).build();
                return new Car(null, null, null);
            } else if (clazz == Book.class) {
//                return new Book.Builder().setTitle(input).build();
                return new Book();
            } else if (clazz == Vegetable.class) {
//                return new Vegetable.Builder().setType(input).build();
                return new Vegetable();
            }
        } catch (Exception e) {
            System.out.println("Ошибка преобразования ключа: " + e.getMessage());
        }
        return null;
    }
}
package by.aston.controller;


import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.model.Vegetable;
import by.aston.service.CustomCollections;
import by.aston.view.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static by.aston.utils.NumberUtils.isEven;
import static by.aston.utils.NumberUtils.parseInt;

public class UserInterfaceApplication {

    private final DataInput input;
    private List<Object> currentData = Collections.emptyList();

    public UserInterfaceApplication(DataInput input) {
        this.input = input;
    }

    public void run() {
        boolean running = true;

        while (running) {
            input.showMessage("""
                    Выберите действие:
                    1. Ввести данные.
                    2. Сортировать данные.
                    3. Найти элемент.
                    4. Вывести текущие данные в консоль.
                    5. Сохранить текущие данные в файл.
                    6. Выйти.
                    """);

            int choice = parseInt(input.readLine(), -1);

            switch (choice) {
                case 1 -> handleInput();
                case 2 -> handleSort();
                case 3 -> handleSearch();
                case 4 -> displayInfo();
                case 5 -> handleSaveToFile();
                case 6 -> {
                    running = false;
                    input.showMessage("Выход из программы.");
                }
                default -> input.showMessage("Неверный выбор. Попробуйте снова.");
            }
        }
    }


    private void handleInput() {
        var collectionLoader = new CollectionLoader(input);
        currentData = collectionLoader.getObjectList();
    }

    private void handleSort() {
        if (currentData == null || currentData.isEmpty()) {
            input.showMessage("Нет данных для сортировки. Сначала введите данные.");
            return;
        }

        input.showMessage("Выберите режим сортировки:");
        input.showMessage("1. Обычная сортировка");
        input.showMessage("2. Сортировка с учётом чётных/нечётных значений");

        int choice = parseInt(input.readLine(), 1);

        Comparator<Object> comparator = Comparator.comparing(Object::toString);

        switch (choice) {
            case 1 -> {
                try {
                    CustomCollections.sort(currentData, comparator);
                } catch (IllegalArgumentException e) {
                    input.showMessage("Ошибка сортировки: " + e.getMessage());
                    return;
                }
            }
            case 2 -> {
                Comparator<Object> specialComparator = (o1, o2) -> {
                    boolean isFirstEven = isEven(o1);
                    boolean isSecondEven = isEven(o2);

                    if (isFirstEven && !isSecondEven) return -1;
                    if (!isFirstEven && isSecondEven) return 1;

                    return comparator.compare(o1, o2);
                };

                try {
                    CustomCollections.sort(currentData, specialComparator);
                } catch (IllegalArgumentException e) {
                    input.showMessage("Ошибка сортировки: " + e.getMessage());
                    return;
                }
            }
            default -> input.showMessage("Неверный выбор.");
        }

        input.showMessage("Сортировка завершена. Отсортированные данные:");
        displayInfo();
    }

    private void handleSearch() {
        if (currentData == null || currentData.isEmpty()) {
            input.showMessage("Нет данных для поиска. Сначала введите данные.");
            return;
        }

        input.showMessage("Выберите метод поиска:");
        input.showMessage("1. BinarySearch");
        input.showMessage("2. LinearSearch");

        int choice = parseInt(input.readLine(), -1);
        if (choice != 1 && choice != 2) {
            input.showMessage("Некорректный ввод.");
            return;
        }

        input.showMessage("Введите элемент для поиска:");
        String target = input.readLine();

        Object key = parseKey(target, currentData.get(0).getClass());
        if (key == null) {
            input.showMessage("Неверный формат ключа для поиска.");
            return;
        }

        Comparator<Object> comparator = Comparator.comparing(Object::toString);
        int index = -1;

        if (choice == 1) {
            try {
                index = CustomCollections.searchBinary(currentData, key, comparator);
            } catch (IllegalArgumentException e) {
                input.showMessage("Ошибка: " + e.getMessage());
            }
        } else {
            for (int i = 0; i < currentData.size(); i++) {
                if (comparator.compare(currentData.get(i), key) == 0) {
                    index = i;
                    break;
                }
            }
        }

        if (index != -1) {
            input.showMessage("Элемент найден: " + currentData.get(index));
        } else {
            input.showMessage("Элемент не найден.");
        }
    }

    private void displayInfo() {
        if (currentData == null || currentData.isEmpty()) {
            input.showMessage("Нет данных для отображения.");
        } else {
            input.showMessage("Текущие данные:");
            currentData.forEach(data -> input.showMessage(data.toString()));
        }
    }

    //todo проверять является ли путь файлом
    private void handleSaveToFile() {
        input.showMessage("Введите путь к файлу для сохранения данных: ");
        String path = input.readLine();

        if (currentData == null || currentData.isEmpty()){
            input.showErrorMessage("Коллекция для сохранения отсутствует.");
        }

        try {
            FileHandler.writeCollectionToFile(path, currentData);
        } catch (IOException exception) {
            input.showErrorMessage("При сохранении коллекции возникла ошибка: "
                    + exception.getMessage());
        }
    }


//    private List<Object> chooseInputMethod(String type) {
//        input.showMessage("Выберите способ ввода данных:");
//        input.showMessage("1. Из файла");
//        input.showMessage("2. С клавиатуры");
//        input.showMessage("3. Случайная генерация");
//
//        int choice = parseInt(input.readLine(), -1);
//        switch (choice) {
//            case 1 -> {
//                return fileInput.readData(type);
//            }
//            case 2 -> {
//                var keyboardInput = new KeyboardInput(input);
//                return keyboardInput.getObjectList();
//            }
//            case 3 -> {
//                input.showMessage("Введите количество элементов: ");
//                int count = parseInt(input.readLine(), 0);
//                return randomInput.generateData(type, count);
//            }
//            default -> {
//                input.showMessage("Неверный выбор.");
//                return null;
//            }
//        }
//    }

//    private void handleInput() {
//        input.showMessage("Выберите тип данных:");
//        input.showMessage("1. Car");
//        input.showMessage("2. Book");
//        input.showMessage("3. RootVegetable");
//
//        String type = switch (parseInt(input.readLine(), -1)) {
//            case 1 -> "Car";
//            case 2 -> "Book";
//            case 3 -> "RootVegetable";
//            default -> {
//                input.showMessage("Неверный выбор.");
//                yield null;
//            }
//        };
//
//        if (type == null) return;
//
//        currentData = chooseInputMethod(type);
//        if (currentData != null) {
//            input.showMessage("Данные успешно загружены.");
//        }
//    }


    private Object parseKey(String key, Class<?> clazz) {
        try {
            if (clazz == Car.class) {
                return new Car.Builder().model(key).build();
            } else if (clazz == Book.class) {
                return new Book.Builder().title(key).build();
            } else if (clazz == Vegetable.class) {
                return new Vegetable.Builder().type(key).build();
            }
        } catch (Exception e) {
            input.showMessage("Ошибка преобразования ключа: " + e.getMessage());
        }
        return null;
    }
}
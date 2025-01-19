package by.aston.controller;


import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.model.Vegetable;
import by.aston.service.CustomAlgorithm;
import by.aston.service.CustomCollections;
import by.aston.service.algorithm.ExtraSort;
import by.aston.service.algorithm.ShellSort;
import by.aston.service.algorithm.SortContext;
import by.aston.utils.NumberUtils;
import by.aston.view.CollectionLoader;
import by.aston.view.DataInput;
import by.aston.view.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserInterfaceApplication {

    private final DataInput input;
    private List<?> currentData = new ArrayList<>();

    public UserInterfaceApplication(DataInput input) {
        this.input = input;
    }

    public void run() {
        while (true) {
            input.showMessage("""
                    \nВыберите действие:
                    1. Ввести данные.
                    2. Сортировать данные.
                    3. Найти элемент.
                    4. Вывести текущие данные в консоль.
                    5. Сохранить текущие данные в файл.
                    6. Выйти.
                    """);
            try {
                var choice = NumberUtils.parseInt(input.readLine());

                switch (choice) {
                    case 1 -> handleInput();
                    case 2 -> handleSort();
                    case 3 -> handleSearch();
                    case 4 -> displayInfo();
                    case 5 -> handleSaveToFile();
                    case 6 -> {
                        input.showMessage("Выход из программы.");
                        return;
                    }
                    default -> input.showMessage("Неверный выбор. Попробуйте снова.");
                }
            } catch (NumberFormatException exception) {
                input.showErrorMessage("Ошибка формата числа: " + exception.getMessage());
            }
        }
    }

    private void handleInput() {
        var collectionLoader = new CollectionLoader(input);
        currentData = collectionLoader.getObjectList();
    }

    private void handleSort() {
        if (currentData == null || currentData.isEmpty()) {
            input.showErrorMessage("Нет данных для сортировки. Сначала введите данные.");
            return;
        }

        input.showMessage("""
                \nВыберите режим сортировки:
                1. Сортировка Шелла.
                2. Сортировка с учётом чётных/нечётных значений.
                """);
        try {
            int choice = NumberUtils.parseInt(input.readLine());
            switch (choice) {
                case 1 -> {
                    try {
                        CustomCollections.sortWildcard(currentData);
                    } catch (IllegalArgumentException exception) {
                        input.showErrorMessage("Ошибка сортировки: " + exception.getMessage());
                        return;
                    }
                }
                case 2 -> {
                    try {
                        CustomAlgorithm.naturalOrderForEvenSortWildcard(currentData);
                    } catch (IllegalArgumentException exception) {
                        input.showErrorMessage("Ошибка сортировки чётных/нечётных значений: " +
                                exception.getMessage());
                    }
                }
                default -> input.showErrorMessage("Неверный выбор.");
            }
        } catch (NumberFormatException exception) {
            input.showErrorMessage("Ошибка формата числа при выборе режима сортировки: " +
                    exception.getMessage());
        }

        input.showMessage("Сортировка завершена. Отсортированные данные:");
        displayInfo();
    }

    private <T> void handleSearch() {
        if (currentData == null || currentData.isEmpty()) {
            input.showErrorMessage("Нет данных для поиска. Сначала введите данные.");
            return;
        }
        input.showMessage("Введите элемент для поиска:");
        var key = input.readLine();

        Class<?> clazz = currentData.get(0).getClass();
        T searchObject = createSearchObject(clazz, key);
        if (searchObject == null) {
            input.showErrorMessage("Не удалось создать объект для поиска.");
            return;
        }

        Comparator<T> comparator = getComparator(clazz);
        if (comparator == null) {
            input.showErrorMessage("Нет данных для создания поиска для класса: " +
                    clazz.getName());
        }

        List<T> data = (List<T>) currentData;

        var index = -1;
        try {
            index = CustomCollections.searchBinary(data, searchObject, comparator);
        } catch (IllegalArgumentException exception) {
            input.showErrorMessage("Ошибка при выполнения поиска: " + exception.getMessage());
        }

        if (index != -1) {
            input.showMessage("Элемент найден: " + currentData.get(index));
        } else {
            input.showErrorMessage("Элемент не найден.");
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T createSearchObject(Class<?> clazz, String key) {
        try {
            if (clazz == Car.class) {
                return (T) new Car.Builder().model(key).build();
            } else if (clazz == Book.class) {
                return (T) new Book.Builder().title(key).build();
            } else if (clazz == Vegetable.class) {
                return (T) new Vegetable.Builder().type(key).build();
            }
        } catch (Exception e) {
            input.showMessage("Ошибка создания объекта для поиска: " + e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> Comparator<T> getComparator(Class<?> clazz) {
        if (clazz == Car.class) {
            return (Comparator<T>) Comparator.comparing(obj -> ((Car) obj).getModel());//Поиск по model
        } else if (clazz == Book.class) {
            return (Comparator<T>) Comparator.comparing(obj -> ((Book) obj).getTitle());//Поиск по title
        } else if (clazz == Vegetable.class) {
            return (Comparator<T>) Comparator.comparing(obj -> ((Vegetable) obj).getType());//Поиск по type
        }
        return null;
    }

    private void displayInfo() {
        if (currentData == null || currentData.isEmpty()) {
            input.showMessage("Нет данных для отображения.");
        } else {
            input.showMessage("Текущие данные:");
            currentData.forEach(data -> input.showMessage(data.toString()));
        }
    }

    private void handleSaveToFile() {
        input.showMessage("Введите путь к файлу для сохранения данных: ");
        var path = input.readLine();

        if (currentData == null || currentData.isEmpty()) {
            input.showErrorMessage("Коллекция для сохранения отсутствует.");
        }

        try {
            FileHandler.writeCollectionToFile(path, currentData);
        } catch (IOException exception) {
            input.showErrorMessage("При сохранении коллекции возникла ошибка: " +
                    exception.getMessage());
        }

        input.showMessage("Коллекция успешно сохранена.");
    }
}
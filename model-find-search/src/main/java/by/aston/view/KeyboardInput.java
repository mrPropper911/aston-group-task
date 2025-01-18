package by.aston.view;

import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.model.Vegetable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class KeyboardInput {

    public Scanner scanner = new Scanner(System.in);

    private final DataInput input;

    public KeyboardInput(DataInput input) {
        this.input = input;
    }

    //todo userDialogInit перенести текст

    public <T> List<T> getObjectList() {
        input.showMessage("Выберите тип коллекции для создания:");
        input.showMessage("1. Автомобили");
        input.showMessage("2. Книги");
        input.showMessage("3. Корнеплоды");
        input.showMessage("4. Выход");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                inputFromConsole(Car.class);
                break;
            case 2:
                inputFromConsole(Book.class);
                break;
            case 3:
                inputFromConsole(Vegetable.class);
                break;
            case 4:
                input.showMessage("Выход из программы.");
                return Collections.emptyList();
            default:
                input.showMessage("Неверный выбор. Пожалуйста, попробуйте снова.");
                getObjectList(); // Повторный вызов для нового выбора
        }
        //todo
        return Collections.emptyList();
    }

    private <T> List<T> inputFromConsole(Class<T> clazz) {
        input.showMessage("Введите количество элементов для создания: ");
        int count = scanner.nextInt();
        scanner.nextLine();
        List<T> collection = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            try {
                T obj = createObject(clazz);
                if (obj != null) {
                    collection.add(obj);
                }
            } catch (IllegalArgumentException e) {
                input.showMessage("Ошибка при создании объекта: " + e.getMessage());
                i--; // Уменьшаем счетчик, чтобы повторить ввод для этого объекта
            } catch (Exception e) {
                input.showMessage("Произошла ошибка: " + e.getMessage());
                scanner.nextLine(); // Очистка ввода
            }
        }

        input.showMessage("Созданные объекты: " + collection);
        return collection;
    }

    private <T> T createObject(Class<T> clazz) {
        if (clazz == Car.class) {
            return (T) createCar();
        } else if (clazz == Book.class) {
            return (T) createBook();
        } else if (clazz == Vegetable.class) {
            return (T) createVegetable();
        }
        return null;
    }

    //todo добавить валидатор на каждое поле
    private Car createCar() {
        input.showMessage("Введите модель автомобиля: ");
        String model = scanner.nextLine();
        input.showMessage("Введите мощность автомобиля: ");
        int power = scanner.nextInt();
        input.showMessage("Введите год производства автомобиля: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        return new Car.Builder()
                .model(model)
                .power(power)
                .yearRelease(year)
                .build();
    }

    private Book createBook() {
        input.showMessage("Введите автора книги: ");
        String author = scanner.nextLine();
        input.showMessage("Введите название книги: ");
        String title = scanner.nextLine();
        input.showMessage("Введите количество страниц книги: ");
        int pages = scanner.nextInt();
        scanner.nextLine();

        return new Book.Builder()
                .author(author)
                .title(title)
                .numberPages(pages)
                .build();
    }

    private Vegetable createVegetable() {
        input.showMessage("Введите тип корнеплода: ");
        String type = scanner.nextLine();
        input.showMessage("Введите вес корнеплода: ");
        double weight = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        input.showMessage("Введите цвет корнеплода: ");
        String color = scanner.nextLine();

        return new Vegetable.Builder()
                .type(type)
                .weight(weight)
                .color(color)
                .build();
    }
}

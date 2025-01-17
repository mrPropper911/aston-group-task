package by.aston.view;

import by.aston.model.Book;
import by.aston.model.Car;
import by.aston.model.Vegetable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KeyboardInput {

    public Scanner scanner = new Scanner(System.in);

    public void userDialogInit() {
        System.out.println("Выберите тип коллекции для создания:");
        System.out.println("1. Автомобили");
        System.out.println("2. Книги");
        System.out.println("3. Корнеплоды");
        System.out.println("4. Выход");

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
                System.out.println("Выход из программы.");
                return;
            default:
                System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                userDialogInit(); // Повторный вызов для нового выбора
        }
    }

    public <T> void inputFromConsole(Class<T> clazz) {
    System.out.print("Введите количество элементов для создания: ");
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
            System.out.println("Ошибка при создании объекта: " + e.getMessage());
            i--; // Уменьшаем счетчик, чтобы повторить ввод для этого объекта
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
            scanner.nextLine(); // Очистка ввода
        }
    }

    // Выводим созданные объекты
    System.out.println("Созданные объекты: " + collection); // Используем toString() для вывода коллекции
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

    private Car createCar() {
        System.out.print("Введите модель автомобиля: ");
        String model = scanner.nextLine();
        System.out.print("Введите мощность автомобиля: ");
        int power = scanner.nextInt();
        System.out.print("Введите год производства автомобиля: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        return new Car.Builder()
                .model(model)
                .power(power)
                .yearRelease(year)
                .build();
    }

    private Book createBook() {
        System.out.print("Введите автора книги: ");
        String author = scanner.nextLine();
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();
        System.out.print("Введите количество страниц книги: ");
        int pages = scanner.nextInt();
        scanner.nextLine();

        return new Book.Builder()
                .author(author)
                .title(title)
                .numberPages(pages)
                .build();
    }

    private Vegetable createVegetable() {
        System.out.print("Введите тип корнеплода: ");
        String type = scanner.nextLine();
        System.out.print("Введите вес корнеплода: ");
        double weight = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.print("Введите цвет корнеплода: ");
        String color = scanner.nextLine();

        return new Vegetable.Builder()
                .type(type)
                .weight(weight)
                .color(color)
                .build();
    }
}

package by.aston.view;

import by.aston.model.*;
import by.aston.utils.NumberUtils;

import java.util.*;

public class KeyboardInput {

    private final DataInput input;
    public Scanner scanner = new Scanner(System.in);//todo delete

    public KeyboardInput(DataInput input) {
        this.input = input;
    }

    public <T> List<T> getObjectList() {
        try {
            input.showMessage("""
                    Выберите тип коллекции для создания:
                    1. Автомобили
                    2. Книги
                    3. Корнеплоды
                    """);
            var userObjectChoice = NumberUtils.parseInt(input.readLine());

            input.showMessage("Введите количество элементов для создания: ");
            var userCountOfObjectChoice = NumberUtils.parseInt(input.readLine());

            List<T> objectsList = generateObjectList(userObjectChoice, userCountOfObjectChoice);
            input.showMessage("Созданные объекты: " + objectsList);

            return objectsList;

        } catch (NumberFormatException exception) {
            input.showErrorMessage("Ошибка формата числа: " + exception.getMessage());
        } catch (IllegalArgumentException exception) {
            input.showErrorMessage(exception.getMessage());
        }
        return Collections.emptyList();
    }

    private <T> List<T> generateObjectList(int objectType,
                                           int objectCount) throws IllegalArgumentException {
        if (objectCount <= 0) {
            throw new IllegalArgumentException("Количество объектов не может быть отрицательной.");
        }

        List<T> collection = new ArrayList<>(objectCount);
        ObjectBuilderFactory factory = new ObjectBuilderFactory();
        ObjectBuilder<?> builder = factory.getBuilder(objectType);

        if (builder == null) {
            throw new IllegalArgumentException("Ошибка выбора типа коллекции.");
        }

        for (int i = 0; i < objectCount; i++) {
            Optional<?> optional = builder.buildFromInput(input);
            if (optional.isPresent()) {
                collection.add((T) optional.get());
            } else {
                i--;// Уменьшаем счетчик, чтобы повторить ввод для этого объекта
            }
        }
        return collection;
    }

//    private <T> List<T> inputFromConsole(Class<T> clazz) {
//        List<T> collection = new ArrayList<>();
//        try {
//            input.showMessage("Введите количество элементов для создания: ");
//            int count = NumberUtils.parseInt(input.readLine());
//            input.readLine();
//
//            for (int i = 0; i < count; i++) {
//                try {
//                    T obj = createObject(clazz);
//                    if (obj != null) {
//                        collection.add(obj);
//                    }
//                } catch (IllegalArgumentException e) {
//                    input.showMessage("Ошибка при создании объекта: " + e.getMessage());
//                    i--; // Уменьшаем счетчик, чтобы повторить ввод для этого объекта
//                } catch (Exception e) {
//                    input.showMessage("Произошла ошибка: " + e.getMessage());
//                    scanner.nextLine(); // Очистка ввода
//                }
//            }
//
//            input.showMessage("Созданные объекты: " + collection);
//            return collection;
//
//        } catch (NumberFormatException exception) {
//            input.showErrorMessage(exception.getMessage());
//        }
//
//        return Collections.emptyList();
//    }


//    //todo удалить
//    private <T> T createObject(Class<T> clazz) {
//        if (clazz == Car.class) {
//            return (T) createCar();
//        } else if (clazz == Book.class) {
//            return (T) createBook();
//        } else if (clazz == Vegetable.class) {
//            return (T) createVegetable();
//        }
//        return null;
//    }

//    //todo удалить
//    private Car createCar() {
//        input.showMessage("Введите модель автомобиля: ");
//        String model = scanner.nextLine();
//        input.showMessage("Введите мощность автомобиля: ");
//        int power = scanner.nextInt();
//        input.showMessage("Введите год производства автомобиля: ");
//        int year = scanner.nextInt();
//        scanner.nextLine();
//
//        return new Car.Builder()
//                .model(model)
//                .power(power)
//                .yearRelease(year)
//                .build();
//    }
//
//    //todo удалить
//    private Book createBook() {
//        input.showMessage("Введите автора книги: ");
//        String author = scanner.nextLine();
//        input.showMessage("Введите название книги: ");
//        String title = scanner.nextLine();
//        input.showMessage("Введите количество страниц книги: ");
//        int pages = scanner.nextInt();
//        scanner.nextLine();
//
//        return new Book.Builder()
//                .author(author)
//                .title(title)
//                .numberPages(pages)
//                .build();
//    }
//
//    //todo удалить
//    private Vegetable createVegetable() {
//        input.showMessage("Введите тип корнеплода: ");
//        String type = scanner.nextLine();
//        input.showMessage("Введите вес корнеплода: ");
//        double weight = scanner.nextDouble();
//        scanner.nextLine(); // consume newline
//        input.showMessage("Введите цвет корнеплода: ");
//        String color = scanner.nextLine();
//
//        return new Vegetable.Builder()
//                .type(type)
//                .weight(weight)
//                .color(color)
//                .build();
//    }
}

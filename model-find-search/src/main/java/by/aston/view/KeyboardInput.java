package by.aston.view;

import by.aston.model.ObjectBuilder;
import by.aston.model.ObjectBuilderFactory;
import by.aston.utils.NumberUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class KeyboardInput {

    private final DataInput input;

    public KeyboardInput(DataInput input) {
        this.input = input;
    }

    public List<?> getObjectList() {
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

            List<?> objectsList = generateObjectList(userObjectChoice, userCountOfObjectChoice);
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
}

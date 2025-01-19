package by.aston.validator;

import by.aston.model.Vegetable;

public class VegetableValidator {
    public static void validate(Vegetable vegetable) {
        if (vegetable == null) {
            throw new IllegalArgumentException("Объект Vegetable не может быть null.");
        }
        validateType(vegetable.getType());
        validateWeight(vegetable.getWeight());
        validateColor(vegetable.getColor());
    }

    public static void validateType(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Название овоща не может быть пустым.");
        }
    }

    public static void validateWeight(Double weight) {
        if (weight == null || weight <= 0) {
            throw new IllegalArgumentException("Вес овоща не может быть меньше нуля.");
        }
    }

    public static void validateColor(String color) {
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("Цвет овоща не может быть пустым.");
        }
    }
}

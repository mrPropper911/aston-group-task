package by.aston.helper;

import by.aston.model.Vegetable;

public class VegetableValidator {

    public static boolean validate(Vegetable vegetable) {
        if (vegetable == null) {
            System.out.println("Объект Vegetable не может быть null.");
            return false;
        }

        if (!validateType(vegetable.getType())) {
            System.out.println("Некорректный тип овоща: " + vegetable.getType());
            return false;
        }

        if (!validateWeight(vegetable.getWeight())) {
            System.out.println("Некорректный вес овоща: " + vegetable.getWeight());
            return false;
        }

        if (!validateColor(vegetable.getColor())) {
            System.out.println("Некорректный цвет овоща: " + vegetable.getColor());
            return false;
        }

        return true;
    }

    private static boolean validateType(String type) {
        return type != null && !type.isBlank();
    }

    private static boolean validateWeight(Double weight) {
        return weight != null && weight > 0;
    }

    private static boolean validateColor(String color) {
        return color != null && !color.isBlank();
    }
}

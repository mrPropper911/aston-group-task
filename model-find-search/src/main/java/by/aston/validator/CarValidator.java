package by.aston.validator;

import by.aston.model.Car;

import java.util.Calendar;

public class CarValidator {
    public static void validate(Car car) throws IllegalArgumentException {
        if (car == null) {
            throw new IllegalArgumentException("Объект Car не может быть null.");
        }
        validateModel(car.getModel());
        validatePower(car.getPower());
        validateYearRelease(car.getYearRelease());
    }

    public static void validateModel(String model) throws IllegalArgumentException {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Модель машины не может быть пустой.");
        }
    }

    public static void validatePower(Integer power) throws IllegalArgumentException {
        if (power == null || power <= 0) {
            throw new IllegalArgumentException("Мощность машины не может быть пустой или меньше нуля.");
        }
    }

    // первый авто появился в 1886 году, поэтому такая проверка
    public static void validateYearRelease(Integer yearRelease) throws IllegalArgumentException {
        int currentYear = java.util.Calendar.getInstance().get(Calendar.YEAR);
        if (yearRelease == null || yearRelease <= 1885 || yearRelease > currentYear) {
            throw new IllegalArgumentException("Год выпуска машины должен быть в диапазоне от 1886 до" + currentYear);
        }
    }
}

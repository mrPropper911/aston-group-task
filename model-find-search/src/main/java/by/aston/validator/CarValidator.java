package by.aston.validator;

import by.aston.model.Car;

public class CarValidator {
    public static boolean validate(Car car) {
        if (car == null) {
            System.out.println("Объект Car не может быть null.");
            return false;
        }

        if (!validateModel(car.getModel())) {
            System.out.println("Некорректная модель машины: " + car.getModel());
            return false;
        }

        if (!validatePower(car.getPower())) {
            System.out.println("Некорректная мощность машины: " + car.getPower());
            return false;
        }

        if (!validateYearRelease(car.getYearRelease())) {
            System.out.println("Некорректный год выпуска машины: " + car.getYearRelease());
            return false;
        }

        return true;
    }

    private static boolean validateModel(String model) {
        return model != null && !model.isBlank();
    }

    private static boolean validatePower(Integer power) {
        return power != null && power > 0;
    }

    // первый авто появился в 1886 году, поэтому такая проверка
    private static boolean validateYearRelease(Integer yearRelease) {
        return yearRelease != null && yearRelease > 1885 && yearRelease <= java.time.Year.now().getValue();
    }
}

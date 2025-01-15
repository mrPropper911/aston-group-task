package by.aston.helper;

import by.aston.controller.DataValidation;
import by.aston.model.Car;

public class CarValidator {
    private final DataValidation dataValidation = new DataValidation();

    public boolean validate(Car car) {
        return dataValidation.isNonEmptyString(car.getModel()) && dataValidation.isIntegerInRange(car.getYearRelease(), 1886, Integer.MAX_VALUE)
                && dataValidation.isDoubleInRange(car.getPower(), 0, Double.MAX_VALUE);
    }
}

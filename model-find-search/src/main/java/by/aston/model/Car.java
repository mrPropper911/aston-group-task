package by.aston.model;

import by.aston.utils.NumberUtils;
import by.aston.validator.CarValidator;
import by.aston.view.DataInput;
import by.aston.view.RandomObjectGenerator;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class Car implements Serializable, Comparable<Car>,
        ObjectBuilder<Car> {
    private final static String PATH_RANDOM_CAR_MODELS = "randomazers/models.txt";
    private static final long SerialVersionUID = 42L;
    private Integer power;
    private String model;
    private Integer yearRelease;

    public Car() {
    }

    public Car(Builder builder) {
        this.power = builder.power;
        this.model = builder.model;
        this.yearRelease = builder.yearRelease;
    }

    public Integer getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public Integer getYearRelease() {
        return yearRelease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(power, car.power) && Objects.equals(model, car.model)
                && Objects.equals(yearRelease, car.yearRelease);
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, model, yearRelease);
    }

    @Override
    public java.lang.String toString() {
        return "Машина: " + "модель - " + model + ", мощность - " + power
                + ", год выпуска - " + yearRelease;
    }

    @Override
    public int compareTo(Car car) {
        var modelsCompareResult = this.model.compareTo(car.model);
        if (modelsCompareResult != 0) {
            return modelsCompareResult;
        }

        var powersCompareResult = Integer.compare(this.power, car.power);
        if (powersCompareResult != 0) {
            return powersCompareResult;
        }

        return Integer.compare(this.yearRelease, car.yearRelease);
    }

    @Override
    public Optional<Car> buildFromInput(DataInput input) {
        try {
            input.showMessage("Введите модель автомобиля: ");
            var model = input.readLine();

            input.showMessage("Введите мощность автомобиля: ");
            var power = NumberUtils.parseInt(input.readLine());

            input.showMessage("Введите год производства автомобиля: ");
            var year = NumberUtils.parseInt(input.readLine());

            var car = new Builder()
                    .model(model)
                    .power(power)
                    .yearRelease(year)
                    .build();

            CarValidator.validate(car);
            return Optional.of(car);

        } catch (NumberFormatException exception) {
            input.showErrorMessage("Ошибка формата числа: " + exception.getMessage());
        } catch (IllegalArgumentException exception) {
            input.showErrorMessage("Ошибка валидации: " + exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Car createRandomObject() {
        var random = new Random();
        var stringModelList = RandomObjectGenerator
                .getRandomObjectsByCount(PATH_RANDOM_CAR_MODELS, 1);
        return new Car.Builder()
                .model(stringModelList.get(0))
                .power(random.nextInt(300) + 100)
                .yearRelease(random.nextInt(100) + 1886)
                .build();
    }

    @Override
    public Integer getValue() {
        return power;
    }

    public static class Builder {
        private String model;
        private Integer power;
        private Integer yearRelease;

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder power(Integer power) {
            this.power = power;
            return this;
        }

        public Builder yearRelease(Integer yearRelease) {
            this.yearRelease = yearRelease;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
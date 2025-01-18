package by.aston.model;

import by.aston.utils.NumberUtils;
import by.aston.validator.VegetableValidator;
import by.aston.view.DataInput;
import by.aston.view.RandomObjectGenerator;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class Vegetable implements Serializable, Comparable<Vegetable>,
        ObjectBuilder<Vegetable> {
    private final static String PATH_RANDOM_COLORS = "randomazers/colors.txt";
    private final static String PATH_RANDOM_VEGETABLES_NAME = "randomazers/vegetables.txt";
    private static final long SerialVersionUID = 42L;
    private String type;
    private Double weight;
    private String color;

    public Vegetable() {
    }

    public Vegetable(Builder builder) {
        this.weight = (builder.weight);
        this.type = builder.type;
        this.color = builder.color;
    }

    public String getType() {
        return type;
    }

    public Double getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vegetable vegetable = (Vegetable) o;
        return Objects.equals(type, vegetable.type)
                && Objects.equals(weight, vegetable.weight)
                && Objects.equals(color, vegetable.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, weight, color);
    }

    @Override
    public String toString() {
        return "Корнеплод: " + "тип - " + type + ", вес - "
                + weight + ", цвет - " + color;
    }

    @Override
    public int compareTo(Vegetable vegetable) {
        var typesCompareResult = this.type.compareTo(vegetable.type);
        if (typesCompareResult != 0) {
            return typesCompareResult;
        }

        var colorsCompareResult = this.color.compareTo(vegetable.color);
        if (colorsCompareResult != 0) {
            return colorsCompareResult;
        }

        return Double.compare(this.weight, vegetable.weight);
    }

    @Override
    public Optional<Vegetable> buildFromInput(DataInput input) {
        try {
            input.showMessage("Введите тип корнеплода: ");
            var type = input.readLine();

            input.showMessage("Введите вес корнеплода: ");
            var weight = NumberUtils.parseDouble(input.readLine());

            input.showMessage("Введите цвет корнеплода: ");
            var color = input.readLine();

            var vegetable = new Builder()
                    .type(type)
                    .weight(weight)
                    .color(color)
                    .build();

            VegetableValidator.validate(vegetable);
            return Optional.of(vegetable);

        } catch (NumberFormatException exception) {
            input.showErrorMessage("Ошибка формата числа: " +
                    exception.getMessage());
        } catch (IllegalArgumentException exception) {
            input.showErrorMessage("Ошибка валидации: " +
                    exception.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Vegetable createRandomObject() {
        var random = new Random();
        var randomNamesList = RandomObjectGenerator
                .getRandomObjectsByCount(PATH_RANDOM_VEGETABLES_NAME, 1);
        var randomColorsList = RandomObjectGenerator
                .getRandomObjectsByCount(PATH_RANDOM_COLORS, 1);
        return new Vegetable.Builder()
                .type(randomNamesList.get(0))
                .weight(random.nextDouble() * 10)
                .color(randomColorsList.get(0))
                .build();
    }

    @Override
    public Integer getValue() {
        return (int) Math.ceil(weight);
    }

    public static class Builder {
        private String type;
        private Double weight;
        private String color;

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder weight(Double weight) {
            this.weight = weight;
            return this;
        }

        public Vegetable build() {
            return new Vegetable(this);
        }
    }
}

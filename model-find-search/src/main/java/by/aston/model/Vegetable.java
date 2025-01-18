package by.aston.model;

import by.aston.utils.NumberUtils;
import by.aston.validator.VegetableValidator;
import by.aston.view.DataInput;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public class Vegetable implements Serializable, Comparable<Vegetable>, ObjectBuilder<Vegetable> {
    private static final long SerialVersionUID = 42L;
    private final String type;
    private final Double weight;
    private final String color;

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
        return Objects.equals(type, vegetable.type) && Objects.equals(weight, vegetable.weight)
                && Objects.equals(color, vegetable.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, weight, color);
    }

    @Override
    public java.lang.String toString() {
        return "Корнеплод: " + "тип - " + type + ", вес - "
                + weight + ", цвет - " + color;
    }

    @Override
    public int compareTo(Vegetable vegetable) {
        int typesCompareResult = this.type.compareTo(vegetable.type);
        if (typesCompareResult != 0) {
            return typesCompareResult;
        }

        int colorsCompareResult = this.color.compareTo(vegetable.color);
        if (colorsCompareResult != 0) {
            return colorsCompareResult;
        }

        return Double.compare(this.weight - vegetable.weight, 0);
    }

    @Override
    public Optional<Vegetable> buildFromInput(DataInput input) {
        try {
            input.showMessage("Введите тип корнеплода: ");
            String type = input.readLine();

            input.showMessage("Введите вес корнеплода: ");
            double weight = NumberUtils.parseDouble(input.readLine());

            input.showMessage("Введите цвет корнеплода: ");
            String color = input.readLine();

            Vegetable vegetable = new Builder()
                    .type(type)
                    .weight(weight)
                    .color(color)
                    .build();

            VegetableValidator.validate(vegetable);
            return Optional.of(vegetable);

        } catch (NumberFormatException exception) {
            input.showErrorMessage("Ошибка формата числа: " + exception.getMessage());
        } catch (IllegalArgumentException exception) {
            input.showErrorMessage("Ошибка валидации: " + exception.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public String write(Object obj) {
        Vegetable vegetable = (Vegetable) obj;
        return "Vegetable:" + vegetable.getType() + ","
                + vegetable.getWeight() + "," + vegetable.getColor();
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

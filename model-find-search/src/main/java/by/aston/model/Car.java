package by.aston.model;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable, Comparable<Car>{
    private static final long SerialVersionUID = 42L;
    private final Integer power;
    private final String model;
    private final Integer yearRelease;

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
        return Objects.equals(power, car.power) && Objects.equals(model, car.model) && Objects.equals(yearRelease, car.yearRelease);
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
        int modelsCompareResult = this.model.compareTo(car.model);
        if(modelsCompareResult != 0){ return modelsCompareResult;}

        int powersCompareResult = this.power - car.power;
        if(powersCompareResult != 0){ return powersCompareResult;}

        return this.yearRelease - car.yearRelease;
    }

    public static class Builder{
        private String model;
        private Integer power;
        private Integer yearRelease;

        public Builder model(String model){
            this.model = model;
            return this;
        }

        public Builder power(Integer power){
            this.power = power;
            return this;
        }

        public Builder yearRelease(Integer yearRelease){
            this.yearRelease = yearRelease;
            return this;
        }

        public Car build(){
            return new Car(this);
        }
    }
}
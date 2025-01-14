package by.aston.model;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable, Comparable<Car>
    private final Integer power;
    private final String model;
    private final Integer yearRelease;

    public Car(Builder builder) {
        this.power = builder.power;
        this.yearRelease = builder.yearRelease;
        this.model = builder.model;
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
        return Objects.equals(power, car.power) &&
                Objects.equals(yearRelease, car.yearRelease) &&
                Objects.equals(model, car.model);
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
        if(this.model.compareTo(car.model) < 0) {return -1;}
        else if(this.model.compareTo(car.model) > 0) {return 1;}
        else if (this.power - car.power < 0) {return -1;}
        else if (this.power - car.power > 0) {return 1;}
        else return Integer.compare(this.yearRelease - car.yearRelease, 0);
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
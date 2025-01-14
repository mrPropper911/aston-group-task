package by.aston.model;

import java.io.Serializable;
import java.util.Objects;

public class Vegetable implements Serializable, Comparable<Vegetable> {
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
        return Objects.equals(weight, vegetable.weight) &&
                Objects.equals(type, vegetable.type) &&
                Objects.equals(color, vegetable.color);
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
        if(this.type.compareTo(vegetable.type) < 0) {return -1;}
        else if(this.type.compareTo(vegetable.type) > 0) {return 1;}
        else if (this.color.compareTo(vegetable.color) < 0) {return -1;}
        else if (this.color.compareTo(vegetable.color) > 0) {return 1;}
        else return Double.compare(this.weight.compareTo(vegetable.weight), 0);
    }

    public static class Builder{
        private String type;
        private Double weight;
        private String color;

        public Builder type(String type){
            this.type = type;
            return this;
        }

        public Builder color(String color){
            this.color = color;
            return this;
        }

        public Builder weight(Double weight){
            this.weight = weight;
            return this;
        }

        public Vegetable build(){
            return new Vegetable(this);
        }
    }
}

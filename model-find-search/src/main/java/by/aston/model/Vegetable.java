package by.aston.model;

public class Vegetable {
    private String type;
    private Double weight;
    private String color;

    public Vegetable(String type, Double weight, String color) {
        this.type = type;
        this.weight = weight;
        this.color = color;
    }

    public String getType() {
        return type;
    }
}

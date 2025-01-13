package by.aston.model;

public class Car {
    private Integer power;
    private String model;
    private Integer yearRelease;

    public Car(Integer power, String model, Integer yearRelease) {
        this.power = power;
        this.model = model;
        this.yearRelease = yearRelease;
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
}

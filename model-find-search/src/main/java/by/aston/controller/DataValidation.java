package by.aston.controller;

public class DataValidation {

    public boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isValidDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isNonEmptyString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public boolean isIntegerInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public boolean isDoubleInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }
}

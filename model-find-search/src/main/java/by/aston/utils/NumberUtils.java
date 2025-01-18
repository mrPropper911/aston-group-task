package by.aston.utils;

/**
 * Утилитный класс для работы с числами.
 */
public class NumberUtils {

    //todo лишний код

    /**
     * Проверяет, является ли объект четным числом.
     *
     * @param obj объект для проверки
     * @return true, если объект является четным числом;иначе false
     */
    public static boolean isEven(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).intValue() % 2 == 0;
        }
        return false;
    }

    //todo убрать эту реализацию
    public static int parseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue; //todo need throw new exception
        }
    }

    public static int parseInt(String value) throws NumberFormatException{
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ошибка ввода. Необходимо ввести числовое значение: " + value);
        }
    }
}

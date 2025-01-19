package by.aston.utils;

/**
 * Утилитный класс для работы с числами.
 */
public class NumberUtils {
    public static int parseInt(String value) throws NumberFormatException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ошибка ввода целого числа." +
                    " Необходимо ввести корректное значение: " + value);
        }
    }

    public static double parseDouble(String value) throws NumberFormatException {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ошибка ввода вещественного числа." +
                    " Необходимо ввести корректное значение: " + value);
        }
    }
}

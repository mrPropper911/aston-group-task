package by.aston.utils;

/**
 * Утилитный класс для работы с числами.
 */
public class NumberUtils {

    /**
     * Проверяет, является ли объект четным числом.
     *
     * @param obj объект для проверки
     * @return true, если объект является четным числом; иначе false
     */
    public static boolean isEven(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).intValue() % 2 == 0;
        }
        return false;
    }
}

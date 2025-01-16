package by.aston.helper;

import by.aston.model.Book;

public class BookValidator {

    public static boolean validate(Book book) {
        if (book == null) {
            System.out.println("Объект Book не может быть null.");
            return false;
        }

        if (!validateTitle(book.getTitle())) {
            System.out.println("Некорректное название книги: " + book.getTitle());
            return false;
        }

        if (!validateAuthor(book.getAuthor())) {
            System.out.println("Некорректное имя автора: " + book.getAuthor());
            return false;
        }

        if (!validateNumberPages(book.getNumberPages())) {
            System.out.println("Некорректное количество страниц: " + book.getNumberPages());
            return false;
        }

        return true;
    }

    private static boolean validateTitle(String title) {
        return title != null && !title.isBlank();
    }

    private static boolean validateAuthor(String author) {
        return author != null && !author.isBlank();
    }

    private static boolean validateNumberPages(Integer numberPages) {
        return numberPages != null && numberPages > 0;
    }
}
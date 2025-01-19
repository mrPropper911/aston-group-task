package by.aston.validator;

import by.aston.model.Book;

public class BookValidator {
    public static void validate(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Объект Book не может быть null.");
        }
        validateTitle(book.getTitle());
        validateAuthor(book.getAuthor());
        validateNumberPages(book.getNumberPages());
    }

    public static void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название книги не может быть пустой.");
        }
    }

    public static void validateAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("У книги должен быть автор.");
        }
    }

    public static void validateNumberPages(Integer numberPages) {
        if (numberPages == null || numberPages <= 0) {
            throw new IllegalArgumentException("Количество страниц у книги должно быть больше нуля.");
        }
    }
}

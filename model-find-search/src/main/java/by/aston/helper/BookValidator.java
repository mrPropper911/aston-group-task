package by.aston.helper;

import by.aston.controller.DataValidation;
import by.aston.model.Book;

public class BookValidator {
    private final DataValidation dataValidation = new DataValidation();

    public boolean validate(Book book) {
//        return dataValidation.isNonEmptyString(book.getTitle()) && dataValidation.isNonEmptyString(book.getAuthor())
//                && dataValidation.isIntegerInRange(book.getPages(), 1, Integer.MAX_VALUE);
        return true;
    }
}

package by.aston.model;

public class Book {
    private String title;
    private String author;
    private Integer numberPages;

    public Book(String title, String author, Integer numberPages) {
        this.title = title;
        this.author = author;
        this.numberPages = numberPages;
    }

    public Integer getNumberOfPages() {
        return numberPages;
    }

    @Override
    public String toString() {
        return "книга " + numberPages;
    }
}

package by.aston.model;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable, Comparable<Book>{
    private final String title;
    private final String author;
    private final Integer numberPages;

    Book(Builder builder){
        this.numberPages = builder.numberPages;
        this.title = builder.title;
        this.author = builder.author;

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getNumberPages() {
        return numberPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(numberPages, book.numberPages) &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, numberPages);
    }

    @Override
    public String toString() {
        return "Книга: " + "название - " + title + ", автор - "
                + author + ", количество страниц - " + numberPages;
    }

    @Override
    public int compareTo(Book book) {
        if(this.author.compareTo(book.author) < 0) {return -1;}
        else if(this.author.compareTo(book.author) > 0) {return 1;}
        else if (this.title.compareTo(book.title) < 0) {return -1;}
        else if (this.title.compareTo(book.title) > 0) {return 1;}
        else return Integer.compare(this.numberPages.compareTo(book.numberPages), 0);
    }

    public static class Builder{
        private String title;
        private String author;
        private Integer numberPages;

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder author(String author){
            this.author = author;
            return this;
        }

        public Builder numberPages(Integer numberPages){
            this.numberPages = numberPages;
            return this;
        }

        public Book build(){
            return new Book(this);
        }
    }
}

package by.aston.utils;

import by.aston.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class FileUtilsTest{

    @Test
    void testGeters(){
        String booksFilePath = FileUtils.getBooksFilePath();
        String carsFilePath = FileUtils.getCarsFilePath();
        String vegetablesFilePath = FileUtils.getVegetablesFilePath();
        String propertiesFilePath = FileUtils.getPropertiesFilePath();

        assertThat(booksFilePath).isEqualTo("src/main/resources/data.Books.txt");
        assertThat(carsFilePath).isEqualTo("src/main/resources/data.Cars.txt");
        assertThat(vegetablesFilePath).isEqualTo("src/main/resources/data.Vegetables.txt");
        assertThat(propertiesFilePath).isEqualTo("src/main/resources/properties.txt");
    }


    @Test
    void testWriteAndRreadObject() {
        try{
            File file = new File(FileUtils.getBooksFilePath());
            if(file.delete()) {
                file.createNewFile();

                Book writedBookObject = new Book.Builder().title("Герой нашего времени").author("М. Ю. Лермонтов").numberPages(133).build();
                FileUtils.writeObject(FileUtils.getBooksFilePath(), writedBookObject);

                Book ridingBookObject = FileUtils.<Book>readObject(FileUtils.getBooksFilePath());
                Book test = new Book.Builder().title("Капитал").author("Маркс").numberPages(533).build();

                assertThat(writedBookObject).isEqualTo(ridingBookObject);
            }
        }catch (IOException | ClassNotFoundException e){ e.getMessage();}
    }

    @Test
    void testWriteAndRreadCollection() {
        try{
            File file = new File(FileUtils.getBooksFilePath());
            if(file.delete()) {
                file.createNewFile();

                List<Book> writedCollection = new ArrayList<Book>();
                List<Book> ridingC0lllection = null;

                writedCollection.add(new Book.Builder().title("Герой нашего времени").author("М. Ю. Лермонтов").numberPages(133).build());
                writedCollection.add(new Book.Builder().title("Война и мир").author("Л. Н. Толстой").numberPages(867).build());
                writedCollection.add(new Book.Builder().title("Преступление и наказание").author("Ф. М. Достоевский").numberPages(266).build());
                writedCollection.add(new Book.Builder().title("Евгений Онегин").author("А. С. Пушкин").numberPages(146).build());
                writedCollection.add(new Book.Builder().title("Идиот").author("Ф. М. Достоевский").numberPages(498).build());

                FileUtils.writeCollection(FileUtils.getBooksFilePath(), writedCollection);

                Book writedBookObject_1 = new Book.Builder().title("Герой нашего времени").author("М. Ю. Лермонтов").numberPages(133).build();
                Book writedBookObject_2 = new Book.Builder().title("Война и мир").author("Л. Н. Толстой").numberPages(867).build();
                Book writedBookObject_3 = new Book.Builder().title("Преступление и наказание").author("Ф. М. Достоевский").numberPages(266).build();
                Book writedBookObject_4 = new Book.Builder().title("Евгений Онегин").author("А. С. Пушкин").numberPages(146).build();
                Book writedBookObject_5 = new Book.Builder().title("Идиот").author("Ф. М. Достоевский").numberPages(498).build();

                ridingC0lllection = FileUtils.readCollection(FileUtils.getBooksFilePath());

                assertThat(ridingC0lllection.get(0)).isEqualTo(writedBookObject_1);
                assertThat(ridingC0lllection.get(1)).isEqualTo(writedBookObject_2);
                assertThat(ridingC0lllection.get(2)).isEqualTo(writedBookObject_3);
                assertThat(ridingC0lllection.get(3)).isEqualTo(writedBookObject_4);
                assertThat(ridingC0lllection.get(4)).isEqualTo(writedBookObject_5);
            }
        }catch (IOException | ClassNotFoundException e){ e.getMessage();}
    }
}
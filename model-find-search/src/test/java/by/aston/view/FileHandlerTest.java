package by.aston.view;

import by.aston.model.Book;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileHandlerTest {

    @Test
    void testWriteCollectionToFileWithAppend() {
        try {
            File file = new File("src/main/resources/data/Books.txt");
            if (file.delete()) {
                file.createNewFile();

                List<Book> writedCollectionFirst = new ArrayList<Book>();
                List<Book> writedCollectionSecond = new ArrayList<Book>();
                List<Book> ridingC0lllection = null;

                writedCollectionFirst.add(new Book.Builder().title("Герой нашего времени").author("М. Ю. Лермонтов").numberPages(133).build());
                writedCollectionFirst.add(new Book.Builder().title("Война и мир").author("Л. Н. Толстой").numberPages(867).build());
                writedCollectionFirst.add(new Book.Builder().title("Преступление и наказание").author("Ф. М. Достоевский").numberPages(266).build());

                writedCollectionSecond.add(new Book.Builder().title("Евгений Онегин").author("А. С. Пушкин").numberPages(146).build());
                writedCollectionSecond.add(new Book.Builder().title("Идиот").author("Ф. М. Достоевский").numberPages(498).build());

                FileHandler.writeCollectionToFileWithAppend("src/main/resources/data/Books.txt", writedCollectionFirst);
                FileHandler.writeCollectionToFileWithAppend("src/main/resources/data/Books.txt", writedCollectionSecond);

                Book writedBookObject_1 = new Book.Builder().title("Герой нашего времени").author("М. Ю. Лермонтов").numberPages(133).build();
                Book writedBookObject_2 = new Book.Builder().title("Война и мир").author("Л. Н. Толстой").numberPages(867).build();
                Book writedBookObject_3 = new Book.Builder().title("Преступление и наказание").author("Ф. М. Достоевский").numberPages(266).build();
                Book writedBookObject_4 = new Book.Builder().title("Евгений Онегин").author("А. С. Пушкин").numberPages(146).build();
                Book writedBookObject_5 = new Book.Builder().title("Идиот").author("Ф. М. Достоевский").numberPages(498).build();

                ridingC0lllection = FileHandler.readCollectionFromFile("src/main/resources/data/Books.txt", Book.class);

                assertThat(ridingC0lllection.get(0)).isEqualTo(writedBookObject_1);
                assertThat(ridingC0lllection.get(1)).isEqualTo(writedBookObject_2);
                assertThat(ridingC0lllection.get(2)).isEqualTo(writedBookObject_3);
                assertThat(ridingC0lllection.get(3)).isEqualTo(writedBookObject_4);
                assertThat(ridingC0lllection.get(4)).isEqualTo(writedBookObject_5);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }
    }
}
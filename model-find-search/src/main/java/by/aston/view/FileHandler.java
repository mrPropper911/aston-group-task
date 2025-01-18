package by.aston.view;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static <T> List<T> readCollectionFromFile(String filePath,
                                                     Class<T> clazz) throws IOException, ClassNotFoundException {
        List<T> collection = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            throw new FileNotFoundException("Файл не существует или это не файл: " + filePath);
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object object = inputStream.readObject();
                    if (clazz.isInstance(object)) {
                        collection.add(clazz.cast(object));
                    } else {
                        throw new InvalidClassException("Объект из файла не соответствует указанному типу");
                    }
                } catch (EOFException exception) {
                    break; //end of file, stop reading
                }
            }
        }
        return collection;
    }

    public static <T> void writeCollectionToFile(String filePath, List<T> collection) throws IOException {
        File file = new File(filePath);

        if (file.exists() && !file.canWrite()) {
            throw new IOException("Файл недоступен для записи: " + filePath);
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            for (T object : collection) {
                outputStream.writeObject(object);
            }
        }
    }

    public static <T> void writeCollectionToFileWithAppend(String filePath, List<T> collection) throws IOException {
        File file = new File(filePath);

        if (file.exists() && !file.canWrite()) {
            throw new IOException("Файл недоступен для записи: " + filePath);
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file, true))) {
            for (T object : collection) {
                outputStream.writeObject(object);
            }
        }
    }
}
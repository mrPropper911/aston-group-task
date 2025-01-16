package by.aston.view;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static <T> List<T> readCollectionFromFile(String filePath, Class<T> clazz) throws IOException, ClassNotFoundException {
        List<T> collection = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    T obj = (T) ois.readObject();
                    collection.add(obj);
                } catch (EOFException e) {
                    break;
                }
            }
        }
        return collection;
    }

    public static <T> void writeCollectionToFile(String filePath, List<T> collection) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (T obj : collection) {
                oos.writeObject(obj);
            }
        }
    }
}
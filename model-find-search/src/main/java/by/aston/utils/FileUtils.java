package by.aston.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final String BOOKS_FILE_PATH;
    private static final String CARS_FILE_PATH;
    private static final String VEGETABLES_FILE_PATH;
    private static final  String PROPERTIES_FILE_PATH = "src/main/resources/properties.txt";

    static {
        Path path = Paths.get(PROPERTIES_FILE_PATH);
        String fileContents = "";
        try {
            fileContents = Files.readString(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String[] stringFileContext = fileContents.split(System.lineSeparator());
        BOOKS_FILE_PATH = stringFileContext[0].split("=")[1];
        CARS_FILE_PATH = stringFileContext[1].split("=")[1];
        VEGETABLES_FILE_PATH = stringFileContext[2].split("=")[1];
    }

    public static String getBooksFilePath() {return BOOKS_FILE_PATH;}

    public static String getCarsFilePath() {return CARS_FILE_PATH;}

    public static String getVegetablesFilePath() {return VEGETABLES_FILE_PATH;}

    public static void writeObject(String filePath, Object obj) throws IOException {
        File f = new File(filePath);
        try {
            if (f.length() == 0){
                var oos = new ObjectOutputStream(new FileOutputStream(filePath, true));
                oos.writeObject(obj);
                oos.close();
            }
            else {
                var moos = new MyObjectOutputStream(new FileOutputStream(filePath, true));
                moos.close();
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static <T> void writeCollection(String filePath, List<T> collection) throws IOException {
        File f = new File(filePath);
        try{
            for (T obj : collection){
                if (f.length() == 0){
                    var oos = new ObjectOutputStream(new FileOutputStream(filePath, true));
                    oos.writeObject(obj);
                    oos.close();
                }
                else {
                    var moos = new MyObjectOutputStream(new FileOutputStream(filePath, true));
                    moos.writeObject(obj);
                    moos.close();
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static Object readObject(String filePath) throws IOException, ClassNotFoundException {
        Object object = null;
        try(var ois = new ObjectInputStream(new FileInputStream(filePath))){
            object = ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return object;
    }

    public static <T> List<T> readCollection(String filePath) throws IOException, ClassNotFoundException {
        List<T> collection = new ArrayList<>();
        try (var ois = new ObjectInputStream(new FileInputStream(filePath))){
            while (true) {
                try {
                    T obj = (T) ois.readObject();
                    collection.add(obj);
                } catch (EOFException e) {
                    break;
                }
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return collection;
    }
}
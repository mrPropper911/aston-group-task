package by.aston.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ObjectBuilderFactory {
    private final Map<Integer, ObjectBuilder<?>> builders = new HashMap<>();

    public ObjectBuilderFactory() {
        builders.put(1, new Car());
        builders.put(2, new Book.Builder().build());
        builders.put(3, new Vegetable.Builder().build());
    }

    public ObjectBuilder<?> getBuilder(int choice){
        return builders.get(choice);
    }

    public <T> ObjectBuilder<T> getFactory(Class<T> clazz){
        if (clazz == Car.class) {
            return (ObjectBuilder<T>) new Car();
        } else if (clazz == Book.class) {
            return (ObjectBuilder<T>) new Book();
        } else if (clazz == Vegetable.class) {
            return (ObjectBuilder<T>) new Vegetable();
        }
        throw new IllegalArgumentException("Неизвестный тип: " + clazz.getSimpleName());
    }

    public static <T> ObjectBuilder<T> getFactoryWithFields(Class<T> clazz, String key){
        if (clazz == Car.class) {
            return (ObjectBuilder<T>) new Car.Builder().model(key).build();
        } else if (clazz == Book.class) {
            return (ObjectBuilder<T>) new Book.Builder().title(key).build();
        } else {
            return (ObjectBuilder<T>) new Vegetable.Builder().type(key).build();
        }
    }

    public static <T> Comparator<T> getFactoryObjectComparator(Class<T> clazz){
        if (clazz == Car.class) {
            return (Comparator<T>) Comparator.comparing(obj -> ((Car) obj).getModel());//Поиск по model
        } else if (clazz == Book.class) {
            return (Comparator<T>) Comparator.comparing(obj -> ((Book) obj).getTitle());//Поиск по title
        } else if (clazz == Vegetable.class) {
            return (Comparator<T>) Comparator.comparing(obj -> ((Vegetable) obj).getType());//Поиск по type
        }
        return null;
    }

}

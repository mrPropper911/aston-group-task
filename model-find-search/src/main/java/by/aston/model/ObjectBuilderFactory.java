package by.aston.model;

import java.util.HashMap;
import java.util.Map;

public class ObjectBuilderFactory {
    private final Map<Integer, ObjectBuilder<?>> builders = new HashMap<>();

    public ObjectBuilderFactory() {
        builders.put(1, new Car.Builder().build());
        builders.put(2, new Book.Builder().build());
        builders.put(3, new Vegetable.Builder().build());
    }

    public ObjectBuilder<?> getBuilder(int choice){
        return builders.get(choice);
    }
}

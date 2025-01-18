package by.aston.model;

import by.aston.view.DataInput;

import java.util.Optional;

public interface ObjectBuilder<T> {
    Optional<T> buildFromInput(DataInput input);
    Integer getValue();
}

package ch04.ex03;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class MyControl {

    private static final String DEFAULT_VALUE = "hello";
    private StringProperty valueProperty = null;

    public StringProperty valueProperty() {
        if (Objects.isNull(valueProperty)) {
            valueProperty = new SimpleStringProperty(DEFAULT_VALUE);
        }
        return valueProperty;
    }

    public String getValue() {
        if (Objects.nonNull(valueProperty)) {
            return valueProperty.get();
        }
        return DEFAULT_VALUE;
    }

    public void setValue(String value) {
        if (Objects.nonNull(valueProperty)) {
            valueProperty.set(value);
        } else {
            valueProperty = new SimpleStringProperty(value);
        }
    }

}

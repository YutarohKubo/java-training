package ch04.ex02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class MyControl {
    private StringProperty valueProperty = null;
    private String value;

    public StringProperty valueProperty() {
        if (Objects.isNull(valueProperty)) {
            valueProperty = new SimpleStringProperty(value);
        }
        return valueProperty;
    }

    public String getValue() {
        if (Objects.nonNull(valueProperty)) {
            return valueProperty.get();
        }
        return value;
    }

    public void setValue(String value) {
        if (Objects.nonNull(valueProperty)) {
            valueProperty.set(value);
        } else {
            this.value = value;
        }
    }
}

package ch12.ex01;

public class ObjectNotFoundException extends Exception {

    private Object value;

    public ObjectNotFoundException (Object value) {
        super(value + " is not found");
        this.value = value;
    }
}

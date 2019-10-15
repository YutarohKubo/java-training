package ch03.ex07;

public class ScreenColor {

    private Object value;

    public ScreenColor (Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return value.equals(obj);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

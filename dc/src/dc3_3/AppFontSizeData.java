package dc3_3;

public class AppFontSizeData {

    private int size;
    private String name;

    public AppFontSizeData(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return name;
    }
}

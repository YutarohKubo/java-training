package ch09.ex09;

import java.util.Objects;

public class LabeledPoint {
    private String label;
    private int x;
    private int y;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LabeledPoint otherObj = (LabeledPoint) obj;
        return Objects.equals(label, otherObj.label) && x == otherObj.x && y == otherObj.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, x, y);
    }
}

package ch09.ex10;

import java.util.Objects;

public class LabeledPoint implements Comparable<LabeledPoint> {
    private String label;
    private int x;
    private int y;

    public LabeledPoint(String label, int x, int y) {
        this.label = label != null ? label : "";
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(LabeledPoint o) {
        return label.compareTo(o.label);
    }

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

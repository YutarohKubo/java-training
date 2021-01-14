package ch08.ex06;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Comparator;

public class Main {

    static Comparator<Point2D> comparator1 = (o1, o2) -> {
        if (o1.getX() - o2.getX() > 0) {
            return 1;
        } else if (o1.getX() - o2.getX() < 0) {
            return -1;
        } else {
            if (o1.getY() - o2.getY() > 0) {
                return 1;
            } else if (o1.getY() - o2.getY() < 0) {
                return -1;
            }
        }
        return 0;
    };

    static Comparator<Rectangle2D> comparator2 = (o1, o2) -> {
        if (o1.getX() - o2.getX() > 0) {
            return 1;
        } else if (o1.getX() - o2.getX() < 0) {
            return -1;
        } else {
            if (o1.getY() - o2.getY() > 0) {
                return 1;
            } else if (o1.getY() - o2.getY() < 0) {
                return -1;
            }
        }
        return 0;
    };

    public static void main(String[] args) {

    }

}

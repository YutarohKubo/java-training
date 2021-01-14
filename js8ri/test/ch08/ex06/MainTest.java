package ch08.ex06;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static ch08.ex06.Main.comparator1;
import static ch08.ex06.Main.comparator2;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class MainTest {

    class MyPoint2D extends Point2D {

        private double x;
        private double y;

        @Override
        public double getX() {
            return x;
        }

        @Override
        public double getY() {
            return y;
        }

        @Override
        public void setLocation(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    class MyRectangle extends Rectangle2D {

        private double x;
        private double y;
        private double w;
        private double h;

        @Override
        public void setRect(double x, double y, double w, double h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        @Override
        public int outcode(double x, double y) {
            return 0;
        }

        @Override
        public Rectangle2D createIntersection(Rectangle2D r) {
            return null;
        }

        @Override
        public Rectangle2D createUnion(Rectangle2D r) {
            return null;
        }

        @Override
        public double getX() {
            return x;
        }

        @Override
        public double getY() {
            return y;
        }

        @Override
        public double getWidth() {
            return w;
        }

        @Override
        public double getHeight() {
            return h;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }

    @Test
    public void testComparatorPoint2D_1() {
        List<Point2D> points = new ArrayList<>();
        List<Point2D> expectedPoints = new ArrayList<>();
        Point2D point1 = new MyPoint2D();
        point1.setLocation(4.0, 8.0);
        Point2D point2 = new MyPoint2D();
        point2.setLocation(6.0, 3.0);
        Point2D point3 = new MyPoint2D();
        point3.setLocation(2.0, 5.0);
        Point2D point4 = new MyPoint2D();
        point4.setLocation(4.0, 2.0);
        Point2D point5 = new MyPoint2D();
        point5.setLocation(8.0, 1.0);
        Point2D point6 = new MyPoint2D();
        point6.setLocation(2.0, 5.0);
        Point2D point7 = new MyPoint2D();
        point7.setLocation(5.0, 3.0);
        expectedPoints.add(point3);
        expectedPoints.add(point6);
        expectedPoints.add(point4);
        expectedPoints.add(point1);
        expectedPoints.add(point7);
        expectedPoints.add(point2);
        expectedPoints.add(point5);
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        points.add(point5);
        points.add(point6);
        points.add(point7);
        points.sort(comparator1);
        assertIterableEquals(expectedPoints, points);
    }

    @Test
    public void testComparatorRectangle2D_1() {
        List<Rectangle2D> points = new ArrayList<>();
        List<Rectangle2D> expectedPoints = new ArrayList<>();
        Rectangle2D point1 = new MyRectangle();
        point1.setRect(4.0, 8.0, 2.0, 2.0);
        Rectangle2D point2 = new MyRectangle();
        point2.setRect(6.0, 3.0, 2.0, 2.0);
        Rectangle2D point3 = new MyRectangle();
        point3.setRect(2.0, 5.0, 2.0, 2.0);
        Rectangle2D point4 = new MyRectangle();
        point4.setRect(4.0, 2.0, 2.0, 2.0);
        Rectangle2D point5 = new MyRectangle();
        point5.setRect(8.0, 1.0, 2.0, 2.0);
        Rectangle2D point6 = new MyRectangle();
        point6.setRect(2.0, 5.0, 2.0, 2.0);
        Rectangle2D point7 = new MyRectangle();
        point7.setRect(5.0, 3.0, 2.0, 2.0);
        expectedPoints.add(point3);
        expectedPoints.add(point6);
        expectedPoints.add(point4);
        expectedPoints.add(point1);
        expectedPoints.add(point7);
        expectedPoints.add(point2);
        expectedPoints.add(point5);
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        points.add(point5);
        points.add(point6);
        points.add(point7);
        points.sort(comparator2);
        assertIterableEquals(expectedPoints, points);
    }

}

package ch09.ex08;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainTest {

    @Test
    public void testNormalSort() {
        Point point1 = new Point(3, 8);
        Point point2 = new Point(-2, 4);
        Point point3 = new Point(7, -5);
        Point point4 = new Point(4, 9);
        Point point5 = new Point(3, 4);
        Point point6 = new Point(-5, -8);

        List<Point> expectedList = new ArrayList<>();
        expectedList.add(point6);
        expectedList.add(point2);
        expectedList.add(point5);
        expectedList.add(point1);
        expectedList.add(point4);
        expectedList.add(point3);

        List<Point> testList = new ArrayList<>();
        testList.add(point1);
        testList.add(point2);
        testList.add(point3);
        testList.add(point4);
        testList.add(point5);
        testList.add(point6);

        Collections.sort(testList);

        assertIterableEquals(expectedList, testList);
    }

    @Test
    public void testLargeNumberSort() {
        Point point1 = new Point(2147483640, 2147483628);
        Point point2 = new Point(-2147483608, 2147483634);
        Point point3 = new Point(2147483628, -2147483633);
        Point point4 = new Point(2147483640, -2147483639);
        Point point5 = new Point(-2147483645, 2147483618);
        Point point6 = new Point(2147483644, -2147483644);

        List<Point> expectedList = new ArrayList<>();
        expectedList.add(point5);
        expectedList.add(point2);
        expectedList.add(point3);
        expectedList.add(point4);
        expectedList.add(point1);
        expectedList.add(point6);

        List<Point> testList = new ArrayList<>();
        testList.add(point1);
        testList.add(point2);
        testList.add(point3);
        testList.add(point4);
        testList.add(point5);
        testList.add(point6);

        Collections.sort(testList);

        assertIterableEquals(expectedList, testList);
    }

}

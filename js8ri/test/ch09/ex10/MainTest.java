package ch09.ex10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class MainTest {

    @Test
    public void testLabeledPointSort() {
        LabeledPoint point1 = new LabeledPoint("hot dog", 3, 9);
        LabeledPoint point2 = new LabeledPoint("chocolate", 9, 3);
        LabeledPoint point3 = new LabeledPoint("energy drink", 4, 2);
        LabeledPoint point4 = new LabeledPoint("waffle", 95, 1);
        LabeledPoint point5 = new LabeledPoint("mashmarrow", 58, 39);

        List<LabeledPoint> expectedList = new ArrayList<>();
        expectedList.add(point2);
        expectedList.add(point3);
        expectedList.add(point1);
        expectedList.add(point5);
        expectedList.add(point4);

        List<LabeledPoint> testList = new ArrayList<>();
        testList.add(point1);
        testList.add(point2);
        testList.add(point3);
        testList.add(point4);
        testList.add(point5);

        Collections.sort(testList);

        assertIterableEquals(expectedList, testList);
    }

    @Test
    public void testLabeledPointSortContainNull() {
        LabeledPoint point1 = new LabeledPoint("hot dog", 3, 9);
        LabeledPoint point2 = new LabeledPoint("chocolate", 9, 3);
        LabeledPoint point3 = new LabeledPoint(null, 4, 2);
        LabeledPoint point4 = new LabeledPoint("waffle", 95, 1);
        LabeledPoint point5 = new LabeledPoint("mashmarrow", 58, 39);

        List<LabeledPoint> expectedList = new ArrayList<>();
        expectedList.add(point3);
        expectedList.add(point2);
        expectedList.add(point1);
        expectedList.add(point5);
        expectedList.add(point4);

        List<LabeledPoint> testList = new ArrayList<>();
        testList.add(point1);
        testList.add(point2);
        testList.add(point3);
        testList.add(point4);
        testList.add(point5);

        Collections.sort(testList);

        assertIterableEquals(expectedList, testList);
    }

}

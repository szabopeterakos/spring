package closestPairOfPoint;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EuclideanDistanceTest {
    EuclideanDistance ed = new EuclideanDistance();

    Point pointA = new Point();
    Point pointB = new Point();


    @Test
    public void With2_2DimensionTest() {

        List<Double> firstCordinates = new ArrayList<>();
        firstCordinates.add(0.);
        firstCordinates.add(0.);

        List<Double> secondCordinates = new ArrayList<>();
        secondCordinates.add(2.);
        secondCordinates.add(3.3);

        pointA.setIndex(13);
        pointB.setIndex(501);

        pointA.setCordinates(firstCordinates);
        pointB.setCordinates(secondCordinates);

        double distance = ed.CalculateDistanceWithList(pointA, pointB);
        assertEquals(distance, 3.858756);
    }

    @Test
    public void With3_3DimensionTest() {
        List<Double> firstCordinates = new ArrayList<>();
        firstCordinates.add(0.);
        firstCordinates.add(2.33);
        firstCordinates.add(2.33);

        List<Double> secondCordinates = new ArrayList<>();
        secondCordinates.add(2.);
        secondCordinates.add(3.3);
        secondCordinates.add(3.3);

        pointA.setIndex(13);
        pointB.setIndex(501);

        pointA.setCordinates(firstCordinates);
        pointB.setCordinates(secondCordinates);

        double distance = ed.CalculateDistanceWithList(pointA, pointB);
        assertEquals(distance, 2.425242);
    }

    @Test
    public void With4_2DimensionTest() {
        List<Double> firstCordinates = new ArrayList<>();
        firstCordinates.add(0.);
        firstCordinates.add(0.);
        firstCordinates.add(3.);
        firstCordinates.add(5.556);

        List<Double> secondCordinates = new ArrayList<>();
        secondCordinates.add(2.);
        secondCordinates.add(3.3);

        pointA.setIndex(13);
        pointB.setIndex(501);

        pointA.setCordinates(firstCordinates);
        pointB.setCordinates(secondCordinates);


        double distance = ed.CalculateDistanceWithList(pointA, pointB);
        assertEquals(distance, 7.399942);
    }

    @Test(expected = NullPointerException.class)
    public void NullException() {
        List<Double> firstCordinates = new ArrayList<>();
        List<Double> secondCordinates = new ArrayList<>();

        pointA.setIndex(13);
        pointB.setIndex(501);

        pointA.setCordinates(firstCordinates);
        pointB.setCordinates(secondCordinates);


        double distance = ed.CalculateDistanceWithList(pointA, pointB);
        assertEquals(distance, 3.3);
    }
}

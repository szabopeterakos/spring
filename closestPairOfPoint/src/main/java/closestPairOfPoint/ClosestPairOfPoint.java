package closestPairOfPoint;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ClosestPairOfPoint {

    /**
     * This application reads a text file with LineScanner.arrayCreator() where each line contains the coordinates
     * of a multidimensional point these values stored in List where lines are also List-s filled Double values.
     * <p>
     * Then find the closest pair of points in the list.
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * If the program has found the closest pair of points,
     * it send the line numbers and the coordinates of the two closest points.
     */

    private LineScanner lineScanner = LineScanner.getScannerInstance();
    private EuclideanDistance euclideanDistance = new EuclideanDistance();

    // find the closest pair of points
    private List<Point> findClosestDoubles(List<Point> pointList) {
        if (pointList.size() == 1) {
            throw new IllegalArgumentException("I have just found one point!");
        }

        Point firstPoint = pointList.get(0);
        Point secondPoint = pointList.get(1);
        double distance = Double.MAX_VALUE;

        for (int i = 0; i < pointList.size(); i++) {
            for (int j = i + 1; j < pointList.size(); j++) {
                double currentDistance = Math.abs(euclideanDistance.CalculateDistanceWithList(pointList.get(i), pointList.get(j)));

                if (currentDistance < distance) {
                    distance = currentDistance;
                    firstPoint = pointList.get(i);
                    secondPoint = pointList.get(j);
                }
            }
        }

        List<Point> resoult = new ArrayList<>();
        resoult.add(firstPoint);
        resoult.add(secondPoint);
        return resoult;
    }

    // created by the already founded result lists and their indexes
    private String printer(List<Point> lists) {
        StringBuilder answerBuilder = new StringBuilder();
        for (int i = 0; i < 2; i++) {

            answerBuilder.append(lists.get(i).getIndex() + ":");

            List<Double> currentList = lists.get(i).getCordinates();
            for (int j = 0; j < currentList.size(); j++) {
                BigDecimal currentValue = new BigDecimal(currentList.get(j)).setScale(0, RoundingMode.HALF_UP);

                answerBuilder.append(j < 1 ? currentValue : "\t" + currentValue);
            }
            answerBuilder.append("\n");
        }

        String printAbleString = answerBuilder.toString();
        return printAbleString;
    }

    // the main logic of the application
    public String mainLogic(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("This file is not exist: " + file);
        }

        List<Point> allList = lineScanner.arrayCreator(file); // a list form file
        List<Point> resultLists = findClosestDoubles(allList); //a list from allList, this include just the 2 proper list

        String resultString = printer(resultLists); // print proper values
        return resultString;
    }

}

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
     * Then found the closest pair of points in the list.
     * If the program has found the closest pair of points,
     * it send the line numbers and the coordinates of the two closest points.
     */

    private LineScanner lineScanner = LineScanner.getScannerInstance();
    private EuclideanDistance euclideanDistance = new EuclideanDistance();

    // find the closest pair of points
    private List<List<Double>> findClosestDoubles(List<List<Double>> lists) {
        if (lists.size() == 1) {
            throw new IllegalArgumentException("I have just found one point!");
        }

        List<Double> firstList = lists.get(0);
        List<Double> secondList = lists.get(1);
        double distance = Double.MAX_VALUE;

        for (int i = 0; i < lists.size(); i++) {
            for (int j = i + 1; j < lists.size(); j++) {
                double currentDistance = Math.abs(euclideanDistance.CalculateDistanceWithList(lists.get(i), lists.get(j)));

                if (currentDistance < distance) {
                    distance = currentDistance;
                    firstList = lists.get(i);
                    secondList = lists.get(j);
                }
            }
        }

        List<List<Double>> resoult = new ArrayList<>();
        resoult.add(firstList);
        resoult.add(secondList);
        return resoult;
    }

    // find the result lists indexes from root Double-Lists-List
    private int findNumberOfLines(List<Double> list, List<List<Double>> lists) {
        int number = -1;
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) == list) {
                number = i + 1;
            }
        }
        return number;
    }

    // created by the already founded result lists and their indexes
    private String printer(List<List<Double>> lists, int[] indexes) {
        StringBuilder answerBuilder = new StringBuilder();
        for (int i = 0; i < indexes.length; i++) {

            answerBuilder.append(indexes[i] + ":");

            List<Double> currentList = lists.get(i);
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

        List<List<Double>> allList = lineScanner.arrayCreator(file); // a list form file
        List<List<Double>> resultLists = findClosestDoubles(allList); //a list from allList, this include just the 2 proper list

        int[] indexes = new int[2];
        indexes[0] = findNumberOfLines(resultLists.get(0), allList); // find first proper list index
        indexes[1] = findNumberOfLines(resultLists.get(1), allList); // find second proper list index

        String resultString = printer(resultLists, indexes); // print proper values
        return resultString;
    }

}

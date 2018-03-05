package closestPairOfPoint;

import java.math.BigDecimal;
import java.util.List;

public class EuclideanDistance {

    // calculate distance with - euclidean logic for all dimension points
    public double CalculateDistanceWithList(List<Double> a, List<Double> b) {
        if (a == null || b == null || a.size() == 0 || b.size() == 0) {
            throw new NullPointerException("These parameter lists [a] or [b] is null or the size equals 0");
        }

        // calculate usable array length - with stater value: 0
        int arraySize = a.size() >= b.size() ? a.size() : b.size();
        double[] aa = new double[arraySize];
        double[] bb = new double[arraySize];

        for (int i = 0; i < a.size(); i++) {
            aa[i] = a.get(i);
        }
        for (int i = 0; i < b.size(); i++) {
            bb[i] = b.get(i);
        }

        // sum of every coordinates calculated value
        double sum = 0;

        // I. coordinates calculate with: (x2-x1)^2
        for (int i = 0; i < arraySize; i++) {
            double cordinateRaw = Math.pow(aa[i] - bb[i], 2);
            sum += cordinateRaw;
        }

        //  II. finished the logic
        double distance = Math.sqrt(sum);

        return new BigDecimal(distance).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}

package closestPairOfPoint;

import java.util.List;

public class Point {

    private int index;
    private List<Double> coordinates;

    public Point() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Double> getCordinates() {
        return coordinates;
    }

    public void setCordinates(List<Double> cordinates) {
        this.coordinates = cordinates;
    }
}

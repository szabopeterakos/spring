import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Spot{

    private String name = ".";
    private double f;
    private double g;
    private double h;

    private int x;
    private int y;
    private boolean way = true;

    public void setWay(boolean way) {
        this.way = way;
    }

    public boolean isWay() {

        return way;
    }

    private List<Spot> neighbors = new ArrayList<>();
    private Spot parent;


    public void setNeighbors(List<Spot> neighbors) {
        this.neighbors = neighbors;
    }

    public Spot getParent() {
        return parent;
    }

    public void setParent(Spot parent) {
        this.parent = parent;
    }

    //heuristic
    public static double heutistic(Spot start, Spot end) {
        double dx = Math.abs(start.x - end.x);
        double dy = Math.abs(start.y - end.y);
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void setNeighbors(Spot[][] grid) {
        List<Spot> spots = new ArrayList<>();
        Spot thisSpot = grid[getX()][getY()];
        int xx = thisSpot.getX();
        int yy = thisSpot.getY();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Spot currentSpot = grid[i][j];

                if (xx + 1 == i && yy == j) {
                    spots.add(currentSpot);
                }
                if (xx - 1 == i && yy == j) {
                    spots.add(currentSpot);
                }
                if (xx == i && yy + 1 == j) {
                    spots.add(currentSpot);
                }
                if (xx == i && yy - 1 == j) {
                    spots.add(currentSpot);
                }
            }
        }
        this.neighbors = spots;
    }

    public List<Spot> getNeighbors() {
        return neighbors;
    }

    public Spot(String name) {
        this.name = name;
    }

    public Spot() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Spot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setF(double f) {
        this.f = f;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setH(double h) {
        this.h = h;
    }

    public String getName() {

        return name;
    }

    public double getF() {
        return f;
    }

    public double getG() {
        return g;
    }

    public double getH() {
        return h;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spot spot = (Spot) o;
        return x == spot.x &&
                y == spot.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }
}

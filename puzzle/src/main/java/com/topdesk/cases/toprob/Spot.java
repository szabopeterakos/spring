package com.topdesk.cases.toprob;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Spot {

    private String name = ".";
    private double f;
    private double g;
    private double h;
    private int x;
    private int y;
    private boolean way = true;
    private List<Spot> neighbors = new ArrayList<>();
    private Spot parent;

    public Spot() {
    }

    public Spot(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public Spot(String name) {
        this.name = name;
    }

    public Spot(Coordinate coordinate) {
        this.y = coordinate.getY();
        this.x = coordinate.getX();
    }

    public List<Spot> getNeighbors() {
        return neighbors;
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

    public void setNeighbors(List<Spot> neighbors) {
        this.neighbors = neighbors;
    }

    public Spot getParent() {
        return parent;
    }

    public void setParent(Spot parent) {
        this.parent = parent;
    }

    public static double heutistic(Spot start, Spot end) {
        double dx = Math.abs(start.x - end.x);
        double dy = Math.abs(start.y - end.y);
        return Math.sqrt(dx * dx + dy * dy);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return x;
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

    public double getF() {
        return f;
    }

    public double getG() {
        return g;
    }

    public double getH() {
        return h;
    }

    public void setWay(boolean way) {
        this.way = way;
    }

    public boolean isWay() {
        return way;
    }

    @Override
    public String toString() {
        return name + " x:" + y + " y:" + x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) o;
            return x == coordinate.getX() && y == coordinate.getY();
        }

        Spot spot = (Spot) o;
        return x == spot.x &&
                y == spot.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

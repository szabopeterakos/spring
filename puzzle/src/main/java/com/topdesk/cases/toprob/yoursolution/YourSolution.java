package com.topdesk.cases.toprob.yoursolution;

import java.util.*;

import com.topdesk.cases.toprob.*;

public class YourSolution implements Solution {

    /**
     * This program can find the best route from the room to the kitchen
     * and back and pay attention to bugs.
     * <p>
     * When the best path had been founded, the wholeRouteCreator() walk through the grid,
     * if next step will be a bug, insert the current value.
     * It uses the A-Star algorithm to find the shortest way,
     * but just in a static grid after that create a dynamic version.
     * It can recognise the current grid value is a possible way or not.
     */

    @Override
    public List<Instruction> solve(Grid grid, int time) {
        if (grid == null) throw new NullPointerException("The Grid is must be not null.");
        if (time < 0) throw new IllegalArgumentException("The time must be more than -1 : " + time);

        int gridWebHeight = grid.getHeight();
        int gridWebWidth = grid.getWidth();
        Set<Coordinate> holes = grid.getHoles(); // with Coordinates
        Set<Spot> holeSpots = new HashSet<>(); // with Spots
        Spot bug = new Spot(grid.getBug(time)); // a bug
        List<Instruction> instructionList = new ArrayList<>(); // instructions
        Spot kitchen = new Spot(grid.getKitchen().getY(), grid.getKitchen().getX());
        Spot room = new Spot(grid.getRoom().getY(), grid.getRoom().getX());
        Spot[][] gridWeb = new Spot[gridWebHeight][gridWebWidth]; // grid with Spots

        // holes coordinate to Spot
        for (Coordinate c : holes) {
            holeSpots.add(new Spot(c));
        }

        // fill myGrid with routes, kitchen, room
        for (int i = 0; i < gridWeb.length; i++) {
            for (int j = 0; j < gridWeb[i].length; j++) {
                // kitchen to grid
                if (i == kitchen.getX() && j == kitchen.getY()) {
                    gridWeb[i][j] = kitchen;
                    // room to grid
                } else if (i == room.getX() && j == room.getY()) {
                    gridWeb[i][j] = room;
                } else {
                    gridWeb[i][j] = new Spot(i, j);
                }
            }
        }

        // fill myGrid with holes
        for (Spot[] arr : gridWeb) {
            for (Spot c : arr) {
                if (holeSpots.contains(c)) {
                    c.setWay(false);
                }
            }
        }

        // maze give an optimal route without bug checking from kitchen to room.
        List<Spot> pathOneWay = mazeAlgorithm(gridWeb, room, kitchen);

        // calculate a full path a-b wait in a kitchen and b-a with no bugs.
        List<Spot> path = wholeRouteCreator(pathOneWay);

        // calculate from whole path to real path with bugs and convert to Instructions list.
        instructionList = realtimeWalker(path, grid, time);

        return instructionList;
    }

    public List<Spot> mazeAlgorithm(Spot[][] aGrid, Spot start, Spot end) {
        List<Spot> openSet = new ArrayList<>();
        List<Spot> closedSet = new ArrayList<>();
        List<Spot> path = new ArrayList<>();

        openSet.add(start);

        // set neighbors
        for (int i = 0; i < aGrid.length; i++) {
            for (int j = 0; j < aGrid[i].length; j++) {
                aGrid[i][j].setNeighbors(aGrid);
            }
        }

        // find the best route
        while (openSet.size() > 0) {

            int winner = 0;
            for (int i = 0; i < openSet.size(); i++) {
                if (openSet.get(i).getF() < openSet.get(winner).getF()) {
                    winner = i;
                }
            }

            Spot currentSpot = openSet.get(winner);

            // when it has founded
            if (currentSpot.equals(end)) {
                Spot temp = currentSpot;
                path.add(temp);

                while (temp.getParent() != null) {
                    path.add(temp.getParent());
                    temp = temp.getParent();
                }
                return path;
            }

            // recheck possibilities
            openSet.remove(currentSpot);
            closedSet.add(currentSpot);

            // query current neighbors //
            List<Spot> currentNeighborsList = currentSpot.getNeighbors();

            // find the next point
            for (int i = 0; i < currentNeighborsList.size(); i++) {
                Spot currentNeighbor = currentNeighborsList.get(i);

                if (!closedSet.contains(currentNeighbor) && currentNeighbor.isWay()) {
                    double tempG = Spot.heutistic(start, currentNeighbor);

                    if (!openSet.contains(currentNeighbor)) {
                        openSet.add(currentNeighbor);
                    } else if (tempG >= currentNeighbor.getG()) {
                        continue;
                    }

                    currentNeighbor.setG(tempG);
                    currentNeighbor.setH(Spot.heutistic(currentNeighbor, end));
                    currentNeighbor.setF(currentNeighbor.getG() + currentNeighbor.getH());
                    currentNeighbor.setParent(currentSpot);
                }
            }

        }

        // if no solution
        System.out.println("NO SOLUTION");
        return path;
    }

    private List<Spot> wholeRouteCreator(List<Spot> path) {
        int sandwichTime = 5;
        Collections.reverse(path); // A-B
        List<Spot> allSpots = new ArrayList<>();
        allSpots.addAll(path); // add A-b route

        for (int i = 0; i < sandwichTime; i++) {
            allSpots.add(path.get(path.size() - 1)); // add current value 5 times
        }

        Collections.reverse(path); // B-A
        allSpots.addAll(path); // add B-A route

        return allSpots;
    }

    private List<Instruction> instructionsParser(List<Spot> allSpots) {

        List<Instruction> instructionList = new ArrayList<>();

        for (int i = 1; i < allSpots.size(); i++) {
            Spot current = allSpots.get(i);

            if (allSpots.get(i - 1).getX() == current.getX()) {
                if (allSpots.get(i - 1).getY() < current.getY()) {
                    instructionList.add(Instruction.EAST);
                }
                if (allSpots.get(i - 1).getY() > current.getY()) {
                    instructionList.add(Instruction.WEST);
                }
                if (allSpots.get(i - 1).getY() == current.getY()) {
                    instructionList.add(Instruction.PAUSE);
                }
            }

            if (allSpots.get(i - 1).getY() == current.getY()) {
                if (allSpots.get(i - 1).getX() < current.getX()) {
                    instructionList.add(Instruction.SOUTH);
                }
                if (allSpots.get(i - 1).getX() > current.getX()) {
                    instructionList.add(Instruction.NORTH);
                }
            }
        }

        return instructionList;
    }

    private void gui(Spot[][] gridWeb, Spot room, Spot kitchen, Set<Spot> holeSpots, Spot bug) {
        System.out.println();
        for (int i = 0; i < gridWeb.length; i++) {
            for (int j = 0; j < gridWeb[i].length; j++) {
                Spot current = gridWeb[i][j];
                if (current.equals(room)) { // the room
                    System.out.print("R ");
                } else if (current.equals(kitchen)) { // the kitchen
                    System.out.print("K ");
                } else if (holeSpots.contains(current)) { // holes
                    System.out.print("O ");
                } else if (bug.equals(current)) { // first bug appearance
                    System.out.print("x ");
                } else {
                    System.out.print(". "); // clear route
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private List<Instruction> realtimeWalker(List<Spot> route, Grid grid, int time) {
        List<Spot> currentRout = route;
        int i = 0;

        while (i < currentRout.size() - 2) {

            Coordinate bugHere = grid.getBug(i + 1 + time); // next bug appearance
            Spot currentStep = currentRout.get(i);
            Spot nextStep = currentRout.get(i + 1);

            //check bug is appeared my next move
            if (bugHere.getY() == nextStep.getX() && bugHere.getX() == nextStep.getY()) {
                currentRout.add(i, currentStep);
            }
            i++;
        }

        List<Instruction> list = instructionsParser(currentRout);
        return list;

    }

}

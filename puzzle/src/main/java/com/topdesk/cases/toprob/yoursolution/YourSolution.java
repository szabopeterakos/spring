package com.topdesk.cases.toprob.yoursolution;

import java.util.*;

import com.topdesk.cases.toprob.*;

public class YourSolution implements Solution {

    /**
     * This program can find the best route from room to kitchen and back;
     * It uses the A-Star algorithm to find the shortest way but just in a static grid.
     * It can recognise the current grid value can walk or not.
     * The dynamic bug appearing is not part of this solution.
     * <p>
     * The maze algorithm creates a list with Spots,
     * and the instructionsParser() can generate a List of Instruction from that.
     */

    @Override
    public List<Instruction> solve(Grid grid, int time) {

        //null, time exceptions
        if (grid == null) throw new NullPointerException("The Grid is must be not null.");
        if (time < 0) throw new IllegalArgumentException("The time must be more than -1 : " + time);

        int gridWebHeight = grid.getHeight();
        int gridWebWidth = grid.getWidth();

        Set<Coordinate> holes = grid.getHoles(); // with Coordinates
        Set<Spot> holeSpots = new HashSet<>(); // with Spots

        Spot bug = new Spot(grid.getBug(time)); // a bug

        List<Instruction> instructionList = new ArrayList<>(); // instructions

        // kitchen, room x,y name
        Spot kitchen = new Spot(grid.getKitchen().getY(), grid.getKitchen().getX());
        Spot room = new Spot(grid.getRoom().getY(), grid.getRoom().getX());
        kitchen.setName("K");
        room.setName("R");

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

        // GUI
        gui(gridWeb, room, kitchen, holeSpots, bug);

        // maze algorithm
        instructionList = mazeAlgorithm(gridWeb, room, kitchen);

        // check values
        System.out.println(instructionList);

        return instructionList;
    }

    public List<Instruction> mazeAlgorithm(Spot[][] aGrid, Spot start, Spot end) {
        List<Spot> openSet = new ArrayList<>();
        List<Spot> closedSet = new ArrayList<>();
        List<Spot> path = new ArrayList<>();
        int timer = 0;

        openSet.add(start);

        // set Neighbors
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

                return instructionsParser(path);
            }

            // re check possibilities
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
        return instructionsParser(path);

    }

    private List<Instruction> instructionsParser(List<Spot> path) {
        Collections.reverse(path);
        List<Spot> allSpots = new ArrayList<>();
        allSpots.addAll(path);
        Collections.reverse(path);
        allSpots.addAll(path);


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
            }
            if (allSpots.get(i - 1).getY() == current.getY()) {
                if (allSpots.get(i - 1).getX() < current.getX()) {
                    instructionList.add(Instruction.SOUTH);
                }
                if (allSpots.get(i - 1).getX() > current.getX()) {
                    instructionList.add(Instruction.NORTH);
                }
            }

            if (i == allSpots.size() / 2) {
                instructionList.add(Instruction.PAUSE);
                instructionList.add(Instruction.PAUSE);
                instructionList.add(Instruction.PAUSE);
                instructionList.add(Instruction.PAUSE);
                instructionList.add(Instruction.PAUSE);
            }
        }

//        System.out.println(allSpots);
//        System.out.println(instructionList);

        return instructionList;
    }

    private void gui(Spot[][] gridWeb, Spot room, Spot kitchen, Set<Spot> holeSpots, Spot bug) {
        System.out.println("");
        for (int i = 0; i < gridWeb.length; i++) {
            for (int j = 0; j < gridWeb[i].length; j++) {
                Spot current = gridWeb[i][j];
                if (current.equals(room)) {
                    System.out.print("R ");
                } else if (current.equals(kitchen)) {
                    System.out.print("K ");
                } else if (holeSpots.contains(current)) {
                    System.out.print("O ");
                } else if (bug.equals(current)) {
                    System.out.print("x ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("");
    }

}

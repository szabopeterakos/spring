import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AStar13 {

    public int mazeAlgorithm(Spot[][] aGrid, Spot start, Spot end) {
        List<Spot> openSet = new ArrayList<>();
        List<Spot> closedSet = new ArrayList<>();
        List<Spot> path = new ArrayList<>();
        int timer = 0;

        openSet.add(start);

        int opp = 1;
        for (int i = 0; i < aGrid.length; i++) {
            for (int j = 0; j < aGrid[i].length; j++) {
                aGrid[i][j].setNeighbors(aGrid);
                if (aGrid[i][j].getName().equals(".")) {
                    aGrid[i][j].setName("" + opp++);
                }
            }
        }

        while (openSet.size() > 0) {

            int winner = 0;
            for (int i = 0; i < openSet.size(); i++) {
                if (openSet.get(i).getF() < openSet.get(winner).getF()) {
                    winner = i;
                }
            }

            Spot currentSpot = openSet.get(winner);
            timer++;

            //end
            if (currentSpot.getName().equals(end.getName())) {
                Spot temp = currentSpot;
                path.add(temp);

                while (temp.getParent() != null) {
                    path.add(temp.getParent());
                    temp = temp.getParent();
                }

                System.out.println("DONE!");
                System.out.println(path);

                return timer;
            }

            openSet.remove(currentSpot);
            closedSet.add(currentSpot);

            // check neighbors //
            List<Spot> currentNeighborsList = currentSpot.getNeighbors();

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

        System.out.println("NO SOLUTION");
        return -1;

    }

    public static void main(String[] args) {
        Spot[][] grid = new Spot[7][7];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Spot(i, j);
            }
        }
        grid[0][0].setName("s");
        grid[5][5].setName("x");
        grid[0][3].setWay(false);
        grid[0][3].setName("||");
        grid[1][3].setWay(false);
        grid[1][3].setName("||");
        grid[2][3].setWay(false);
        grid[2][3].setName("||");

//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[i].length; j++) {
//                System.out.print(grid[i][j] + "\t");
//            }
//            System.out.println();
//        }

        AStar13 aStar13 = new AStar13();
        System.out.println(aStar13.mazeAlgorithm(grid, grid[0][0], grid[5][5]));
        System.out.println();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }

    }


}

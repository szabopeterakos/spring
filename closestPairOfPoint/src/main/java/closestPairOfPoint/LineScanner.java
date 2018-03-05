package closestPairOfPoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LineScanner {

    private static final LineScanner SCANNER = new LineScanner();

    private LineScanner() {
    }

    public static LineScanner getScannerInstance() {
        return SCANNER;
    }

    public List<Point> arrayCreator(File file) {

        List<Point> resultList = new ArrayList<>();

        // File scanner
        try (Scanner scn = new Scanner(file)) {
            
            int lineIndex = 1;
            while (scn.hasNextLine()) {

                String line = scn.nextLine();

                Point currentPoint = new Point();
                List<Double> currentList;

                // Line scanner
                try (Scanner scannerInner = new Scanner(line)) {
                    currentList = new ArrayList<>();
                    while (scannerInner.hasNextDouble()) {
                        currentList.add(scannerInner.nextDouble());
                    }
                }
                currentPoint.setCordinates(currentList);
                currentPoint.setIndex(lineIndex++);
                resultList.add(currentPoint);

            }

        } catch (FileNotFoundException e) {
            System.out.println("The file is not founded : " + file);
        }

        return resultList;
    }

}

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

    public List<List<Double>> arrayCreator(File file) {

        List<List<Double>> resultList = new ArrayList<>();

        // File scanner
        try (Scanner scn = new Scanner(file)) {

            while (scn.hasNextLine()) {

                String line = scn.nextLine();
                List<Double> currentList;

                // Line scanner
                try (Scanner scannerInner = new Scanner(line)) {

                    currentList = new ArrayList<>();
                    while (scannerInner.hasNextDouble()) {
                        currentList.add(scannerInner.nextDouble());
                    }

                }

                resultList.add(currentList);

            }

        } catch (FileNotFoundException e) {
            System.out.println("The file is not founded : " + file);
        }

        return resultList;
    }

}

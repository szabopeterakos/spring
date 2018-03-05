package closestPairOfPoint;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LineScannerTest {

    File inputfile2_8 = new File("src/main/resources/sample_input_2_8.tsv");
    String outputfile2_8 = "3:742431\t-772652\n" +
            "6:726622\t-813088\n";

    @Test
    public void CreateListWithPoints() {
        List<Point> pointList = LineScanner.getScannerInstance().arrayCreator(inputfile2_8);
        System.out.println(pointList);
        assertEquals(8,pointList.size());
    }

    @Test
    public void PointIndexIsCorrent() {
        List<Point> pointList = LineScanner.getScannerInstance().arrayCreator(inputfile2_8);
        System.out.println(pointList);
        assertEquals(1,pointList.get(0).getIndex());
        assertEquals(3,pointList.get(2).getIndex());
    }
}

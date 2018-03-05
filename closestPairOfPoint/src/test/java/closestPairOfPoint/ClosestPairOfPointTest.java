package closestPairOfPoint;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ClosestPairOfPointTest {

    LineScanner ls = LineScanner.getScannerInstance();
    ClosestPairOfPoint cpp = new ClosestPairOfPoint();

    File inputNotExist = new File("src/main/resources/notexist.tsv");

    File inputNoArgument = new File("src/main/resources/sample_input_0_1.tsv");

    File inputOneArgument = new File("src/main/resources/sample_input_0_1.tsv");

    File input1 = new File("src/main/resources/sample_input_100_100.tsv");
    String output1 = "48:459242\t392250\t-635527\t-938513\t116252\t535038\t-870127\t-20345\t19438.4\t80689\t-600704\t82936\t-942344\t972759\t555590\t236653\t-28483.2\t164409\t518598\t466178\t-469033\t-977764\t-16046\t-878885\t-15445.8\t176714\t-9386\t373020\t-344180\t-142027\t-781009\t-778787\t102453\t-244685\t-307648\t-413434\t985400\t422941\t302334\t312537\t-549432\t592034\t-304040\t144991\t737879\t-688937\t-123314\t-721067\t-856154\t166921\t405265\t818995\t-297449\t-959488\t653999\t-188894\t160610\t90461.8\t474290\t-627913\t527016\t200358\t504379\t631036\t-845241\t-950756\t-768589\t-473008\t317377\t-514879\t677452\t-517601\t304497\t-238875\t-501201\t160641\t-422783\t-938243\t-8780\t-986836\t760793\t-234184\t-668726\t-222017\t298099\t-664659\t-886828\t-83891\t56091.5\t168044\t-778198\t580637\t272374\t-139180\t248268\t-361628\t-47198\t-688349\t912585\t744474\n" +
            "96:-510550\t300561\t-558739\t-381097\t-581750\t-406877\t-189551\t-254356\t949600\t-56602.8\t551823\t-59568.3\t-688735\t26780\t-992238\t-596551\t-817865\t-792444\t172319\t545400\t-684279\t-884474\t-118774\t-595477\t29339\t-557391\t-37353\t240884\t408429\t-671517\t400311\t-528347\t-349138\t466512\t220191\t919665\t758916\t241043\t-637993\t705208\t25817\t506979\t363266\t-36954\t390778\t-955376\t998795\t-425043\t-858692\t-179922\t604912\t200724\t491262\t-638737\t591655\t813199\t-501927\t-463608\t-143192\t-765569\t-395045\t771736\t912480\t-509991\t20454.2\t-419813\t-704557\t155696\t-215059\t-447585\t-95158\t-913443\t762619\t355944\t-143896\t752745\t142305\t-360684\t736862\t-690375\t353709\t-914762\t-72390\t-488244\t483648\t-327922\t-580079\t298960\t-606990\t-695298\t933565\t-229767\t-171873\t-695821\t-68352\t-982564\t488992\t-505308\t385579\t490999\n";

    File input2 = new File("src/main/resources/sample_input_2_8.tsv");
    String output2 = "3:742431\t-772652\n" +
            "6:726622\t-813088\n";

    File input3 = new File("src/main/resources/sample_input_3_1000.tsv");
    String output3 = "223:-532230\t-121034\t-172064\n" +
            "388:-540627\t-121936\t-185821\n";

    File input4 = new File("src/main/resources/sample_input_4_4.tsv");
    String output4 = "2:-543447\t-319599\t130119\t42410.4\n" +
            "3:-800188\t58364\t586736\t-409415\n";

    File input5 = new File("src/main/resources/sample_input_10_100.tsv");
    String output5 = "40:666272\t527672\t152967\t520180\t670892\t101834\t800325\t-292876\t-956374\t511728\n" +
            "94:969976\t293327\t838907\t617179\t491237\t-236921\t416816\t-462137\t-782541\t450974\n";


    @Test(expected = IllegalArgumentException.class)
    public void OnePointFoundedTest() {
        String result = cpp.mainLogic(inputOneArgument);
    }

    @Test(expected = IllegalArgumentException.class)
    public void NoPointFoundedTest() {
        String result = cpp.mainLogic(inputNoArgument);
    }

    @Test(expected = IllegalArgumentException.class)
    public void InputNotExistTest() {
        String result = cpp.mainLogic(inputNotExist);
    }

    // some places I found double values, like 9438.4 instead of 19438 in output file !!!
    @Test
    public void MatchesInput1Test() {
        String result = cpp.mainLogic(input1);
        assertEquals(result, output1);
    }

    @Test
    public void MatchesInput2Test() {
        String result = cpp.mainLogic(input2);
        assertEquals(result, output2);
    }

    @Test
    public void MatchesInput3Test() {
        String result = cpp.mainLogic(input3);
        assertEquals(result, output3);
    }

    // some places I found double values, like 42410.4 instead of 42410 in output file!!!
    @Test
    public void MatchesInput4Test() {
        String result = cpp.mainLogic(input4);
        assertEquals(result, output4);
    }

    @Test
    public void MatchesInput5Test() {
        String result = cpp.mainLogic(input5);
        assertEquals(result, output5);
    }


}

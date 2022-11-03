import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.ImageProcessorController;
import controller.ImageProcessorControllerImp;
import model.imageprocessor.PPMImageProcessor;
import model.imageprocessor.ImageProcessor;

import static org.junit.Assert.assertEquals;

/**
 * Is the ImageProcessorTests of the file.
 */
public class ImageProcessorTests {

  @Test
  public void testDarkenBy50() {
    StringReader reader = new StringReader("load 4by4.ppm four\n" +
            "brighten -50 four four-dark\n" +
            "save four-dark.ppm four");
    StringBuilder builder = new StringBuilder();
    ImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);

    controller.run();
    String expectedOutput = "P3\n" +
            "4 4\n" +
            "255\n" +
            "0\n" +
            "172\n" +
            "73\n" +
            "\n" +
            "100\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "75\n" +
            "75\n" +
            "75\n" +
            "\n" +
            "205\n" +
            "205\n" +
            "205\n" +
            "\n" +
            "10\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "150\n" +
            "\n" +
            "0\n" +
            "150\n" +
            "150\n" +
            "\n" +
            "0\n" +
            "181\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "181\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "181\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n";

    assertEquals(model.getImageString("four-dark").toString(), expectedOutput);
  }

  @Test
  public void testBrightenBy50() {
    StringReader reader = new StringReader("load 4by4.ppm four\n" +
            "brighten 50 four four-bright\n" +
            "save four-bright.ppm four");
    StringBuilder builder = new StringBuilder();
    ImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);

    controller.run();
    System.out.println(builder);
    String expectedOutput = "P3\n" +
            "4 4\n" +
            "255\n" +
            "90\n" +
            "255\n" +
            "173\n" +
            "\n" +
            "200\n" +
            "95\n" +
            "84\n" +
            "\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "\n" +
            "175\n" +
            "175\n" +
            "175\n" +
            "\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "\n" +
            "110\n" +
            "84\n" +
            "84\n" +
            "\n" +
            "74\n" +
            "84\n" +
            "250\n" +
            "\n" +
            "60\n" +
            "250\n" +
            "250\n" +
            "\n" +
            "90\n" +
            "255\n" +
            "84\n" +
            "\n" +
            "90\n" +
            "255\n" +
            "84\n" +
            "\n" +
            "90\n" +
            "255\n" +
            "84\n" +
            "\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "\n";

    assertEquals(model.getImageString("four-bright").toString(), expectedOutput);
  }

  @Test
  public void testFlipVertically() {
    StringReader reader = new StringReader("load 10by10.ppm k\n" +
            "vertical-flip k kb\n" +
            "save vf110.ppm kb");
    StringBuilder builder = new StringBuilder();
    PPMImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);
    controller.run();
    String expectd = "P3\n" +
            "5 20\n" +
            "255\n" +
            "197\n" +
            "108\n" +
            "9\n" +
            "\n" +
            "167\n" +
            "46\n" +
            "211\n" +
            "\n" +
            "121\n" +
            "61\n" +
            "44\n" +
            "\n" +
            "41\n" +
            "254\n" +
            "157\n" +
            "\n" +
            "10\n" +
            "112\n" +
            "204\n" +
            "\n" +
            "136\n" +
            "171\n" +
            "143\n" +
            "\n" +
            "6\n" +
            "238\n" +
            "56\n" +
            "\n" +
            "230\n" +
            "73\n" +
            "226\n" +
            "\n" +
            "220\n" +
            "19\n" +
            "31\n" +
            "\n" +
            "40\n" +
            "113\n" +
            "52\n" +
            "\n" +
            "22\n" +
            "164\n" +
            "96\n" +
            "\n" +
            "119\n" +
            "55\n" +
            "11\n" +
            "\n" +
            "107\n" +
            "62\n" +
            "2\n" +
            "\n" +
            "16\n" +
            "95\n" +
            "59\n" +
            "\n" +
            "217\n" +
            "60\n" +
            "137\n" +
            "\n" +
            "221\n" +
            "104\n" +
            "133\n" +
            "\n" +
            "218\n" +
            "198\n" +
            "13\n" +
            "\n" +
            "3\n" +
            "47\n" +
            "120\n" +
            "\n" +
            "222\n" +
            "158\n" +
            "135\n" +
            "\n" +
            "57\n" +
            "102\n" +
            "91\n" +
            "\n" +
            "201\n" +
            "12\n" +
            "81\n" +
            "\n" +
            "146\n" +
            "170\n" +
            "251\n" +
            "\n" +
            "66\n" +
            "111\n" +
            "106\n" +
            "\n" +
            "191\n" +
            "131\n" +
            "165\n" +
            "\n" +
            "38\n" +
            "34\n" +
            "72\n" +
            "\n" +
            "243\n" +
            "88\n" +
            "76\n" +
            "\n" +
            "186\n" +
            "160\n" +
            "30\n" +
            "\n" +
            "86\n" +
            "179\n" +
            "248\n" +
            "\n" +
            "187\n" +
            "109\n" +
            "232\n" +
            "\n" +
            "115\n" +
            "127\n" +
            "199\n" +
            "\n" +
            "237\n" +
            "239\n" +
            "173\n" +
            "\n" +
            "242\n" +
            "43\n" +
            "99\n" +
            "\n" +
            "177\n" +
            "51\n" +
            "151\n" +
            "\n" +
            "159\n" +
            "103\n" +
            "139\n" +
            "\n" +
            "124\n" +
            "150\n" +
            "175\n" +
            "\n" +
            "4\n" +
            "90\n" +
            "196\n" +
            "\n" +
            "210\n" +
            "100\n" +
            "178\n" +
            "\n" +
            "67\n" +
            "8\n" +
            "188\n" +
            "\n" +
            "208\n" +
            "17\n" +
            "255\n" +
            "\n" +
            "54\n" +
            "64\n" +
            "129\n" +
            "\n" +
            "23\n" +
            "174\n" +
            "142\n" +
            "\n" +
            "123\n" +
            "93\n" +
            "253\n" +
            "\n" +
            "21\n" +
            "192\n" +
            "97\n" +
            "\n" +
            "183\n" +
            "25\n" +
            "206\n" +
            "\n" +
            "231\n" +
            "180\n" +
            "37\n" +
            "\n" +
            "250\n" +
            "148\n" +
            "24\n" +
            "\n" +
            "98\n" +
            "87\n" +
            "224\n" +
            "\n" +
            "228\n" +
            "89\n" +
            "182\n" +
            "\n" +
            "153\n" +
            "244\n" +
            "214\n" +
            "\n" +
            "209\n" +
            "215\n" +
            "53\n" +
            "\n" +
            "141\n" +
            "125\n" +
            "223\n" +
            "\n" +
            "84\n" +
            "233\n" +
            "114\n" +
            "\n" +
            "94\n" +
            "49\n" +
            "26\n" +
            "\n" +
            "42\n" +
            "116\n" +
            "80\n" +
            "\n" +
            "154\n" +
            "147\n" +
            "122\n" +
            "\n" +
            "39\n" +
            "74\n" +
            "105\n" +
            "\n" +
            "193\n" +
            "172\n" +
            "58\n" +
            "\n" +
            "117\n" +
            "128\n" +
            "140\n" +
            "\n" +
            "252\n" +
            "181\n" +
            "27\n" +
            "\n" +
            "155\n" +
            "92\n" +
            "28\n" +
            "\n" +
            "236\n" +
            "205\n" +
            "29\n" +
            "\n" +
            "77\n" +
            "169\n" +
            "203\n" +
            "\n" +
            "110\n" +
            "7\n" +
            "189\n" +
            "\n" +
            "190\n" +
            "20\n" +
            "35\n" +
            "\n" +
            "229\n" +
            "202\n" +
            "134\n" +
            "\n" +
            "130\n" +
            "184\n" +
            "149\n" +
            "\n" +
            "45\n" +
            "48\n" +
            "207\n" +
            "\n" +
            "246\n" +
            "163\n" +
            "69\n" +
            "\n" +
            "63\n" +
            "213\n" +
            "14\n" +
            "\n" +
            "225\n" +
            "132\n" +
            "240\n" +
            "\n" +
            "185\n" +
            "50\n" +
            "195\n" +
            "\n" +
            "235\n" +
            "245\n" +
            "176\n" +
            "\n" +
            "161\n" +
            "101\n" +
            "118\n" +
            "\n" +
            "68\n" +
            "138\n" +
            "82\n" +
            "\n" +
            "241\n" +
            "65\n" +
            "15\n" +
            "\n" +
            "85\n" +
            "162\n" +
            "194\n" +
            "\n" +
            "200\n" +
            "144\n" +
            "79\n" +
            "\n" +
            "78\n" +
            "152\n" +
            "36\n" +
            "\n" +
            "249\n" +
            "216\n" +
            "227\n" +
            "\n" +
            "33\n" +
            "247\n" +
            "145\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "71\n" +
            "75\n" +
            "168\n" +
            "\n" +
            "5\n" +
            "166\n" +
            "83\n" +
            "\n" +
            "70\n" +
            "234\n" +
            "32\n" +
            "\n" +
            "40\n" +
            "231\n" +
            "34\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "60\n" +
            "34\n" +
            "34\n" +
            "\n" +
            "24\n" +
            "34\n" +
            "200\n" +
            "\n" +
            "10\n" +
            "200\n" +
            "200\n" +
            "\n" +
            "40\n" +
            "231\n" +
            "34\n" +
            "\n" +
            "40\n" +
            "231\n" +
            "34\n" +
            "\n" +
            "40\n" +
            "222\n" +
            "123\n" +
            "\n" +
            "150\n" +
            "45\n" +
            "34\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "125\n" +
            "125\n" +
            "125\n" +
            "\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "\n";
    assertEquals(model.getImageString("kb").toString(), expectd);
  }

  @Test
  public void testFlipVertically2() {
    StringReader reader = new StringReader("load 4by4.ppm k\n" +
            "vertical-flip k kb\n");
    StringBuilder builder = new StringBuilder();
    PPMImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);
    controller.run();
    assertEquals(model.getImageString("kb").toString(), "P3\n" +
            "4 4\n" +
            "255\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "40\n" +
            "231\n" +
            "34\n" +
            "\n" +
            "40\n" +
            "231\n" +
            "34\n" +
            "\n" +
            "40\n" +
            "231\n" +
            "34\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "\n" +
            "60\n" +
            "34\n" +
            "34\n" +
            "\n" +
            "24\n" +
            "34\n" +
            "200\n" +
            "\n" +
            "10\n" +
            "200\n" +
            "200\n" +
            "\n" +
            "40\n" +
            "222\n" +
            "123\n" +
            "\n" +
            "150\n" +
            "45\n" +
            "34\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "125\n" +
            "125\n" +
            "125\n\n");

  }

  @Test
  public void testFlipHorizontally() {
    StringReader reader = new StringReader("load 4by4.ppm four\n" +
            "horizontal-flip four test\n");
    StringBuilder builder = new StringBuilder();
    PPMImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);
    controller.run();
    String expectedOutput = "P3\n" +
            "4 4\n" +
            "255\n" +
            "125\n" +
            "125\n" +
            "125\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "150\n" +
            "45\n" +
            "34\n" +
            "\n" +
            "40\n" +
            "222\n" +
            "123\n" +
            "\n" +
            "10\n" +
            "200\n" +
            "200\n" +
            "\n" +
            "24\n" +
            "34\n" +
            "200\n" +
            "\n" +
            "60\n" +
            "34\n" +
            "34\n" +
            "\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "40\n" +
            "231\n" +
            "34\n" +
            "\n" +
            "40\n" +
            "231\n" +
            "34\n" +
            "\n" +
            "40\n" +
            "231\n" +
            "34\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n";
    assertEquals(expectedOutput, model.getImageString("test").toString());
  }

  @Test
  public void testRedComponent() {
    StringReader reader = new StringReader("load 4by4.ppm four\n" +
            "red-component four test\n");
    StringBuilder builder = new StringBuilder();
    PPMImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);
    controller.run();
    String expectedOutput = "P3\n" +
            "4 4\n" +
            "255\n" +
            "40\n" +
            "40\n" +
            "40\n" +
            "\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "125\n" +
            "125\n" +
            "125\n" +
            "\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "\n" +
            "60\n" +
            "60\n" +
            "60\n" +
            "\n" +
            "24\n" +
            "24\n" +
            "24\n" +
            "\n" +
            "10\n" +
            "10\n" +
            "10\n" +
            "\n" +
            "40\n" +
            "40\n" +
            "40\n" +
            "\n" +
            "40\n" +
            "40\n" +
            "40\n" +
            "\n" +
            "40\n" +
            "40\n" +
            "40\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n";
    assertEquals(expectedOutput, model.getImageString("test").toString());
  }

  @Test
  public void testBlueComponent() {
    StringReader reader = new StringReader("load 4by4.ppm four\n" +
            "blue-component four test\n");
    StringBuilder builder = new StringBuilder();
    PPMImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);
    controller.run();
    String expectedOutput = "P3\n" +
            "4 4\n" +
            "255\n" +
            "123\n" +
            "123\n" +
            "123\n" +
            "\n" +
            "34\n" +
            "34\n" +
            "34\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "125\n" +
            "125\n" +
            "125\n" +
            "\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "\n" +
            "34\n" +
            "34\n" +
            "34\n" +
            "\n" +
            "200\n" +
            "200\n" +
            "200\n" +
            "\n" +
            "200\n" +
            "200\n" +
            "200\n" +
            "\n" +
            "34\n" +
            "34\n" +
            "34\n" +
            "\n" +
            "34\n" +
            "34\n" +
            "34\n" +
            "\n" +
            "34\n" +
            "34\n" +
            "34\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n";
    assertEquals(expectedOutput, model.getImageString("test").toString());
  }

  @Test
  public void testGreenComponent() {
    StringReader reader = new StringReader("load 4by4.ppm four\n" +
            "green-component four test\n");
    StringBuilder builder = new StringBuilder();
    PPMImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);
    controller.run();
    String expectedOutput = "P3\n" +
            "4 4\n" +
            "255\n" +
            "222\n" +
            "222\n" +
            "222\n" +
            "\n" +
            "45\n" +
            "45\n" +
            "45\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "125\n" +
            "125\n" +
            "125\n" +
            "\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "\n" +
            "34\n" +
            "34\n" +
            "34\n" +
            "\n" +
            "34\n" +
            "34\n" +
            "34\n" +
            "\n" +
            "200\n" +
            "200\n" +
            "200\n" +
            "\n" +
            "231\n" +
            "231\n" +
            "231\n" +
            "\n" +
            "231\n" +
            "231\n" +
            "231\n" +
            "\n" +
            "231\n" +
            "231\n" +
            "231\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n";
    assertEquals(expectedOutput, model.getImageString("test").toString());
  }

  @Test
  public void testIntensityComponent() {
    StringReader reader = new StringReader("load 4by4.ppm four\n" +
            "intensity-component four test\n");
    StringBuilder builder = new StringBuilder();
    PPMImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);
    controller.run();
    String expectedOutput = "P3\n" +
            "4 4\n" +
            "255\n" +
            "128\n" +
            "128\n" +
            "128\n" +
            "\n" +
            "76\n" +
            "76\n" +
            "76\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "125\n" +
            "125\n" +
            "125\n" +
            "\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "\n" +
            "42\n" +
            "42\n" +
            "42\n" +
            "\n" +
            "86\n" +
            "86\n" +
            "86\n" +
            "\n" +
            "136\n" +
            "136\n" +
            "136\n" +
            "\n" +
            "101\n" +
            "101\n" +
            "101\n" +
            "\n" +
            "101\n" +
            "101\n" +
            "101\n" +
            "\n" +
            "101\n" +
            "101\n" +
            "101\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n";
    assertEquals(expectedOutput, model.getImageString("test").toString());
  }

  @Test
  public void testLumaComponent() {
    StringReader reader = new StringReader("load 4by4.ppm four\n" +
            "luma-component four test\n");
    StringBuilder builder = new StringBuilder();
    PPMImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);
    controller.run();
    String expectedOutput = "P3\n" +
            "4 4\n" +
            "255\n" +
            "176\n" +
            "176\n" +
            "176\n" +
            "\n" +
            "66\n" +
            "66\n" +
            "66\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "125\n" +
            "125\n" +
            "125\n" +
            "\n" +
            "254\n" +
            "254\n" +
            "254\n" +
            "\n" +
            "39\n" +
            "39\n" +
            "39\n" +
            "\n" +
            "43\n" +
            "43\n" +
            "43\n" +
            "\n" +
            "159\n" +
            "159\n" +
            "159\n" +
            "\n" +
            "176\n" +
            "176\n" +
            "176\n" +
            "\n" +
            "176\n" +
            "176\n" +
            "176\n" +
            "\n" +
            "176\n" +
            "176\n" +
            "176\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n";
    assertEquals(expectedOutput, model.getImageString("test").toString());
  }

  @Test
  public void testValueComponent() {
    StringReader reader = new StringReader("load 4by4.ppm four\n" +
            "value-component four test\n");
    StringBuilder builder = new StringBuilder();
    PPMImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);
    controller.run();
    String expectedOutput = "P3\n" +
            "4 4\n" +
            "255\n" +
            "222\n" +
            "222\n" +
            "222\n" +
            "\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "125\n" +
            "125\n" +
            "125\n" +
            "\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "\n" +
            "60\n" +
            "60\n" +
            "60\n" +
            "\n" +
            "200\n" +
            "200\n" +
            "200\n" +
            "\n" +
            "200\n" +
            "200\n" +
            "200\n" +
            "\n" +
            "231\n" +
            "231\n" +
            "231\n" +
            "\n" +
            "231\n" +
            "231\n" +
            "231\n" +
            "\n" +
            "231\n" +
            "231\n" +
            "231\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n";
    assertEquals(expectedOutput, model.getImageString("test").toString());
  }

  @Test
  public void testLoad() {
    StringReader reader = new StringReader("load 4by4.ppm four\n");
    StringBuilder builder = new StringBuilder();
    PPMImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);
    controller.run();

    String expectedOutput = "P3\n" +
            "4 4\n" +
            "255\n" +
            "40\n" +
            "222\n" +
            "123\n" +
            "\n" +
            "150\n" +
            "45\n" +
            "34\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "125\n" +
            "125\n" +
            "125\n" +
            "\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "\n" +
            "60\n" +
            "34\n" +
            "34\n" +
            "\n" +
            "24\n" +
            "34\n" +
            "200\n" +
            "\n" +
            "10\n" +
            "200\n" +
            "200\n" +
            "\n" +
            "40\n" +
            "231\n" +
            "34\n" +
            "\n" +
            "40\n" +
            "231\n" +
            "34\n" +
            "\n" +
            "40\n" +
            "231\n" +
            "34\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n";

    assertEquals(expectedOutput, model.getImageString("four").toString());
  }

  @Test
  public void testSave() {
    // since we have tested load, we can test save by loading the file
    // we just saved to see if it is what it should be
    StringReader reader = new StringReader("load 4by4.ppm four\n"
            + "save four-save.ppm four\n"
            + "load four-save.ppm four-save");
    StringBuilder builder = new StringBuilder();
    PPMImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);
    controller.run();
    assertEquals(model.getImageString("four").toString(),
            model.getImageString("four-save").toString());
  }

}

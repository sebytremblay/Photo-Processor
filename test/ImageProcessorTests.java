import org.junit.Test;

import java.io.StringReader;

import controller.ImageProcessorController;
import controller.ImageProcessorControllerImp;
import model.imageprocessor.PPMImageProcessor;
import model.imageprocessor.ImageProcessor;

import static org.junit.Assert.assertEquals;

public class ImageProcessorTests {
  @Test
  public void testBrightenBy50() {
    StringReader reader = new StringReader("load Koala.ppm koala\n" +
            "brighten 50 koala kb\n" +
            "save koala-bright-50.ppm kb");
    StringBuilder builder = new StringBuilder();
    ImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);

    controller.run();
    System.out.println(builder);
  }

  @Test
  public void testFlipVertically() {
    StringReader reader = new StringReader("load Koala.ppm koala\n" +
            "vertical-flip koala kb\n" +
            "save koala-vertical2.ppm kb");
    StringBuilder builder = new StringBuilder();
    ImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);

    controller.run();
  }
  @Test
  public void testFlipVertically2() {
    StringReader reader = new StringReader("load 4by4.ppm k\n"+
          "vertical-flip k kb\n");
    StringBuilder builder = new StringBuilder();
    PPMImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);
    controller.run();
    assertEquals(model.getPPMImage("kb"),"P3\n" +
            "4 4\n" +
            "255\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "\n"+
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
  public void testFlipVertically3() {
    StringReader reader = new StringReader("load 10by10.ppm k\n" +
            "vertical-flip k kb\n"+
            "save vf110.ppm kb");
    StringBuilder builder = new StringBuilder();
    PPMImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);
    controller.run();
    //assertEquals(model.getPPMImage("kb").toString(),"P3\n" +"");
  }

}

import org.junit.Test;

import java.io.StringReader;

import controller.ImageProcessorController;
import controller.ImageProcessorControllerImp;
import model.imageprocessor.PPMImageProcessor;
import model.imageprocessor.ImageProcessor;

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
            "save koala-vertical.ppm kb");
    StringBuilder builder = new StringBuilder();
    ImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);

    controller.run();
    System.out.println(builder);

  }


}

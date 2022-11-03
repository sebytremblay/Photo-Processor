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
            "brighten 50 koala-bright-50 koala" +
            "save koala-bright-50.ppm koala-bright-50");
    StringBuilder builder = new StringBuilder();
    ImageProcessor model = new PPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);

    controller.run();
  }
}

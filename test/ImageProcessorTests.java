import org.junit.Test;

import java.io.StringReader;

public class ImageProcessorTests {
  @Test
  public void testBrightenBy50() {
    StringReader reader = new StringReader("load Koala.ppm koala\n" +
            "red-component koala-red koala");
    StringBuilder builder = new StringBuilder();
    ImageProcessor model = new ASCIIPPMImageProcessor();
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);

    controller.run();
  }
}

import org.junit.Test;

import java.io.StringReader;

import controller.ImageProcessorController;
import controller.ImageProcessorControllerImp;
import model.imageprocessor.ImageProcessor;

import static org.junit.Assert.assertEquals;

/**
 * is the controller mock tests.
 */
public class ControllerTests {

  private void assertControllerMock(String input, String output) {
    StringBuilder build = new StringBuilder();
    ImageProcessor model = new MockImageProcessor(build);
    StringReader reader = new StringReader(input);
    StringBuilder builder = new StringBuilder("");
    ImageProcessorController controller = new ImageProcessorControllerImp(reader, builder, model);
    controller.run();
    assertEquals(output, build.toString());
  }

  @Test
  public void testMockModelVertical() {
    assertControllerMock("load 4by4.ppm l\nvertical-flip l lk", "imgName: l maxValue:255\n" +
            "imgName: l, newImageName:lk, Direction: Vertical");
  }

  @Test
  public void testMockModelHorz() {
    assertControllerMock("load 4by4.ppm l\nhorizontal-flip l lk", "imgName: l maxValue:255\n" +
            "imgName: l, newImageName:lk, Direction: Horizontal");
  }

  @Test
  public void testMockLoad() {
    assertControllerMock("load 4by4.ppm l", "imgName: l maxValue:255");
  }

  @Test
  public void testBrighten() {
    assertControllerMock("imgName: l maxValue:255imgName: l, newImageName:lk, brightenBy: 50", "load 4by4.ppm l\n brighten 50 l lk");
  }

  @Test
  public void testVisualizeIntensity() {
    assertControllerMock("load 4by4.ppm l\nintensity-component l lk", "imgName: l maxValue:255\n" +
            "imgName: l, newImageName:lk");
  }

  @Test
  public void testVisualizeBlue() {
    assertControllerMock("load 4by4.ppm l\nintensity-component l lk", "imgName: l maxValue:255\n" +
            "imgName: l, newImageName:lk");
  }

  @Test
  public void testSave() {
    assertControllerMock("load 4by4.ppm l\nsave l lk", "imgName: l maxValue:255\n" +
            "filePath: l, imgName:lk");
  }
}

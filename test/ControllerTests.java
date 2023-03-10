import org.junit.Test;

import java.io.StringReader;

import controller.ControllerFeaturesImpl;
import controller.Features;
import controller.ImageProcessorController;
import controller.ImageProcessorControllerImp;
import model.imageprocessor.ImageProcessor;
import model.imageprocessor.ImageProcessorModel;
import view.ImageProcessorGUI;

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

  @Test(expected = IllegalArgumentException.class)
  public void testNull() {
    ImageProcessor model = new ImageProcessorModel();
    ImageProcessorController controller = new ImageProcessorControllerImp(null,
            new StringBuilder(), model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNull3() {
    ImageProcessorController controller = new ImageProcessorControllerImp(new StringReader(""),
            new StringBuilder(), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNull2() {
    ImageProcessor model = new ImageProcessorModel();
    ImageProcessorController controller = new ImageProcessorControllerImp(new StringReader(""),
            null, model);
  }

  @Test
  public void testMockModelVertical() {
    assertControllerMock("load 4by4.ppm l\nvertical-flip l lk",
            "loaded image: l\n" +
                    "flipImage imgName: l, newImageName:lk, \n");
  }

  @Test
  public void testMockModelHorz() {
    assertControllerMock("load 4by4.ppm l\nhorizontal-flip l lk",
            "loaded image: l\n" +
                    "flipImage imgName: l, newImageName:lk, \n");
  }

  @Test
  public void testMockLoad() {
    assertControllerMock("load 4by4.ppm l",
            "loaded image: l\n");
  }

  @Test
  public void testBrighten() {
    assertControllerMock("load 4by4.ppm l\n brighten 50 l lk", "loaded image: l\n" +
            "visualize imgName: l, newImageName:lk\n");
  }

  @Test
  public void testVisualizeIntensity() {
    assertControllerMock("load 4by4.ppm l\nintensity-component l lk", "loaded image: l\n" +
            "visualize imgName: l, newImageName:lk\n");
  }

  @Test
  public void testVisualizeBlue() {
    assertControllerMock("load 4by4.ppm l\nintensity-component l lk", "loaded image: l\n" +
            "visualize imgName: l, newImageName:lk\n");
  }

  @Test
  public void testSavPPM() {
    assertControllerMock("load 4by4.ppm l\nsave l.ppm lk",
            "loaded image: l\n" +
                    "Saved imgName lk\n");
  }

  @Test
  public void testSavIOImg() {
    assertControllerMock("load res/20by5.png img\n"
                    + "save res/20by5.png img",
            "loaded image: img\n" +
                    "Saved imgName: img\n");
  }

  @Test
  public void testBlur() {
    String commandString = "load 4by4.ppm img\n"
            + "blur img img-blur";
    String expectedOut = "loaded image: img\n" +
            "Applied blur kernel to: img\n";

    assertControllerMock(commandString, expectedOut);
  }

  @Test
  public void testSharpen() {
    String commandString = "load 4by4.ppm img\n"
            + "sharpen img img-sharp";
    String expectedOut = "loaded image: img\n" +
            "Applied sharpen kernel to: img\n";

    assertControllerMock(commandString, expectedOut);
  }

  @Test
  public void testGreyscale() {
    String commandString = "load 4by4.ppm img\n"
            + "greyscale img img-grey";
    String expectedOut = "loaded image: img\n" +
            "Applied greyscale color transformation to: img\n";

    assertControllerMock(commandString, expectedOut);
  }

  @Test
  public void testSepia() {
    String commandString = "load 4by4.ppm img\n"
            + "sepia img img-sepia";
    String expectedOut = "loaded image: img\n" +
            "Applied sepia color transformation to: img\n";

    assertControllerMock(commandString, expectedOut);
  }

  @Test
  public void testGUIButtonClickNormal() {
    StringBuilder build = new StringBuilder();
    ImageProcessor model = new MockImageProcessor(build);
    ImageProcessorGUI gui = new MockGUI();
    Features controller = new ControllerFeaturesImpl(gui, model);
    controller.readButtonClick("sepia", "img");

    String output = "Applied sepia color transformation to: img\n" +
            "Saved imgName: img\n" +
            "Generated histogram.\n";
    assertEquals(output, build.toString());
  }

  @Test
  public void testGUIButtonClickWithFilePath() {
    StringBuilder build = new StringBuilder();
    ImageProcessor model = new MockImageProcessor(build);
    ImageProcessorGUI gui = new MockGUI();
    Features controller = new ControllerFeaturesImpl(gui, model);
    controller.readButtonClick("load", "res/20by5.ppm", "img");

    String output = "loaded image: img\n" +
            "Saved imgName: img\n" +
            "Generated histogram.\n";
    assertEquals(output, build.toString());
  }

  @Test
  public void testGUIButtonClickWithValue() {
    StringBuilder build = new StringBuilder();
    ImageProcessor model = new MockImageProcessor(build);
    ImageProcessorGUI gui = new MockGUI();
    Features controller = new ControllerFeaturesImpl(gui, model);
    controller.takesInTextField("brighten", "50", "img");

    String output = "visualize imgName: img, newImageName:img\n" +
            "Saved imgName: img\n" +
            "Generated histogram.\n";
    assertEquals(output, build.toString());
  }
}

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

                    "flipImage imgName: l, newImageName:lk, \n");
  }

  @Test
  public void testMockModelHorz() {
    assertControllerMock("load 4by4.ppm l\nhorizontal-flip l lk",
                    "flipImage imgName: l, newImageName:lk, \n");
  }


  @Test
  public void testBrighten() {
    assertControllerMock("load 4by4.ppm l\n brighten 50 l lk",
            "visualize imgName: l, mockImgName: null, newImageName:lk\n");
  }

  @Test
  public void testVisualizeIntensity() {
    assertControllerMock("load 4by4.ppm l\nintensity-component l lk",
            "visualize imgName: l, mockImgName: null, newImageName:lk\n");
  }

  @Test
  public void testVisualizeBlue() {
    assertControllerMock("load 4by4.ppm l\nintensity-component l lk",
            "visualize imgName: l, mockImgName: null, newImageName:lk\n");
  }

  @Test
  public void testSavPPM() {
    assertControllerMock("load 4by4.ppm l\nsave l.ppm lk",
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
    String expectedOut =
            "Applied blur mockImgName: null kernel to: img\n";

    assertControllerMock(commandString, expectedOut);
  }

  @Test
  public void testSharpen() {
    String commandString = "load 4by4.ppm img\n"
            + "sharpen img img-sharp";
    String expectedOut =
            "Applied sharpen mockImgName: null kernel to: img\n";

    assertControllerMock(commandString, expectedOut);
  }

  @Test
  public void testGreyscale() {
    String commandString = "load 4by4.ppm img\n"
            + "greyscale img img-grey";
    String expectedOut =
            "Applied greyscale mockImgName: null color transformation to: img\n";

    assertControllerMock(commandString, expectedOut);
  }

  @Test
  public void testSepia() {
    String commandString = "load 4by4.ppm img\n"
            + "sepia img img-sepia";
    String expectedOut =
            "Applied sepia mockImgName: null color transformation to: img\n";

    assertControllerMock(commandString, expectedOut);
  }
  @Test
  public void testResize() {
    String commandString = "load 4by4.ppm img\n"
            + "resize 2 2 img img-resize";
    String expectedOut =
            "Applied Resize of size 2x2 to image: img\n";


    assertControllerMock(commandString, expectedOut);
  }

  @Test
  public void testGUIButtonClickNormal() {
    StringBuilder build = new StringBuilder();
    ImageProcessor model = new MockImageProcessor(build);
    ImageProcessorGUI gui = new MockGUI();
    Features controller = new ControllerFeaturesImpl(gui, model);
    controller.readButtonClick("sepia", "img");

    String output = "Applied sepia mockImgName: null color transformation to: img\n" +
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

    String output = "visualize imgName: img, mockImgName: null, newImageName:img\n" +
            "Saved imgName: img\n" +
            "Generated histogram.\n";
    assertEquals(output, build.toString());
  }
  @Test
  public void testBrightenMask() {
    assertControllerMock("load 4by4.ppm l\n load 4by4.ppm g\nbrighten 50 l g lk",
            "visualize imgName: l, mockImgName: g, newImageName:lk\n");
  }

  @Test
  public void testVisualizeIntensityMask() {
    assertControllerMock("load 4by4.ppm l\nload 4by4.ppm k\nintensity-component l k lk",
            "visualize imgName: l, mockImgName: k, newImageName:lk\n");
  }

  @Test
  public void testVisualizeBlueMask() {
    assertControllerMock("load 4by4.ppm l\nload 4by4.ppm k\nintensity-component l k lk",
            "visualize imgName: l, mockImgName: k, newImageName:lk\n");
  }
  @Test
  public void testBlurMask() {
    String commandString = "load 4by4.ppm img\nload 4by4.ppm l\n"
            + "blur img l img-blur";
    String expectedOut =
            "Applied blur mockImgName: l kernel to: img\n";

    assertControllerMock(commandString, expectedOut);
  }

  @Test
  public void testSharpenMask() {
    String commandString = "load 4by4.ppm img\nload 4by4.ppm l\n"
            + "sharpen img l img-sharp";
    String expectedOut =
            "Applied sharpen mockImgName: l kernel to: img\n";


    assertControllerMock(commandString, expectedOut);
  }

  @Test
  public void testGreyscaleMask() {
    String commandString = "load 4by4.ppm img\nload 4by4.ppm i\n"
            + "greyscale img i img-grey";
    String expectedOut =
            "Applied greyscale mockImgName: i color transformation to: img\n";

    assertControllerMock(commandString, expectedOut);
  }

  @Test
  public void testSepiaMask() {
    String commandString = "load 4by4.ppm img\nload 4by4.ppm m\n"
            + "sepia img m img-sepia";
    String expectedOut =
            "Applied sepia mockImgName: m color transformation to: img\n";

    assertControllerMock(commandString, expectedOut);
  }
}

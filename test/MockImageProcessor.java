import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.imageprocessor.ImageProcessor;

import model.pixel.Pixel;

public class MockImageProcessor implements ImageProcessor {
  MockImageProcessor(StringBuilder log) {
    this.log = log;
  }

  StringBuilder log = new StringBuilder("");


  /**
   * Loads an image from an ASCII PPM file. If imgName is already taken, the new image
   * will overwrite the old image
   *
   * @param imgName  the name of the generated image
   * @param grid
   * @param maxValue
   */
  @Override
  public void loadASCIIPPM(Scanner image, String imgName) {
    log.append("loaded image: " + imgName + "\n");
  }

  /**
   * visualizes a component based on the given function.
   *
   * @param imgName      name of the loaded image we are trying to manipulate
   * @param newImageName the new modified name in the processor
   * @param f            function that is applied the image
   */
  @Override
  public void visualize(String imgName, String newImageName, Function f) {
    log.append("imgName: " + imgName + ", newImageName:" + newImageName);
  }

  /**
   * Flips an image in the given direction.
   *
   * @param imgName      the name of the image to flip
   * @param newImageName the name of the newly flipped image
   * @param dir          the direction to flip
   */
  @Override
  public void flipImage(String imgName, String newImageName, Direction dir) {
    if (dir == Direction.Vertical) {
      log.append("imgName: " + imgName + ", newImageName:" + newImageName + ", " +
              "Direction: Vertical");
    } else {

      log.append("imgName: " + imgName + ", newImageName:" + newImageName + ", " +
              "Direction: Horizontal");
    }
  }

  /**
   * Brightens image by the provided constant and saves into new image.
   *
   * @param imgName    the name of the image to brighten
   * @param newImgName the name of the newly generated image
   * @param brightenBy the factor to brighten the image by
   */
  @Override
  public void brighten(String imgName, String newImgName, int brightenBy) {
    log.append("imgName: " + imgName + ", newImageName:" + newImgName + ", " +
            "brightenBy: " + brightenBy);
  }

  /**
   * Saves an image to a given location and a given name. If filePath is occupied it will overwrite
   *
   * @param filePath location to save of an image
   * @param imgName  name of the saved image
   */
  @Override
  public void saveImage(String filePath, String imgName) {
    log.append("filePath: " + filePath + ", imgName:" + imgName);
  }

  /**
   * Gets a string representation of the provided image.
   *
   * @param imgName the desired image
   * @return the image as a string
   */
  @Override
  public StringBuilder getImageString(String imgName) {
    log.append("imgName " + imgName);
    return new StringBuilder("");
  }
}

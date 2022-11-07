import java.awt.image.BufferedImage;
import java.util.function.Function;

import model.imageprocessor.ImageProcessor;


/**
 * Is a Mock Image processor.
 */
public class MockImageProcessor implements ImageProcessor {
  StringBuilder log;

  MockImageProcessor(StringBuilder log) {
    this.log = log;
  }

  /**
   * Loads an image from an ASCII PPM file. If imgName is already taken, the new image.
   * will overwrite the old image.
   *
   * @param image   the image loaded as a file
   * @param imgName the name for the new image
   */
  @Override
  public void load(String image, String imgName) {
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
    log.append("imgName: " + imgName + ", newImageName:" + newImageName + "");
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
   * Gets a string representation of the provided image.
   *
   * @param imgName the desired image
   * @return the image as a string
   */
  @Override
  public String getImageAsString(String imgName) {
    log.append("imgName " + imgName);
    return "";
  }

  /**
   * Creates a buffered image representation of the provided image
   *
   * @param imgName the image you want to create the buffered image of
   * @return the new buffered image
   */
  @Override
  public BufferedImage getImageAsBufferedImage(String imgName) {
    log.append("imgName: "+imgName);
    return null;
  }

  @Override
  public void applyKernel(String imgName, String newImgName, double[][] kernel) {

  }

  @Override
  public void applyColorTransformation(String imgName, String newImgName, double[][] transformation) {

  }
}

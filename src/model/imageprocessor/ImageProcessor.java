package model.imageprocessor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * Represents the ImageProcessor that holds the information and modifies the Images.
 */
public interface ImageProcessor {
  /**
   * Loads an image from a string representation of a file. If imgName is already taken,
   * the new image will overwrite the old image.
   *
   * @param imgAsString        the image as a string
   * @param imgName            the name of the generated image
   */
  void load(String imgAsString, String imgName);


  /**
   * Visualizes a component based on the given function.
   *
   * @param imgName      name of the loaded image we are trying to manipulate
   * @param newImageName the new modified name in the processor
   * @param f            function that is applied the image
   */
  void visualize(String imgName, String newImageName, Function f);


  /**
   * An enumeration of directions.
   */
  enum Direction {Horizontal, Vertical}

  /**
   * Flips an image in the given direction.
   *
   * @param imgName      the name of the image to flip
   * @param newImageName the name of the newly flipped image
   * @param dir          the direction to flip
   */
  void flipImage(String imgName, String newImageName, Direction dir);

  /**
   * Brightens image by the provided constant and saves into new image.
   *
   * @param imgName    the name of the image to brighten
   * @param newImgName the name of the newly generated image
   * @param brightenBy the factor to brighten the image by
   */
  void brighten(String imgName, String newImgName, int brightenBy);


  /**
   * Gets a string representation of the provided image.
   *
   * @param imgName the desired image
   * @return the image as a string
   */
  String getImageAsString(String imgName);


  /**
   * Creates a buffered image representation of the provided image
   * @param imgName the image you want to create the buffered image of
   * @return the new buffered image
   */
  BufferedImage getImageAsBufferedImage(String imgName);


  /**
   * Applies the kernel to the image
   * @param imgName the image that the kernel is being applied to
   * @param newImgName the new image that will be the applied image
   * @param kernel is the operation on the image
   */
  void applyKernel(String imgName, String newImgName, int[][] kernel);
}

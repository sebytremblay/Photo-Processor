package model.imageprocessor;

import java.util.function.Function;

import model.pixel.Pixel;

/**
 * Represents the ImageProcessor
 */
public interface ImageProcessor {
  /**
   * Loads an image from an ASCII PPM file. If imgName is already taken, the new image will overwrite
   * the old image
   *
   * @param imgPath the file path of the file to load
   * @param imgName  the name of the generated image
   */
  void loadASCIIPPM(String imgName, Pixel[][] grid, int maxValue);


  /**
   * visualizes a component based on the given function
   * @param imgName name of the loaded image we are trying to manipulate
   * @param newImageName the new modified name in the processor
   * @param f function that is applied the image
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
   * Saves an image to a given location and a given name. If filePath is occupied it will overwrite
   * @param filePath location to save of an image
   * @param imgName name of the saved image
   */
  void saveImage(String filePath,String imgName);

}

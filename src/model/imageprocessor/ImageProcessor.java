package model.imageprocessor;

import java.awt.image.BufferedImage;
import java.util.function.Function;

import model.pixel.Pixel;

/**
 * Represents the ImageProcessor that holds the information and modifies the Images.
 */
public interface ImageProcessor {
  /**
   * Loads an image from a string representation of a file. If imgName is already taken,
   * the new image will overwrite the old image.
   *
   * @param pixelGrid the pixels of the image
   * @param imgName   the name of the generated image
   */
  void load(Pixel[][] pixelGrid, String imgName);


  /**
   * Visualizes a component based on the given function.
   *
   * @param imgName      name of the loaded image we are trying to manipulate
   * @param newImageName the new modified name in the processor
   * @param f            function that is applied the image
   */
  void visualize(String imgName, String newImageName, Function<Pixel, Pixel> f);


  /**
   * An enumeration of directions.
   */
  enum Direction { Horizontal, Vertical }

  /**
   * Flips an image in the given direction.
   *
   * @param imgName      the name of the image to flip
   * @param newImageName the name of the newly flipped image
   * @param dir          the direction to flip
   */
  void flipImage(String imgName, String newImageName, Function<Pixel[][], Pixel[][]> dir);


  /**
   * Gets a string representation of the provided image.
   *
   * @param imgName the desired image
   * @return the image as a string
   */
  String getImageAsString(String imgName);


  /**
   * Creates a buffered image representation of the provided image.
   *
   * @param imgName the image you want to create the buffered image of
   * @return the new buffered image
   */
  BufferedImage getImageAsBufferedImage(String imgName);


  /**
   * Applies the kernel to the image.
   *
   * @param imgName    the image that the kernel is being applied to
   * @param newImgName the new image that will be the applied image
   * @param kernel     is the operation on the image
   */
  void applyKernel(String imgName, String newImgName, double[][] kernel);

  /**
   * Applies a color transformation to an image.
   *
   * @param imgName        the image to transform
   * @param newImgName     what to call the resulting image
   * @param transformation the color transformation to apply
   */
  void applyColorTransformation(String imgName, String newImgName, double[][] transformation);


  /**
   * Represents all the possible ways of displaying a histogram.
   */
  enum HistogramOptions { Red, Green, Blue, Intensity }

  /**
   * Generates a representation of a histogram of an image.
   *
   * @param imgName name of Image you are making a histogram for
   * @return
   */
  int[][] generateHistogram(String imgName);


}

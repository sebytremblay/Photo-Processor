package model.pixel;

import java.util.function.Function;

/**
 * Represents a pixel of color.
 */
public interface Pixel {

  /**
   * Visualizes the given color representation the pixel.
   *
   * @return the visualized pixel
   * @Param func takes a function that visualizes
   */
  Pixel visual(Function<Pixel, Integer> func, int maxValue);

  /**
   * Gets the list of all the components of this pixel.
   *
   * @return the list of pixel components
   */
  int[] getComponents();

  /**
   * Changes the image brightness by a given factor.
   * Will not brighten if new values are out of range.
   *
   * @param factor amount of brightness to add or reduce to pixel
   * @return the new pixel brightened.
   */
  Pixel brightenPixel(int factor, int maxValue);


  /**
   * converts a pixel to a hex representation
   * @return the hex representation of the pixel
   */
  int PixelToHex();

  /**
   * applies a kernel to this pixel
   * @param kernel the kernel being applied to the pixel
   * @param kernelBackground the surrounding pixels on the background
   * @param maxValue the maxValue
   * @return the new Pixel with the applied kernel.
   */
  Pixel kernelEval(int[][] kernel, Pixel[][] kernelBackground,int maxValue);
}

package model.pixel;

import java.util.function.Function;

/**
 * Represents a pixel of color.
 */
public interface Pixel {

  /**
   * Gets the list of all the components of this pixel.
   *
   * @return the list of pixel components
   */
  int[] getComponents();


  /**
   * Converts a pixel to a hex representation.
   *
   * @return the hex representation of the pixel
   */
  int PixelToHex();

  /**
   * Applies a kernel to this pixel.
   *
   * @param kernel           the kernel being applied to the pixel
   * @param kernelBackground the surrounding pixels on the background
   * @return the new Pixel with the applied kernel.
   */
  Pixel kernelEval(double[][] kernel, Pixel[][] kernelBackground);

  /**
   * Applies a given color transformation to the pixel.
   *
   * @param transformation the color transformation
   * @return
   */
  Pixel colorTransformation(double[][] transformation);

}

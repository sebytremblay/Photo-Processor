package model.pixel;

/**
 * Represents a pixel of color.
 */
public interface Pixel {

  /**
   * Gets the red component of this pixel.
   *
   * @return the list of pixel components
   */
  int getRed();

  /**
   * Gets the green component of this pixel.
   *
   * @return the list of pixel components
   */
  int getGreen();
  /**
   * Gets the blue component of this pixel.
   *
   * @return the list of pixel components
   */
  int getBlue();

  /**
   * Converts a pixel to a hex representation.
   *
   * @return the hex representation of the pixel
   */
  int pixelToHex();

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

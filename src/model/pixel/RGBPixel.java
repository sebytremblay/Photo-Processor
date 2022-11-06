package model.pixel;

import java.util.function.Function;

/**
 * Represents an RGB pixel.
 */
public class RGBPixel implements Pixel {
  private final int[] components;

  /**
   * Constructor for RBG pixel.
   *
   * @param red      red color of pixel
   * @param green    green color of pixel
   * @param blue     blue color of pixel
   * @param maxValue maxValue of pixel
   */
  public RGBPixel(int red, int green, int blue, int maxValue) {
    this(new int[]{red, green, blue}, maxValue);
  }

  public RGBPixel(int[] components, int maxValue) {
    if (components.length < 3) {
      throw new IllegalArgumentException("Not enough components provided.");
    }

    this.components = components;
  }

  /**
   * Visualizes the value of the pixel.
   *
   * @return the visualized pixel
   */
  @Override
  public Pixel visual(Function<Pixel, Integer> func, int maxValue) {
    int value = func.apply(this);
    return new RGBPixel(value, value, value, maxValue);
  }

  /**
   * Gets the list of all the components of this pixel.
   *
   * @return the list of pixel components
   */
  @Override
  public int[] getComponents() {
    return this.components;
  }

  /**
   * Changes the image brightness by a given factor -Will not brighten
   * if new values are out of range.
   *
   * @param factor amount of brightness to add or reduce to pixel
   * @return the new pixel brightened.
   */
  @Override
  public Pixel brightenPixel(int factor, int maxValue) {
    int brightenRed = this.components[0] + factor;
    int brightenGreen = this.components[1] + factor;
    int brightenBlue = this.components[2] + factor;

    return new RGBPixel(brightenRed, brightenGreen, brightenBlue, maxValue);
  }

  /**
   * converts a pixel to a hex representation
   *
   * @return the hex representation of the pixel
   */
  @Override
  public int PixelToHex() {
    int rgb = components[0];
    rgb = (rgb << 8) + components[1];
    rgb = (rgb << 8) + components[2];
    return rgb;
  }

  /**
   * applies a kernel to this pixel
   *
   * @param kernel           the kernel being applied to the pixel
   * @param kernelBackground the surrounding pixels on the background
   * @return the new Pixel with the applied kernel.
   */
  @Override
  public Pixel kernelEval(int[][] kernel, Pixel[][] kernelBackground, int maxValue) {
    int updatedValueRed = 0;
    int updatedValueGreen = 0;
    int updatedValueBlue = 0;
    for (int row = 0; row < kernel.length; row += 1) {
      for (int col = 0; col < kernel[0].length; col += 1) {
        updatedValueRed += kernel[row][col] * kernelBackground[row][col].getComponents()[0];
        updatedValueGreen += kernel[row][col] * kernelBackground[row][col].getComponents()[1];
        updatedValueBlue += kernel[row][col] * kernelBackground[row][col].getComponents()[2];
      }
    }
    return new RGBPixel(new int[]{updatedValueRed, updatedValueGreen, updatedValueBlue}, maxValue);
  }

  /**
   * makes sure that value is between zero and a particular max value. If it is above or below, it
   * is set to the maxValue and minValue respectively.
   *
   * @param maxValue gets the max value.
   * @return
   */
  private void imposeRange(int maxValue) {

    for (int comp = 0; comp < this.components.length; comp += 1) {
      if (this.components[comp] < 0) {
        components[comp] = 0;
      }
      if (comp > maxValue) {
        components[comp] = maxValue;
      }
    }
  }

  /**
   * Creates a string representation of the pixel as an enumeration of its components.
   *
   * @return the string represents as an enumeration of the components
   */
  @Override
  public String toString() {
    StringBuilder build = new StringBuilder();
    for (int comp : this.components) {
      build.append(comp + "\n");
    }

    return build.toString();
  }

}
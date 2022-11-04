package model.pixel;

import java.util.function.Function;

/**
 * Represents an RGB pixel.
 */
public class RGBPixel implements Pixel {
  private final int red; // must be between 0- and a maxValue
  private final int green; // must be between 0- and a maxValue
  private final int blue; // must be between 0- and a maxValue

  /**
   * Constructor for RBG pixel.
   *
   * @param red      red color of pixel
   * @param green    green color of pixel
   * @param blue     blue color of pixel
   * @param maxValue maxValue of pixel
   */
  public RGBPixel(int red, int green, int blue, int maxValue) {
    if (red < 0 || red > maxValue || green < 0 || green > maxValue ||
            blue < 0 || blue > maxValue || maxValue < 0) {
      throw new IllegalArgumentException("The pixel colors must be" + "between 0 and maxValue");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
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
    int[] comp = {this.red, this.green, this.blue};
    return comp;
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
    int brightenRed = imposeRange(this.red + factor, maxValue);
    int brightenGreen = imposeRange(this.green + factor, maxValue);
    int brightenBlue = imposeRange(this.blue + factor, maxValue);

    return new RGBPixel(brightenRed, brightenGreen, brightenBlue, maxValue);
  }

  /**
   * makes sure that value is between zero and a particular max value. If it is above or below, it
   * is set to the maxValue and minValue respectively.
   *
   * @param comp     the value we are imposing
   * @param maxValue gets the max value.
   * @return
   */
  private int imposeRange(int comp, int maxValue) {
    if (comp < 0) {
      return 0;
    }
    if (comp > maxValue) {
      return maxValue;
    }
    return comp;
  }

  /**
   * Creates a string representation of the pixel as an enumeration of its components.
   *
   * @return the string represents as an enumeration of the components
   */
  @Override
  public String toString() {
    StringBuilder build = new StringBuilder();
    build.append(this.red + "\n");
    build.append(this.green + "\n");
    build.append(this.blue + "\n");

    return build.toString();
  }

}
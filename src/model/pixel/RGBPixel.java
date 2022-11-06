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
    validateComponents(maxValue);
  }

  private void validateComponents(int maxValue) {
    for (int comp : this.components) {
      if (comp < 0 || comp > maxValue) {
        System.out.println("Comp: " + comp);
        System.out.println("Max: " + maxValue);
        throw new IllegalArgumentException("The pixel colors must be between 0 and maxValue");
      }
    }
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
    int brightenRed = imposeRange(this.components[0] + factor, maxValue);
    int brightenGreen = imposeRange(this.components[1] + factor, maxValue);
    int brightenBlue = imposeRange(this.components[2] + factor, maxValue);

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
    for (int comp : this.components) {
      build.append(comp + "\n");
    }

    return build.toString();
  }

}
package model.pixel;

import java.util.function.Function;

/**
 * Represents an RGB pixel.
 */
public class RGBPixel implements Pixel {
  private final int[] components;


  public RGBPixel(int red, int green, int blue, int maxValue) {
    this(new int[]{red, green, blue}, maxValue);
  }

  public RGBPixel(int[] components, int maxValue) {
    if (components.length < 3) {
      throw new IllegalArgumentException("Not enough components provided.");
    }

    this.components = components;
  }


  @Override
  public Pixel visual(Function<Pixel, Integer> func, int maxValue) {
    int value = func.apply(this);
    return new RGBPixel(value, value, value, maxValue);
  }


  @Override
  public int[] getComponents() {
    return this.components;
  }


  @Override
  public Pixel brightenPixel(int factor, int maxValue) {
    int brightenRed = this.components[0] + factor;
    int brightenGreen = this.components[1] + factor;
    int brightenBlue = this.components[2] + factor;

    return new RGBPixel(brightenRed, brightenGreen, brightenBlue, maxValue);
  }


  @Override
  public int PixelToHex() {
    int rgb = components[0];
    rgb = (rgb << 8) + components[1];
    rgb = (rgb << 8) + components[2];
    return rgb;
  }


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


  @Override
  public String toString() {
    StringBuilder build = new StringBuilder();
    for (int comp : this.components) {
      build.append(comp + "\n");
    }

    return build.toString();
  }

}
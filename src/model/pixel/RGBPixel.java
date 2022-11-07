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
    imposeRange(maxValue);
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
  public Pixel kernelEval(double[][] kernel, Pixel[][] kernelBackground, int maxValue) {
    double updatedValueRed = 0;
    double updatedValueGreen = 0;
    double updatedValueBlue = 0;
    for (int row = 0; row < kernel.length; row += 1) {
      for (int col = 0; col < kernel[0].length; col += 1) {
        updatedValueRed += kernel[row][col] * kernelBackground[row][col].getComponents()[0];
        updatedValueGreen += kernel[row][col] * kernelBackground[row][col].getComponents()[1];
        updatedValueBlue += kernel[row][col] * kernelBackground[row][col].getComponents()[2];
      }
    }
    return new RGBPixel(new int[]{(int) updatedValueRed,
            (int) updatedValueGreen,
            (int) updatedValueBlue},
            maxValue);
  }

  @Override
  public Pixel colorTransformation(double[][] transformation, int maxValue) {
    if (transformation.length != components.length
            || transformation[0].length != components.length) {
      throw new IllegalArgumentException("Transformation matrix needs to be square matrix" +
              "with same size as the components matrix");
    }

    int[] newComponents = new int[this.components.length];

    for (int row = 0; row < transformation.length; row += 1) {
      newComponents[row] = (int) dotProduct(transformation[row], this.components);
    }

    return new RGBPixel(newComponents, maxValue);
  }

  private double dotProduct(double[] v1, int[] v2) {
    if (v1.length != v2.length) {
      throw new IllegalArgumentException("Cannot dot vectors of different lengths");
    }

    double dot = 0;

    for (int value = 0; value < v1.length; value += 1) {
      dot += v1[value] * v2[value];
    }

    return dot;
  }

  private void imposeRange(int maxValue) {
    for (int comp = 0; comp < this.components.length; comp += 1) {
      if (this.components[comp] < 0) {
        components[comp] = 0;
      }
      if (this.components[comp] > maxValue) {
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
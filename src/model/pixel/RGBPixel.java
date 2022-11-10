package model.pixel;

import java.util.function.Function;

/**
 * Represents an RGB pixel.
 */
public class RGBPixel implements Pixel {
  private final int red;
  private final int green;
  private final int blue;


  public RGBPixel(int red, int green, int blue) {
    this.red = imposeRange(red);
    this.green = imposeRange(green);
    this.blue = imposeRange(blue);
  }




  @Override
  public int[] getComponents() {
    return new int[]{this.red, this.green, this.blue};
  }

  @Override
  public int PixelToHex() {
    int rgb = this.red;
    rgb = (rgb << 8) + this.green;
    rgb = (rgb << 8) + this.blue;
    return rgb;
  }


  @Override
  public Pixel kernelEval(double[][] kernel, Pixel[][] kernelBackground) {
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
    return new RGBPixel((int) updatedValueRed, (int) updatedValueGreen, (int) updatedValueBlue);
  }

  @Override
  public Pixel colorTransformation(double[][] transformation) {
    if (transformation.length != 3 || transformation[0].length != 3) {
      throw new IllegalArgumentException("Transformation matrix needs to be square matrix" +
              "with same size as the components matrix");
    }
    int[] newComponents = new int[3];
    for (int row = 0; row < transformation.length; row += 1) {
      newComponents[row] = (int) dotProduct(transformation[row], this.getComponents());
    }
    return new RGBPixel(newComponents[0], newComponents[1], newComponents[2]);
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

  private int imposeRange(int colorComp) {
    if (colorComp > 255) {
      return 255;
    }
    if (colorComp < 0) {
      return 0;
    }
    return colorComp;
  }

  @Override
  public String toString() {
    StringBuilder build = new StringBuilder();
    build.append(red + "\n");
    build.append(green + "\n");
    build.append(blue + "\n");

    return build.toString();
  }

}
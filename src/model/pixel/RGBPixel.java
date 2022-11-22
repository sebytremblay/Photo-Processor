package model.pixel;

/**
 * Represents an RGB pixel.
 */
public class RGBPixel implements Pixel {
  private final int red;
  private final int green;
  private final int blue;


  /**
   * Constructs a pixel with the specified RGB components.
   *
   * @param red   the red component
   * @param green the green component
   * @param blue  the blue component
   */
  public RGBPixel(int red, int green, int blue) {
    this.red = imposeRange(red);
    this.green = imposeRange(green);
    this.blue = imposeRange(blue);
  }

  @Override
  public int getRed(){
    return this.red;

  }
  @Override
  public int getGreen(){
    return this.green;

  }
  @Override
  public int getBlue(){
    return this.blue;

  }

  @Override
  public int pixelToHex() {
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
        updatedValueRed += kernel[row][col] * kernelBackground[row][col].getRed();
        updatedValueGreen += kernel[row][col] * kernelBackground[row][col].getGreen();
        updatedValueBlue += kernel[row][col] * kernelBackground[row][col].getBlue();
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
      int count = 0;
      double dot = 0;
      dot += (this.getRed() * transformation[row][count]);
      count+=1;
      dot += (this.getGreen() * transformation[row][count]);
      count+=1;
      dot += (this.getBlue() * transformation[row][count]);
      newComponents[row] = (int)(dot);
    }
    return new RGBPixel(newComponents[0], newComponents[1], newComponents[2]);
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
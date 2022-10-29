import java.util.function.Function;

public class RGBPixel implements Pixel {
  private final int red; // must be between 0-maxValue
  private final int green; // must be between 0-maxValue
  private final int blue; // must be between 0-maxValue
  private final int maxValue;

  public RGBPixel(int red, int green, int blue,int maxValue) {
    if (red < 0 || red > maxValue
            || green < 0 || green > maxValue
            || blue < 0 || blue > maxValue) {
      throw new IllegalArgumentException("The pixel colors must be" +
              "between 0 and maxValue");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.maxValue = maxValue;
  }

  /**
   * Visualizes the value of the pixel.
   *
   * @return the visualized pixel
   */
  @Override
  public Pixel visual(Function<Pixel, Integer> func) {
    int value = func.apply(this);
    return new RGBPixel(value, value, value,maxValue);
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
   * Changes the image brightness by a given factor -Will not brighten if new values are out of range.
   *
   * @param factor amount of brightness to add or reduce to pixel
   * @return the new pixel brightened.
   */
  @Override
  public Pixel brightenPixel(int factor) {
    int brightenRed = imposeRange(this.red + factor);
    int brightenGreen = imposeRange(this.green + factor);
    int brightenBlue = imposeRange(this.blue + factor);

    return new RGBPixel(brightenRed, brightenGreen, brightenBlue,maxValue);
  }

  private int imposeRange(int comp) {
    if (comp < 0) {
      return 0;
    }
    if (comp > maxValue) {
      return maxValue;
    }
    return comp;
  }


}


/**
 * Finds the value of the pixel - the maximum value of the components.
 */
class visualizeValue implements Function<Pixel, Integer> {

  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  @Override
  public Integer apply(Pixel pixel) {
    int[] components = pixel.getComponents();
    int max = components[0];
    for (int comp : components) {
      max = Math.max(max, comp);
    }
    return max;
  }
}

/**
 * Finds the intensity of the pixel by averaging all the components. Will round down if the
 * result is non-integer.
 */
class visualizeIntensity implements Function<Pixel, Integer> {

  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  @Override
  public Integer apply(Pixel pixel) {
    int[] components = pixel.getComponents();
    int sum = 0;
    for (int comp : components) {
      sum += comp;
    }
    return sum / components.length;
  }
}

/**
 * Finds the Luma of the pixel by multiplying each component by a specific value all the components.
 * Will round down if the result is non-integer.
 */
class visualizeLuma implements Function<Pixel, Integer> {

  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  @Override
  public Integer apply(Pixel pixel) {
    int[] components = pixel.getComponents();
    return (int) (0.2126 * components[0]
            + 0.7152 * components[1]
            + 0.0722 * components[2]);
  }

}
class visualizeBlue implements Function<Pixel, Integer>{

  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  @Override
  public Integer apply(Pixel pixel) {
    int[] components = pixel.getComponents();
    return components[2];
  }
}

class visualizeGreen implements Function<Pixel, Integer>{

  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  @Override
  public Integer apply(Pixel pixel) {
    int[] components = pixel.getComponents();
    return components[1];
  }
}
class visualizeRed implements Function<Pixel, Integer>{

  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  @Override
  public Integer apply(Pixel pixel) {
    int[] components = pixel.getComponents();
    return components[0];
  }
}

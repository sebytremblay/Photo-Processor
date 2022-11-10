package model.operations;

import java.util.function.Function;

import model.pixel.Pixel;
import model.pixel.RGBPixel;

/**
 * Finds the value of the pixel - the maximum value of the components.
 */
public class VisualizeValue implements Function<Pixel, Pixel> {

  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  @Override
  public Pixel apply(Pixel pixel) {
    int[] components = pixel.getComponents();
    int max = components[0];
    for (int comp : components) {
      max = Math.max(max, comp);
    }
    return new RGBPixel(max,max,max);
  }
}

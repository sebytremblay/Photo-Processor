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
    int max = Math.max(pixel.getBlue(), Math.max(pixel.getGreen(), pixel.getRed()));
    return new RGBPixel(max, max, max);
  }
}

package model.operations;

import java.util.function.Function;

import model.pixel.Pixel;
import model.pixel.RGBPixel;

/**
 * A command to visualize the green component.
 */
public class VisualizeGreen implements Function<Pixel, Pixel> {

  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  @Override
  public Pixel apply(Pixel pixel) {
    int comp = pixel.getGreen();
    return new RGBPixel(comp, comp, comp);
  }
}

package model.operations;

import java.util.function.Function;

import model.pixel.Pixel;
import model.pixel.RGBPixel;

/**
 * A command to visualize the blue component.
 */
public class VisualizeBlue implements Function<Pixel, Pixel> {

  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  @Override
  public Pixel apply(Pixel pixel) {
    int comp = pixel.getBlue();
    return new RGBPixel(comp, comp, comp);
  }
}

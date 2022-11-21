package model.operations;

import java.util.function.Function;

import model.pixel.Pixel;
import model.pixel.RGBPixel;

/**
 * A command for brightening a pixel.
 */
public class VisualizeBrighten implements Function<Pixel, Pixel> {
  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  private final int brightenBy;

  public VisualizeBrighten(int brightenBy) {
    this.brightenBy = brightenBy;
  }

  @Override
  public Pixel apply(Pixel pixel) {
    return new RGBPixel(pixel.getRed() + brightenBy,
            pixel.getGreen() + brightenBy,
            pixel.getBlue() + brightenBy);
  }
}

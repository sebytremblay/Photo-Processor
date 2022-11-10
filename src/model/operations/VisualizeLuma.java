package model.operations;

import java.util.function.Function;

import model.pixel.Pixel;
import model.pixel.RGBPixel;

/**
 * Finds the Luma of the pixel by multiplying each component by a specific value all the components.
 * Will round down if the result is non-integer.
 */
public class VisualizeLuma implements Function<Pixel, Pixel> {

  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  @Override
  public Pixel apply(Pixel pixel) {
    int[] components = pixel.getComponents();
    int luma = (int) (0.2126 * components[0]
            + 0.7152 * components[1]
            + 0.0722 * components[2]);
    return new RGBPixel(luma,luma,luma);
  }

}

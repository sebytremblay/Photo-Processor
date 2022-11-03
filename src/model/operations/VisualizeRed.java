package model.operations;

import java.util.function.Function;

import model.pixel.Pixel;

/**
 * A command to visualize the red component.
 */
public class VisualizeRed implements Function<Pixel, Integer> {

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

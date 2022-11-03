package model.operations;

import java.util.function.Function;

import model.pixel.Pixel;

/**
 * Finds the intensity of the pixel by averaging all the components. Will round down if the
 * result is non-integer.
 */
public class VisualizeIntensity implements Function<Pixel, Integer> {

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

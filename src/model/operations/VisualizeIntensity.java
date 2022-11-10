package model.operations;

import java.util.function.Function;

import model.pixel.Pixel;
import model.pixel.RGBPixel;

/**
 * Finds the intensity of the pixel by averaging all the components. Will round down if the
 * result is non-integer.
 */
public class VisualizeIntensity implements Function<Pixel,Pixel> {

  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  @Override
  public Pixel apply(Pixel pixel) {
    int[] components = pixel.getComponents();
    int sum = 0;
    for (int comp : components) {
      sum += comp;
    }
    int avg = sum / components.length;
    return new RGBPixel(avg,avg,avg);
  }
}

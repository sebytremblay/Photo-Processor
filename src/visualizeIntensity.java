import java.util.function.Function;

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

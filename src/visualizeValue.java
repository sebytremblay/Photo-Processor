import java.util.function.Function;

/**
 * Finds the value of the pixel - the maximum value of the components.
 */
class visualizeValue implements Function<Pixel, Integer> {

  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  @Override
  public Integer apply(Pixel pixel) {
    int[] components = pixel.getComponents();
    int max = components[0];
    for (int comp : components) {
      max = Math.max(max, comp);
    }
    return max;
  }
}

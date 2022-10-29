import java.util.function.Function;

/**
 * represents an Image
 */
public interface Image {

  /**
   * Visualizes this image based on a given function.
   * @param f that applies the modification to the image
   * @return the new adjusted image
   */
  Image visualize(Function f);

  /**
   * Flips an image of a specified direction.
   * @param dir the way the image will be flipped
   * @return the new flipped image
   */
  Image flipImage(ImageProcessor.Direction dir);

  /**
   * Brightens an image by a specified amount.
   * @param value the value in which you brighten the image components.
   * @return the new brightened image
   */
  Image brightenImage(int value);

  /**
   * gets the desired pixel at a row and col
   * @param row the row of the given image
   * @param col the col of the given image
   * @return the specific pixel at the row and col
   */
  Pixel getPixelAt(int row, int col);
}

package controller.commands;

/**
 * A way to represent the coordinates of a pixel.
 */
public class PixelCoordinate {
  public final int x;
  public final int y;

  /**
   * Stores the provided location.
   *
   * @param x the x-coordinate of the location
   * @param y the y-coordiante of the location
   */
  PixelCoordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

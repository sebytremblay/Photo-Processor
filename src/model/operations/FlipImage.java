package model.operations;

import java.util.function.Function;

import model.imageprocessor.ImageProcessor;
import model.pixel.Pixel;

/**
 * A class for a command that flips entire images in sonme direction.
 */
public class FlipImage implements Function<Pixel[][], Pixel[][]> {

  /**
   * Applies this function to the given argument.
   *
   * @param pixel the function argument
   * @return the function result
   */
  private final ImageProcessor.Direction dir;

  public FlipImage(ImageProcessor.Direction dir) {
    this.dir = dir;
  }

  @Override
  public Pixel[][] apply(Pixel[][] pixelGrid) {
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int row = 0; row < newPixelGrid.length; row += 1) {
      for (int col = 0; col < newPixelGrid[row].length; col += 1) {
        if (dir == ImageProcessor.Direction.Vertical) {
          newPixelGrid[newPixelGrid.length - row - 1][col] = pixelGrid[row][col];
        }
        if (dir == ImageProcessor.Direction.Horizontal) {
          newPixelGrid[row][newPixelGrid[0].length - col - 1] = pixelGrid[row][col];
        }
      }
    }
    return newPixelGrid;
  }
}

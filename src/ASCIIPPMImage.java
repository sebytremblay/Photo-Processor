import java.util.function.Function;

public class ASCIIPPMImage implements Image {
  private final Pixel[][] pixelGrid;
  private final int maxValue;

  public ASCIIPPMImage(Pixel[][] pixelGrid, int maxValue) {
    this.pixelGrid = pixelGrid;
    this.maxValue = maxValue;

  }

  /**
   * Visualizes this image based on a given function.
   *
   * @param f that applies the modification to the image
   * @return the new adjusted image
   */
  @Override
  public Image visualize(Function f) {
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int row = 0; row < newPixelGrid.length; row += 1) {
      for (int col = 0; col < newPixelGrid[row].length; col += 1) {
        Pixel pixel = this.getPixelAt(row, col);
        Pixel newPixel = pixel.visual(f, this.maxValue);
        newPixelGrid[row][col] = newPixel;
      }
    }
    return new ASCIIPPMImage(newPixelGrid, this.maxValue);
  }

  /**
   * Flips an image of a specified direction.
   *
   * @param dir the way the image will be flipped
   * @return the new flipped image
   */
  @Override
  public Image flipImage(ImageProcessor.Direction dir) {
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    if (dir == ImageProcessor.Direction.Vertical) {
      int rowCounter = 0;
      for (int row = newPixelGrid.length - 1; row >= 0; row -= 1) {
        for (int col = 0; col < newPixelGrid[row].length; col += 1) {
          newPixelGrid[rowCounter][col] = getPixelAt(row, col);
        }
        rowCounter += 1;
      }
      return new ASCIIPPMImage(newPixelGrid, this.maxValue);
    }

    int colCounter = 0;
    for (int row = 0; row < newPixelGrid.length; row += 1) {
      for (int col = newPixelGrid[row].length - 1; col >= 0; col -= 1) {
        newPixelGrid[row][colCounter] = getPixelAt(row, col);
        colCounter += 1;
      }
      colCounter = 0;
    }
    return new ASCIIPPMImage(newPixelGrid, this.maxValue);
  }

  /**
   * Brightens an image by a specified amount.
   *
   * @param value the value in which you brighten the image components.
   * @return the new brightened image
   */
  @Override
  public Image brightenImage(int value) {
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int row = 0; row < newPixelGrid.length; row += 1) {
      for (int col = 0; col < newPixelGrid[row].length; col += 1) {
        Pixel pixel = this.getPixelAt(row, col);
        pixel.brightenPixel(value, this.maxValue);
        newPixelGrid[row][col] = pixel;
      }
    }
    return new ASCIIPPMImage(newPixelGrid, this.maxValue);
  }


  /**
   * gets the desired pixel at a row and col
   *
   * @param row the row of the given image
   * @param col the col of the given image
   * @return the specific pixel at the row and col
   */
  @Override
  public Pixel getPixelAt(int row, int col) {
    return this.pixelGrid[row][col];
  }

  /**
   * determines the width of the board
   *
   * @return the width of the board
   */
  @Override
  public int getWidth() {
    return this.pixelGrid.length;
  }

  /**
   * determines the height of the board
   *
   * @return the height of the board
   */
  @Override
  public int getHeight() {
    return this.pixelGrid[0].length;
  }

  /**
   * determines the maxValue of the board
   *
   * @return the maxValue of the board
   */
  @Override
  public int getMaxValue() {
    return this.maxValue;
  }
}

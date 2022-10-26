public interface ImageProcessor {
  /**
   * Loads an image from an ASCII PPM file.
   *
   * @param filePath the file path of the file to load
   * @param imgName  the name of the generated image
   * @return an image representation of the file
   */
  Image loadASCIIPPM(String filePath, String imgName);

  /**
   * An enumeration of all possible RGB colors.
   */
  enum Color {Red, Green, Blue}

  /**
   * Constructs a version of the provided image where the given color is visualized.
   *
   * @param img   the image to visualize
   * @param color the specific color to visualize
   * @return the visualized image
   */
  Image visualizeRGBComponents(Image img, Color color);

  /**
   * Constructs a version of the provided image where the value is visualized.
   *
   * @param img the image to visualize
   * @return the visualized image
   */
  Image visualizeValue(Image img);

  /**
   * Constructs a version of the provided image where the intensity is visualized.
   *
   * @param img the image to visualize
   * @return the visualized image
   */
  Image visualizeIntensity(Image img);

  /**
   * Constructs a version of the provided image where the luma is visualized.
   *
   * @param img the image to visualize
   * @return the visualized image
   */
  Image visualizeLuma(Image img);

  /**
   * An enumeration of directions.
   */
  enum Direction {Horizontal, Vertical}

  /**
   * Flips an image in the given direction.
   *
   * @param imgName      the name of the image to flip
   * @param newImageName the name of the newly flipped image
   * @param dir          the direction to flip
   */
  void flipImage(String imgName, String newImageName, Direction dir);

  /**
   * Brightens image by the provided constant and saves into new image.
   *
   * @param brightenBy the factor to brighten the image by
   * @param imgName    the name of the image to brighten
   * @param newImgName the name of the newly generated image
   */
  void brighten(int brightenBy, String imgName, String newImgName);


  void saveImage(String filePath, String imgName);
}

/**
 * A command to save an image.
 */
public class Save implements ProcessCommand {
  private final String imgPath;
  private final String imgName;

  /**
   * Creates an instance of the Save command.
   *
   * @param imgPath the path of the image to save
   * @param imgName what to name the image after its saved
   */
  public Save(String imgPath, String imgName) {
    this.imgPath = imgPath;
    this.imgName = imgName;
  }

  @Override
  public void go(ImageProcessor model) {
    model.saveImage(this.imgName, this.imgPath);
  }
}

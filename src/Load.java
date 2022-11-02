/**
 * A command to load an image.
 */
public class Load implements ProcessCommand {
  private final String imgPath;
  private final String imgName;

  /**
   * Creates an instance of the Load command.
   *
   * @param imgPath the path of the image to load
   * @param imgName what to name the image after its loaded
   */
  public Load(String imgPath, String imgName) {
    this.imgPath = imgPath;
    this.imgName = imgName;
  }

  @Override
  public void go(ImageProcessor model) {
      model.loadASCIIPPM(this.imgName, this.imgPath);
  }
}

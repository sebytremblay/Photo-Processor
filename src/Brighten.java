/**
 * A command to brighten an image.
 */
public class Brighten implements ProcessCommand {
  private final String imgPath;
  private final String imgName;
  private final int brightenBy;

  /**
   * Creates an instance of the brighten command.
   *
   * @param brightenBy how much to brighten the image by
   * @param imgPath    the path of the image to brighten
   * @param imgName    what to name the image after its flipped
   */
  public Brighten(String brightenBy, String imgPath, String imgName) {
    this.brightenBy = Integer.parseInt(brightenBy);
    this.imgPath = imgPath;
    this.imgName = imgName;
  }

  @Override
  public void go(ImageProcessor model) {
    model.brighten(this.imgPath, this.imgName, this.brightenBy);
  }
}



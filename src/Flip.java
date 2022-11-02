/**
 * A command to flip an image
 */
public class Flip implements ProcessCommand {
  private final String imgName;
  private final String newImgName;
  private final ImageProcessor.Direction dir;

  /**
   * Creates an instance of the flip command with a specifed direction.
   *
   * @param imgName    the name of the image to flip
   * @param newImgName what to name the new image
   * @param dir        the direction to flip
   */
  public Flip(String imgName, String newImgName, ImageProcessor.Direction dir) {
    this.imgName = imgName;
    this.newImgName = newImgName;
    this.dir = dir;
  }

  @Override
  public void go(ImageProcessor model) {
    model.flipImage(this.imgName, this.newImgName, this.dir);
  }
}

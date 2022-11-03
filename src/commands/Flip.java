package commands;


import model.imageprocessor.ImageProcessor;

/**
 * A command to flip an image.
 */
public class Flip extends AbstractCommand {
  private final String imgName;
  private final String newImgName;
  private final ImageProcessor.Direction dir;

  /**
   * Creates an instance of the flip command with a specified direction.
   *
   * @param imgName    the name of the image to flip
   * @param newImgName what to name the new image
   * @param dir        the direction to flip
   * @param append     place to informative information about success of command
   */
  public Flip(String imgName, String newImgName, ImageProcessor.Direction dir, Appendable append) {
    super(append);
    this.imgName = imgName;
    this.newImgName = newImgName;
    this.dir = dir;
  }

  @Override
  public void run(ImageProcessor model) {
    model.flipImage(this.imgName, this.newImgName, this.dir);
    super.successMessage("Flip");
  }


}

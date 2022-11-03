package commands;



import model.imageprocessor.ImageProcessor;

/**
 * A command to brighten an image.
 */
public class Brighten extends AbstractCommand {
  private final String newImageName;
  private final String imgName;
  private final int brightenBy;

  /**
   * Creates an instance of the brightened command.
   *
   * @param brightenBy how much to brighten the image by
   * @param imgName    what to name the image after its flipped
   * @param newImageName   new Image name of the brightened image
   * @param append place to informative information about success of command
   *
   */
  public Brighten(String brightenBy, String imgName, String newImageName, Appendable append) {
    super(append);
    this.brightenBy = Integer.parseInt(brightenBy);
    this.imgName = imgName;
    this.newImageName = newImageName;
  }

  @Override
  public void run(ImageProcessor model) {
    model.brighten(this.imgName, this.newImageName, this.brightenBy);
    super.successMessage("Brighten");
  }
}



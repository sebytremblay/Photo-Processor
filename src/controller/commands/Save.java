package controller.commands;


import utils.ImageUtils;
import model.imageprocessor.ImageProcessor;

/**
 * A command to save an image.
 */
public class Save extends AbstractCommand {
  private final String imgPath;
  private final String imgName;

  /**
   * Creates an instance of the commands.Save command.
   *
   * @param imgPath the path of the image to save
   * @param imgName what to name the image after its saved
   * @param append  place to informative information about success of command
   */
  public Save(String imgPath, String imgName, Appendable append) {
    super(append);
    this.imgPath = imgPath;
    this.imgName = imgName;
  }

  @Override
  public void run(ImageProcessor model) {
    String result = model.getImageAsString(this.imgName);
    ImageUtils.saveImage(imgPath,result);
    super.successMessage("Save");

  }
}

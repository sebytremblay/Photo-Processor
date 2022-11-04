package controller.commands;

import utils.ImageUtils;
import model.imageprocessor.ImageProcessor;

/**
 * A command to load an image.
 */
public class Load extends AbstractCommand {
  private final String imgName;
  private final String imgPath;

  /**
   * Creates an instance of the commands.Flip.Load command.
   *
   * @param imgPath the path of the image to load
   * @param imgName what to name the image after its loaded
   * @param append  place to informative information about success of command
   */
  public Load(String imgPath, String imgName, Appendable append) {
    super(append);
    this.imgName = imgName;
    this.imgPath = imgPath;
  }

  @Override
  public void run(ImageProcessor model) {
    String imageAsString = ImageUtils.readPPM(imgPath);
    model.loadPPM(imageAsString,imgName);
    super.successMessage("Load");
  }

}

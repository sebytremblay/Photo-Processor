package commands;


import model.imageprocessor.ImageProcessor;
import model.pixel.Pixel;
import utils.ImageUtil;

/**
 * A command to load an image.
 */
public class Load extends AbstractCommand {
  private final String imgPath;
  private final String imgName;

  /**
   * Creates an instance of the commands.Flip.Load command.
   *
   * @param imgPath the path of the image to load
   * @param imgName what to name the image after its loaded
   * @param append place to informative information about success of command
   */
  public Load(String imgPath, String imgName,Appendable append) {
    super(append);
    this.imgPath = imgPath;
    this.imgName = imgName;
  }

  @Override
  public void go(ImageProcessor model) {
    Pixel[][] grid = ImageUtil.loadPPM(imgPath);
    int maxValue = ImageUtil.getMaxValue(imgPath);
    model.loadASCIIPPM(imgName,grid,maxValue);
    super.successMessage("Load");
  }

}

package controller.commands;

import java.util.function.Function;
import model.imageprocessor.ImageProcessor;
import model.pixel.Pixel;

/**
 * A command to visualize a component an image.
 */
public class DisplayComponent extends AbstractCommand {
  private final String newImgName;
  private final String imgName;
  private final Function<Pixel, Pixel> func;


  /**
   * Creates an instance of the commands.DisplayComponent command.
   *
   * @param imgName what to name the image after its visualized.
   * @param newImgName new Image name of the visualized image
   * @param func new Image name of the visualized image
   * @param append place to informative information about success of command
   */
  public DisplayComponent(Function<Pixel, Pixel> func,String imgName, String newImgName, Appendable append) {
    super(append);
    this.imgName = imgName;
    this.newImgName = newImgName;
    this.func = func;
  }

  @Override
  public void run(ImageProcessor model) {
    model.visualize(this.imgName,this.newImgName, this.func);
    super.successMessage("Visualize");
  }

}



package controller.commands;

import java.util.Optional;
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

  private final String maskImage;


  /**
   * Creates an instance of the commands.DisplayComponent command.
   *
   * @param func       new Image name of the visualized image
   * @param params     format of imgName maskImageName(optional) newImageName
   * @param append     place to informative information about success of command
   */
  public DisplayComponent(Function<Pixel, Pixel> func, String params, Appendable append) {
    super(append);
    this.func = func;

    String[] commands = params.split(" ");
    if (commands.length == 4){
      this.imgName = commands[1];
      this.maskImage = commands[2];
      this.newImgName = commands[3];

    }else if (commands.length == 3){
      this.imgName = commands[1];
      this.newImgName = commands[2];
      this.maskImage = null;

    }else{
      throw new IllegalArgumentException("Incorrect Arguments");
    }
  }

  @Override
  public void run(ImageProcessor model) {
    model.visualize(this.imgName, this.maskImage, this.newImgName, this.func);
    super.successMessage("Visualize");
  }

}



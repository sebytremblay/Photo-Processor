package controller.commands;


import java.util.function.Function;

import model.imageprocessor.ImageProcessor;
import model.pixel.Pixel;

/**
 * A command to flip an image.
 */
public class Flip extends AbstractCommand {
  private final String imgName;
  private final String newImgName;
  private final Function<Pixel[][],Pixel[][]> func;

  /**
   * Creates an instance of the flip command with a specified direction.
   *
   * @param imgName    the name of the image to flip
   * @param newImgName what to name the new image
   * @param append     place to informative information about success of command
   */
  public Flip(Function<Pixel[][], Pixel[][]> func,String imgName, String newImgName, Appendable append) {
    super(append);
    this.imgName = imgName;
    this.newImgName = newImgName;
    this.func = func;
  }

  @Override
  public void run(ImageProcessor model) {
    model.flipImage(this.imgName, this.newImgName, this.func);
    super.successMessage("Flip");
  }


}

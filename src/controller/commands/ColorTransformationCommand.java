package controller.commands;

import model.imageprocessor.ImageProcessor;

/**
 * A command to apply a kernel an image.
 */
public class ColorTransformationCommand extends AbstractCommand {
  private final String newImgName;
  private final String imgName;
  private final String maskImgName;
  private final double[][] transformation;

  /**
   * Creates a new color transformation command.
   *
   * @param param         params for the command  imgName imgMaskName(optional) newImgName
   * @param transformation the transformation to apply
   * @param append         where to log your changes
   */
  public ColorTransformationCommand(String param,double[][] transformation,
                                    Appendable append) {
    super(append);
    String[] commands = param.split(" ");
    this.transformation = transformation;
    if (commands.length == 4){
      this.imgName = commands[1];
      this.maskImgName = commands[2];
      this.newImgName = commands[3];

    }else if (commands.length == 3){
      this.imgName = commands[1];
      this.newImgName = commands[2];
      this.maskImgName = null;

    }else{
      throw new IllegalArgumentException("Incorrect Arguments");
    }
  }

  @Override
  public void run(ImageProcessor model) {
    model.applyColorTransformation(imgName, maskImgName,newImgName, transformation);
    super.successMessage("Color transformation applied");
  }
}



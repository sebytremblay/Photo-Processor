package controller.commands;

import model.imageprocessor.ImageProcessor;

/**
 * A command to apply a kernel an image.
 */
public class ColorTransformationCommand extends AbstractCommand {
  private final String newImgName;
  private final String imgName;
  private final double[][] transformation;

  public ColorTransformationCommand(String imgName, String newImgName,
                       double[][] transformation, Appendable append) {
    super(append);
    this.imgName = imgName;
    this.newImgName = newImgName;
    this.transformation = transformation;
  }

  @Override
  public void run(ImageProcessor model) {
    model.applyColorTransformation(imgName, newImgName, transformation);
    super.successMessage("Color transformation applied");
  }
}



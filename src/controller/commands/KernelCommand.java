package controller.commands;

import model.imageprocessor.ImageProcessor;

/**
 * A command to apply a kernel an image.
 */
public class KernelCommand extends AbstractCommand {
  private final String newImgName;
  private final String imgName;
  private final double[][] kernel;


  /**
   * Creates a command to apply a given kernel.
   *
   * @param imgName    the image to apply the kernel to
   * @param newImgName what to name the new image
   * @param kernel     the kernel to apply
   * @param append     the output stream
   */
  public KernelCommand(String imgName, String newImgName,
                       double[][] kernel, Appendable append) {
    super(append);
    this.imgName = imgName;
    this.newImgName = newImgName;
    this.kernel = kernel;
  }

  @Override
  public void run(ImageProcessor model) {
    model.applyKernel(imgName, newImgName, kernel);
    super.successMessage("Kernel applied");
  }

}


